package com.mission.four.service;

import com.mission.four.domain.BookEntity;
import com.mission.four.dto.req.BookReqDTO;
import com.mission.four.dto.res.BookResDTO;
import com.mission.four.exception.BusinessException;
import com.mission.four.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    // 등록
    public BookResDTO registerBook(BookReqDTO bookReqDTO) {
        BookEntity bookEntity = modelMapper.map(bookReqDTO, BookEntity.class);
        BookEntity savedBook = bookRepository.save(bookEntity);
        return modelMapper.map(savedBook, BookResDTO.class);
    }

    // 전체 목록 조회
    public List<BookResDTO> fetchAllBook() {
        List<BookEntity> bookList = bookRepository.findAll();

        return bookList.stream()
                .map(bookEntity -> modelMapper.map(bookEntity, BookResDTO.class))
                .collect(Collectors.toList());
    }

    // Id로 조회
    public BookResDTO fetchBookById(int id) {
        BookEntity bookEntity = bookRepository.findById(id).orElseThrow(
                () -> new BusinessException("Book Not Found", HttpStatus.NOT_FOUND));
        return modelMapper.map(bookEntity, BookResDTO.class);
    }

    // ISBN으로 조회
    public BookResDTO fetchBookByIsbn(String isbn) {
        BookEntity bookEntity = bookRepository.findByIsbn(isbn).orElseThrow(
                () -> new BusinessException("Book Not Found", HttpStatus.NOT_FOUND));
        return modelMapper.map(bookEntity, BookResDTO.class);
    }

    // 수정 (Put)
    public ResponseEntity<?> modifyBookById(@PathVariable Integer id, @RequestBody BookEntity bookEntity) {
        BookEntity book = bookRepository.findById(id).orElseThrow(() -> new BusinessException(id + " Book Not Found", HttpStatus.NOT_FOUND));
        book.setAuthor(bookEntity.getAuthor());
        book.setIsbn(bookEntity.getIsbn());
        book.setTitle(bookEntity.getTitle());
        book.setGenre(bookEntity.getGenre());
        return ResponseEntity.ok(book);
    }

    public BookResDTO modifyBookById(int id, BookReqDTO bookReqDTO) {
        BookEntity bookEntity = bookRepository.findById(id).orElseThrow(
                () -> new BusinessException("Book Not Found", HttpStatus.NOT_FOUND));
        bookEntity.setAuthor(bookReqDTO.getAuthor());
        bookEntity.setIsbn(bookReqDTO.getIsbn());
        bookEntity.setTitle(bookReqDTO.getTitle());
        bookEntity.setGenre(bookReqDTO.getGenre());
        bookRepository.save(bookEntity);
        return modelMapper.map(bookEntity, BookResDTO.class);
    }


    // 삭제
    public void deleteBookById(int id) {
        BookEntity book = bookRepository.findById(id).orElseThrow(() -> new BusinessException(id + " Book Not Found", HttpStatus.NOT_FOUND));
        bookRepository.delete(book);
    }

}
