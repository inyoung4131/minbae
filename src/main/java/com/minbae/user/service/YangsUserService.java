package com.minbae.user.service;

import com.minbae.review.dao.ReviewMapper;
import com.minbae.review.dto.ReviewCountAndAvgStar;
import com.minbae.store.dao.StoreMapper;
import com.minbae.store.entity.Store;
import com.minbae.store.repository.StoreRepository;
import com.minbae.user.comm.UserApiStatus;
import com.minbae.user.dao.UserMapper;
import com.minbae.user.dto.UserAddrChangeDto;
import com.minbae.user.dto.UserJoinDto;
import com.minbae.user.dto.UserResponseStoreListDto;
import com.minbae.user.entity.User;
import com.minbae.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.ResultMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class YangsUserService {

    private final StoreMapper storeMapper;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final ReviewMapper reviewMapper;

    public boolean join(UserJoinDto userJoinDto) {
        return userRepository.save(userJoinDto.toEntity()).getUserEmail() == null ? false : true;
    }

    public UserApiStatus userEmailCheck(String userEmail) {
        return userRepository.findByUserEmail(userEmail) == null ? UserApiStatus.SUCCESS : UserApiStatus.FAIL;
    }

    public long userAddrChange(UserAddrChangeDto userAddrChangeDto) {
        User user = userRepository.findByUserIdx(userAddrChangeDto.getUserIdx());
        user.updateAddr(userAddrChangeDto);
        return userRepository.save(user).getUserIdx();
    }

    //기본순 출력
    public List<Map<String, Object>> getStoreByCategoryAndStandard(int page, String category, double user_lat, double user_lng) {
        page = page * 20;
        int page2 = page + 19;
        List<Map<String, Object>> list = storeMapper.findStandardList(page, page2, category, user_lat, user_lng);
        if (list != null && list.size() != 0)
            for (int i = 0; i < list.size(); i++) {
                ReviewCountAndAvgStar reviewCountAndAvgStar = reviewMapper.getReviewCountAndStarAvg((Long) list.get(i).get("store_idx"));
                if (reviewCountAndAvgStar != null) {
                    list.get(i).put("avger_star", reviewCountAndAvgStar.getAvger_star());
                    list.get(i).put("cou", reviewCountAndAvgStar.getCou());
                }
            }
        return list;
    }

    public Store getStoreinfo(long storeIdx) {
        return storeRepository.findByStoreIdx(storeIdx);
    }

    public String sendSms(String tel){
        return sendSMS(tel);
    }


    public String makeSignature(String url, String timestamp, String method, String accessKey, String secretKey)
            throws NoSuchAlgorithmException, InvalidKeyException {
        String space = " ";                    // one space
        String newLine = "\n";                    // new line

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey = null;
        String encodeBase64String = "";
        try {
            signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");

            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);

            byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));

            encodeBase64String = Base64.getEncoder().encodeToString(rawHmac);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeBase64String;
    }

    private String sendSMS(String tel) {
        String hostNameUrl = "https://sens.apigw.ntruss.com";
        String requestUrl = "/sms/v2/services/";
        String requestUrlType = "/messages";
        String accessKey = "jUaOu7qg8xzRJLmsr8eb";
        String secretKey = "BQycyHZWZ5ejz0MOqAIhBo0qKI0FO4dBFsnAapNf";
        String serviceId = "ncp:sms:kr:285641515006:wpskdsek_1rg";
        String method = "POST";
        String timestamp = Long.toString(System.currentTimeMillis());
        requestUrl += serviceId + requestUrlType;
        String apiUrl = hostNameUrl + requestUrl;
        JSONObject bodyJson = new JSONObject();
        JSONObject toJson = new JSONObject();
        JSONArray toArr = new JSONArray();
        Random rand = new Random();
        int authnum=rand.nextInt(89999)+10000;
        String authNum=Integer.toString(authnum);
        toJson.put("to", tel);
        toArr.put(toJson);
        bodyJson.put("type", "SMS");
        bodyJson.put("countryCode", "82");
        bodyJson.put("from", "01035584131");
        bodyJson.put("content", "인증번호 ["+authnum+"]를 입력 해주세요");
        bodyJson.put("messages", toArr);
        String body = bodyJson.toString();
        System.out.println(body);
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("content-type", "application/json");
            con.setRequestProperty("x-ncp-apigw-timestamp", timestamp);
            con.setRequestProperty("x-ncp-iam-access-key", accessKey);
            con.setRequestProperty("x-ncp-apigw-signature-v2",
                    makeSignature(requestUrl, timestamp, method, accessKey, secretKey));
            con.setRequestMethod(method);
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.write(body.getBytes());
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br;
            System.out.println("responseCode" + " " + responseCode);
            if (responseCode == 202) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        return authNum;
    }

}
