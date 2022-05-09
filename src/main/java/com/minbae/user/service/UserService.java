package com.minbae.user.service;

import com.minbae.user.dao.UserMapper;
import com.minbae.user.dto.UserReviewDTO;
import com.minbae.user.exception.UserCommException;
import com.minbae.user.exception.comm.UserExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserMapper userMapper;

    /**주문 많은 순 리스트*/
    public List<Map<String, Object>> getStoreByCategoryOrderAndStar(String category, String type) {


        //주문순이나 별점순 리스트를 담을 놈
        List<Map<String, Object>> orderAndStarCntList = new ArrayList<>();
        //리스트로 나온 놈들의 store idx들을 담을 놈
        List<Long> storeIdxs = new ArrayList<>();

        if(type.equals("order")) {
            //주문 많은 순
            orderAndStarCntList = userMapper.findStoreByCategoryOrder(category);
        }
        else if (type.equals("star")) {
            //별점 높은 순
            orderAndStarCntList = userMapper.findStoreByCategoryStar(category);
        }else{
            throw new UserCommException(UserExceptionType.NOT_EXIST_TYPE);
        }

        //orderAndStarCntList에 담긴 가게 idx들을 따로 담음
        orderAndStarCntList.forEach(data -> storeIdxs.add((Long) data.get("store_idx")));

        //orderAndStarCntList에 담긴 가게 idx들을 제외한 결제 내역, 리뷰 테이블에 없는 가게들 list 출력
        List<Map<String, Object>> storeByNotInIdxs = userMapper.findStoreByNotInIdxs(storeIdxs, category);

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

            long fileSize = mf.getSize(); // 파일 사이즈

            String safeFile = "c:/이젠/upload/" + originFileName;
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
