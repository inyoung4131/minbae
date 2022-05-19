package com.minbae.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minbae.sso.jwt.JwtTokenProvider;
import com.minbae.user.dao.UserMapper;
import com.minbae.user.dto.UserReviewDTO;
import com.minbae.user.exception.UserCommException;
import com.minbae.user.exception.comm.UserExceptionType;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;

    /**주문 많은 순 리스트*/
    public List<Map<String, Object>> getStoreByCategoryOrderAndStar(String category, String type) {


        //주문순이나 별점순 리스트를 담을 놈
        List<Map<String, Object>> orderAndStarCntList = new ArrayList<>();
        //리스트로 나온 놈들의 store idx들을 담을 놈
        List<Long> storeIdxs = new ArrayList<>();
        //별점, 주문순에 해당 안 되는 리스트
        List<Map<String, Object>> storeByNotInIdxs;

        if(type.equals("order")) {
            //주문 많은 순
            orderAndStarCntList = userMapper.findStoreByCategoryOrder(category);

            //orderAndStarCntList에 담긴 가게 idx들을 따로 담음
            orderAndStarCntList.forEach(data -> storeIdxs.add((Long) data.get("store_idx")));

            //orderAndStarCntList에 담긴 가게 idx들을 제외한 결제 내역, 리뷰 테이블에 없는 가게들 list 출력
            storeByNotInIdxs = userMapper.findStoreByOrderNotInIdxs(storeIdxs, category);
        }
        else if (type.equals("star")) {
            //별점 높은 순
            orderAndStarCntList = userMapper.findStoreByCategoryStar(category);

            //orderAndStarCntList에 담긴 가게 idx들을 따로 담음
            orderAndStarCntList.forEach(data -> storeIdxs.add((Long) data.get("store_idx")));

            //orderAndStarCntList에 담긴 가게 idx들을 제외한 결제 내역, 리뷰 테이블에 없는 가게들 list 출력
            storeByNotInIdxs = userMapper.findStoreByStarNotInIdxs(storeIdxs, category);


        }else{
            throw new UserCommException(UserExceptionType.NOT_EXIST_TYPE);
        }

        //orderAndStarCntList와 StoreByNotInIdxs를 합침
        List<Map<String, Object>> finalOrderAndStarCntList = Stream.concat(orderAndStarCntList.stream(), storeByNotInIdxs.stream())
                .collect(Collectors.toList());

        return finalOrderAndStarCntList;
    }

    //특정 가게 상세 정보
    public Map<String, Object> findStoreById(Long store_idx) {
        return userMapper.findStoreById(store_idx);
    }

    //특정 가게의 리뷰 사용자, 사장님 개수
    public Map<String, Object> findReviewByCount(Long store_idx) {
        return userMapper.findReviewByCount(store_idx);
    }

    //특정 가게 대표 메뉴 리스트
    public List<Map<String, Object>> findReviewBykingMenu(Long store_idx) {
        return userMapper.findReviewBykingMenu(store_idx);
    }

    //특정 가게 전체 메뉴 리스트
    public List<Map<String, Object>> findByMenuList(Long store_idx) {

        return userMapper.findByMenuList(store_idx);

    }

    //특정 메뉴 idx 관련 정보들
    public Map<String, Object> findStoreByMenuIdx(Long menu_idx) {
        return userMapper.findStoreByMenuIdx(menu_idx);
    }

    //리뷰 작성
    public int reviewCreate(UserReviewDTO dto, List<MultipartFile> upload) throws IOException {

        List<String> fileName = new ArrayList<>();
        for (MultipartFile mf : upload) {
            fileName.add(mf.getOriginalFilename());
            String originFileName = mf.getOriginalFilename(); // 원본 파일 명
            String formatedNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));

            String safeFile = "C:/이젠/upload/" + formatedNow + originFileName;
            try {
                mf.transferTo(new File(safeFile));
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if(fileName.size() == 1){
            dto.setReview_img1(fileName.get(0));
        }else if(fileName.size() == 2){
            dto.setReview_img1(fileName.get(0));
            dto.setReview_img2(fileName.get(1));
        }else if(fileName.size() == 3){
            dto.setReview_img1(fileName.get(0));
            dto.setReview_img2(fileName.get(1));
            dto.setReview_img3(fileName.get(2));
        }

//        copyInto(upload);

        return userMapper.reviewCreate(dto);
    }

    public List<Map<String, Object>> reviewList(Long user_idx) {

        List<Map<String, Object>> reviewList = userMapper.reviewList(user_idx);

        return reviewList;

    }

    public Map<String, Object> reviewCnt(Long user_idx) {
        Map<String, Object> reviewCnt = userMapper.reviewCnt(user_idx);

        return reviewCnt;
    }

    public int reviewDel(String review_idx) {
        int deleteResult = userMapper.reviewDel(review_idx);
        return deleteResult;
    }

    //특정 사용자의 주문 내역 리스트
    public List<Map<String, Object>> orderHistory(Long user_idx) {

        List<Map<String, Object>> orderHistoryList = userMapper.orderHistory(user_idx);

        return orderHistoryList;
    }

    public Map<String, Object> reviewState(Long trade_history_idx) {

        Map<String, Object> reviewState = userMapper.reviewState(trade_history_idx);

        return reviewState;
    }

    public List<Map<String, Object>> storeDetailReview(Long store_idx) {

        List<Map<String, Object>> sd_review_list = userMapper.storeDetailReview(store_idx);

        return sd_review_list;
    }

    //trade_history, trade_history_detail 테이블에 값 넣는 메소드
    public Map<String, Integer> trade_history_insert(Map<String, Object> map){
        //trade_history에 값 넣기
        int trade_history_result = 0;
        if(map.get("order_deliver_or_take_out").toString().equals("deliver")){
            trade_history_result = userMapper.trade_history_deliver_insert(map);
        }else {
            trade_history_result = userMapper.trade_history_take_out_insert(map);
        }
        //trade_history 마지막에 추가된 idx 값 추출
        Integer trade_history_idx = userMapper.get_last_trade_history_idx();

        //trade_history_detail에 값 넣기
        String[] count = map.get("count").toString().split(",");
        String[] menu_idx = map.get("menu_idx").toString().split(",");

        int trade_history_detail_result = 0;
        for(int i = 0; i < count.length; i++){
            trade_history_detail_result += userMapper.trade_history_detail_insert(count[i], menu_idx[i], trade_history_idx);
        }

        //디비 저장 결과값과 trade_history_idx값
        int dbInsertResult = trade_history_result + trade_history_detail_result;
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("dbInsertResult", dbInsertResult);
        resultMap.put("trade_history_idx", trade_history_idx);
        return resultMap;
    }

    public Map<String, Integer> payment(Map<String, Object> map) throws JsonProcessingException {

        System.out.println(map);

        if(map.get("order_payment").toString().equals("card")) {    //카드일 때
            /**결제 정보 조회*/
            // imp_uid, merchant_uid 추출
            String imp_uid = map.get("imp_uid").toString();
            String merchant_uid = map.get("merchant_uid").toString();

            //rest api 키, rest api secret -> data
            Map<String, Object> iamportRequest = new HashMap<>();
            iamportRequest.put("imp_key", "7055545585085380");
            iamportRequest.put("imp_secret", "34bb5ea753524e201d83a0cbb71875ab9b3bffc9e9a0ef1fd91d9c567ed93977ed68752db8e959a0");

            /*액세스 토큰(access token) 발급 받기*/
            //headers
            HttpHeaders token_httpHeaders = new HttpHeaders();
            token_httpHeaders.add("Content-Type", "application/json");

            //data, headers
            HttpEntity token_httpEntity = new HttpEntity(iamportRequest, token_httpHeaders);

            //restTemplate을 통해 요청 토큰 발급 요청
            RestTemplate restTemplate = new RestTemplate();

            //post로 요청, getToken에 json 문자열로 받아와짐
            ResponseEntity<String> getToken = restTemplate.postForEntity("https://api.iamport.kr/users/getToken", token_httpEntity, String.class);
//        System.out.println(getToken);

            ObjectMapper mapper = new ObjectMapper();
            //json 문자열로 받아와진 getToken을 jsonNode로 생성
            JsonNode root = mapper.readTree(getToken.getBody());
            //response의 access_token으로 접근해서 인증 토큰 가져오기
            JsonNode token = root.path("response").path("access_token");
//        System.out.println("token -> " + token); -> "712f575bcf73a066a66c103551f4da5facd0d052"로 출력

            /*imp_uid로 아임포트 서버에서 결제 정보 조회*/
            // 인증 토큰 Authorization header에 추가
            HttpHeaders info_httpHeaders = new HttpHeaders();
            info_httpHeaders.add("Authorization", token.toString().replaceAll("\"", ""));
            //get방식이라 body 없이 headers만 추가
            HttpEntity info_httpEntity = new HttpEntity(null, info_httpHeaders);

            //결제정보 조회 요청
            ResponseEntity<String> payment_info = restTemplate.exchange("https://api.iamport.kr/payments/" + imp_uid, HttpMethod.GET, info_httpEntity, String.class);
            System.out.println("payment_info >> " + payment_info);

            ObjectMapper payment_info_mapper = new ObjectMapper();
            //json 문자열로 받아와진 payment_info_mapper을 jsonNode로 생성
            JsonNode payment_info_root = payment_info_mapper.readTree(payment_info.getBody());
            //결제 정보에 있는 amount 추출
            JsonNode info_amount = payment_info_root.path("response").path("amount");

            /**결제되어야 할 금액과 실제 결제된 금액을 비교 후 디비에 값 저장*/
            String order_price = map.get("order_price").toString();
            if(Integer.parseInt(info_amount.toString()) == Integer.parseInt(order_price)) {
                //trade 테이블들에 값 넣기 메소드 호출
                return trade_history_insert(map);
            } else {
                throw new UserCommException(UserExceptionType.inconsistency);
            }
        }else{  //현금일 때
            //trade 테이블들에 값 넣기 메소드 호출
            return trade_history_insert(map);
        }
    }

    public Map<String, Object> get_user_info(Long user_idx) {

        Map<String, Object> user_info = userMapper.get_user_info(user_idx);

        return user_info;
    }

    public String minimum_price(Long store_idx) {
        String minimum_price = userMapper.minimum_price(store_idx);
        return minimum_price;
    }

    public List<Map<String, String>> get_order_store(Integer user_idx) {
        List<Map<String, String>> order_store = userMapper.get_order_store(user_idx);

        return order_store;
    }

    public Map<String, Integer> get_store_lication(Integer store_idx) {
        Map<String, Integer> store_location = userMapper.get_store_lication(store_idx);

        return store_location;
    }

    //카카오 로그인
//    public Map<String, Object> kakaoLogin(Map<String, Object> param) {
//        // 카카오 회원 체크
//        Map<String, Object> kakaoUser = userMapper.kakaoLogin(param);
//        System.out.println(kakaoUser);
//
//        // 없으면 insert
//        if(kakaoUser == null) {
//            userMapper.insertKakaoUser(param);
//            kakaoUser = userMapper.kakaoLogin(param);
//        }
//
//        Map<String, Object> result = new HashMap<>();
//        result.put("accessToken", jwtTokenProvider.createToken(kakaoUser.get("user_email").toString(), Long.parseLong(kakaoUser.get("userIdx").toString())));
//        result.put("memberData", kakaoUser);
//        return result;
//    }

//    public int trade_history_idx() {
//        int trade_history_idx = userMapper.get_last_trade_history_idx();
//
//        return trade_history_idx;
//    }


//    public void copyInto(List<MultipartFile> upload) throws IOException {
//
//        //실제 파일의 바이트 단위의 정보를 가지고 올 수 있음, 입출력 사이에 예외가 발생할 수 있으니 예외 처리
//        try {
//
//            /**원본 담기*/
//            byte[] bytes = upload.getBytes;
//
//            /** 이곳에 복사 */
//            File f = new File("c:/이젠/upload/"+upload.stream().forEach(data -> data.getOriginalFilename()););
//
//            /**저 위치에 실제 저장할 수 있도록 스트림을 사용, 1byte 스트림 체계를 사용 - 바이트 단위로 쪼개서 저장하기 때문에*/
//            FileOutputStream fos = new FileOutputStream(f);
//
//            /**복사*/
//            fos.write(bytes);
//
//            /**자원 닫기*/
//            fos.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
}
