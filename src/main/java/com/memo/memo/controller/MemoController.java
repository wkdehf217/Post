package com.memo.memo.controller;

import com.memo.memo.dto.MemoRequestDto;
import com.memo.memo.dto.MemoResponseDto;
import com.memo.memo.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MemoController {

    private final Map<Long, Memo> memoList = new HashMap<>();

    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {
        // RequestDto -> Entity
        Memo memo = new Memo(requestDto);

        // Memo Max ID Check
        Long maxId = memoList.size() > 0 ? Collections.max(memoList.keySet()) + 1 : 1; // memoList의 keySet을 가져와서 + 1, 없으면 1을 가져와
        memo.setId(maxId);

        // DB 저장
        memoList.put(memo.getId(), memo);

        // Entity -> ResponseDto
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);

        return memoResponseDto;
    }

    // 조회
    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos(){ // List인 이유는 메모가 하나일리 없어서
        // Map To List
        List<MemoResponseDto> responseList = memoList.values().stream() // stream : 하나씩 for문처럼 돌려줌
                .map(MemoResponseDto::new).toList();                    // map : 변환

        return responseList;
    }
}
