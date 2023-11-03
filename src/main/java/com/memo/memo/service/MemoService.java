package com.memo.memo.service;

import com.memo.memo.dto.MemoRequestDto;
import com.memo.memo.dto.MemoResponseDto;
import com.memo.memo.entity.Memo;
import com.memo.memo.repository.MemoRepository;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoService {  // memoService 라는 이름으로 Bean에 등록

    private final MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    public MemoResponseDto createMemo(MemoRequestDto requestDto) {
        // RequestDto -> Entity
        Memo memo = new Memo(requestDto);

        // DB 저장
        Memo saveMemo = memoRepository.save(memo);

        // Entity -> ResponseDto
        MemoResponseDto memoResponseDto = new MemoResponseDto(saveMemo);

        return memoResponseDto;
    }

    public List<MemoResponseDto> getMemos() {
        // DB 조회
        return memoRepository.findAll();
    }

    public Long updateMemo(Long id, MemoRequestDto requestDto) {
        // DB 수정
        // 해당 메모가 DB에 존재하는지 확인
        Memo memo =  memoRepository.findById(id);
        if(memo != null) {
            // memo 내용 수정
            memoRepository.update(id,requestDto);

            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    public Long deleteMemo(Long id) {
        // DB 삭제
        // 해당 메모가 DB에 존재하는지 확인
        Memo memo =  memoRepository.findById(id);
        if(memo != null) {
            // memo 삭제
            memoRepository.delete(id);

            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }


}
