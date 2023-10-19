package com.mission.three.repository;


import com.mission.three.domain.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {


    // 등록

    // 전체 목록 조회

    // Id로 조회

    // ISBN으로 조회
    Optional<BookEntity> findByIsbn(String isbn);
    // 수정 (Put)

    // 삭제
}
