package com.mission.two.web;

import com.mission.two.exception.BusinessException;
import com.mission.two.repository.BookRepository;
import com.mission.two.domain.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookSimpleRestController {
    @Autowired
    private BookRepository bookRepository;

    // 등록
    @PostMapping
    public ResponseEntity<?> registerBook(@RequestBody BookEntity bookEntity) {
        bookRepository.save(bookEntity);
        return ResponseEntity.ok(bookEntity);
    }

    // 전체 목록 조회
    @GetMapping
    public ResponseEntity<?> fetchAllBook() {
        List<BookEntity> bookList = bookRepository.findAll();
        return ResponseEntity.ok(bookList);
    }

    // Id로 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> fetchBookById(@PathVariable Integer id){
        BookEntity book = bookRepository.findById(id).orElseThrow(() -> new BusinessException(id + " Book Not Found", HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(book);
    }

    // ISBN으로 조회
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<?> fetchBookByIsbn(@PathVariable String isbn){
        BookEntity book = bookRepository.findByIsbn(isbn).orElseThrow(() -> new BusinessException(isbn + " Book Not Found", HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(book);
    }

    // 수정 (Put)
    @PutMapping("/{id}")
    public ResponseEntity<?> modifyBookById(@PathVariable Integer id, @RequestBody BookEntity bookEntity){
        BookEntity book = bookRepository.findById(id).orElseThrow(() -> new BusinessException(id + " Book Not Found", HttpStatus.NOT_FOUND));
        book.setAuthor(bookEntity.getAuthor());
        book.setIsbn(bookEntity.getIsbn());
        book.setTitle(bookEntity.getTitle());
        book.setGenre(bookEntity.getGenre());
        bookRepository.save(book);
        return ResponseEntity.ok(book);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable Integer id){
        BookEntity book = bookRepository.findById(id).orElseThrow(() -> new BusinessException(id + " Book Not Found", HttpStatus.NOT_FOUND));
        bookRepository.delete(book);
        return ResponseEntity.ok(id+"가 Book이 삭제되었습니다.");
    }
}
