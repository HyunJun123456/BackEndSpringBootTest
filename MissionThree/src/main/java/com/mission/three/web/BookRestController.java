package com.mission.three.web;

import com.mission.three.domain.BookEntity;
import com.mission.three.dto.req.BookReqDTO;
import com.mission.three.dto.res.BookResDTO;
import com.mission.three.exception.BusinessException;
import com.mission.three.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookRestController {
    @Autowired
    private BookService bookService;

    // 등록
    @PostMapping
    public ResponseEntity<?> registerBook(@RequestBody BookReqDTO bookReqDTO) {
        BookResDTO bookResDTO = bookService.registerBook(bookReqDTO);
        return ResponseEntity.ok(bookResDTO);
    }

    // 전체 목록 조회
    @GetMapping
    public ResponseEntity<?> fetchAllBook() {
        List<BookResDTO> bookList = bookService.fetchAllBook();
        return ResponseEntity.ok(bookList);
    }

    // Id로 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> fetchBookById(@PathVariable Integer id){
        BookResDTO bookResDTO = bookService.fetchBookById(id);
        return ResponseEntity.ok(bookResDTO);
    }

    // ISBN으로 조회
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<?> fetchBookByIsbn(@PathVariable String isbn){
        BookResDTO bookResDTO = bookService.fetchBookByIsbn(isbn);
        return ResponseEntity.ok(bookResDTO);
    }

    // 수정 (Put)
    @PutMapping("/{id}")
    public ResponseEntity<?> modifyBookById(@PathVariable Integer id, @RequestBody BookReqDTO bookReqDTO){
        BookResDTO bookResDTO = bookService.modifyBookById(id, bookReqDTO);
        return ResponseEntity.ok(bookResDTO);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable Integer id){
        bookService.deleteBookById(id);
        return ResponseEntity.ok(id+"가 Book이 삭제되었습니다.");
    }
}
