package com.minbae.storedetail.service;

import com.minbae.storedetail.dto.StoreDetailSaveRequestDto;
import com.minbae.storedetail.dto.StoreDetailUpdateRequestDto;
import com.minbae.storedetail.entity.StoreDetail;
import com.minbae.storedetail.repository.StoreDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class StoreDetailService {

    private final StoreDetailRepository storeDetailRepository;

    // 특정 가게 세부 정보 반환
    public StoreDetail storeDetailInfo(Long storeIdx) {
        StoreDetail allByStoreIdx = storeDetailRepository.findByStore(storeIdx);
        return allByStoreIdx;
    }

    // 특정 가게 세부 정보 수정
    public StoreDetail update(Long storeDetailIdx, StoreDetailUpdateRequestDto storeDetailUpdateRequestDto) {
        // dto -> entity
        StoreDetail target = storeDetailUpdateRequestDto.toEntity();
        // 올바르지 않은 storeIdx 검증
        if(storeDetailIdx != target.getStoreDetailIdx()){return null;}
        // repository db
        StoreDetail updated = storeDetailRepository.save(target);
        return updated;
    }

    // 특정 가게 세부 정보 생성
    @Transactional
    public StoreDetail create(StoreDetailSaveRequestDto storeDetailSaveRequestDto){
        return storeDetailRepository.save(storeDetailSaveRequestDto.toEntity());
    }


    public StoreDetail updateNew(Long storeDetailIdx, StoreDetailUpdateRequestDto storeDetailUpdateRequestDto, MultipartFile req) throws IOException {
        if(storeDetailIdx != storeDetailUpdateRequestDto.getStoreDetailIdx()){
           return null;
        }
        // 첨부된 이미지가 존재할 경우
        if(req.getBytes().length > 0) {
            // delete exist image in local repository
            deleteFile(storeDetailRepository.findImgNameById(storeDetailIdx));
            /* 로컬 저장소에 이미지 저장 메소드 실행 */
            String savedFileName = saveImgMethod(req);
            // dto setting
            storeDetailUpdateRequestDto.setStoreDetailInfoImg(savedFileName);
        }else{
            storeDetailUpdateRequestDto.setStoreDetailInfoImg(storeDetailRepository.findImgNameById(storeDetailIdx));
        }

        // dto -> entity
        StoreDetail newStoreDetail = storeDetailUpdateRequestDto.toEntity();
        // save entity in db
        return storeDetailRepository.save(newStoreDetail);
    }

    private String saveImgMethod(MultipartFile req) {
        String formatedNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년MM월dd일HH시mm분ss초"));
        String originFileName = req.getOriginalFilename();
        String savedFileName = formatedNow+originFileName;
        String saveFile = "C:\\workspace\\upload\\" + formatedNow + originFileName;

        try {
            req.transferTo(new File(saveFile));
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return savedFileName;
    }

    public void deleteFile(String imgName){
        File deleteTarget = new File("C:\\workspace\\upload\\"+imgName);
        if(deleteTarget.exists()){
            deleteTarget.delete();
        }else{
            System.out.println("존재하지 않는 파일");
        }
    }


}
