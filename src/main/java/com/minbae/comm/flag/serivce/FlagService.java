package com.minbae.comm.flag.serivce;


import com.minbae.comm.flag.dto.FlagSaveDto;
import com.minbae.comm.flag.repository.FlagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FlagService {
    private final FlagRepository flagRepository;

    public long createFlag(FlagSaveDto flagSaveDto){
        return flagRepository.save(flagSaveDto.toEntity()).getFlagIdx();
    }

}
