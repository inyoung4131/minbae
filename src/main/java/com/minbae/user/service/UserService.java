package com.minbae.user.service;

import com.minbae.user.dao.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserMapper userMapper;

    public List<Map<String, Object>> getStoreByCategory(String category) {
        List<Map<String, Object>> result = userMapper.findStoreByCategory(category);

        List<Long> storeIdxs = new ArrayList<>();
        result.forEach(data -> storeIdxs.add((Long) data.get("store_idx")));

        List<Map<String, Object>> result1 = userMapper.findStoreByNotInIdxs(storeIdxs, category);

        result1.forEach(System.out::println);

        List<Map<String, Object>> joined = Stream.concat(result.stream(), result1.stream())
                .collect(Collectors.toList());



        return joined;
    }

}
