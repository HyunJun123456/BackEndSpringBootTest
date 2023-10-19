package com.mission.four.web;

import com.mission.four.domain.BookEntity;
import com.mission.four.dto.req.BookReqDTO;
import com.mission.four.dto.res.BookResDTO;
import com.mission.four.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/index")
    public String myIndex(Model model) {
        List<BookResDTO> bookList = bookService.fetchAllBook();
        model.addAttribute("bookList", bookList);
        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(BookReqDTO bookReqDTO, Model model) {
        model.addAttribute("book", bookReqDTO);
        return "add-book";
    }

    @PostMapping("/registerbook")
    public String registerBook(@Valid @ModelAttribute("book") BookReqDTO bookReqDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("book", bookReqDto);
            return "add-book";
        }
        bookService.registerBook(bookReqDto);
        model.addAttribute("books", bookService.fetchAllBook());
        return "redirect:/books/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateFrom(@PathVariable Integer id, Model model) {
        BookResDTO bookResDTO = bookService.fetchBookById(id);
        model.addAttribute("book", bookResDTO);
        return "update-book";
    }

    @PostMapping("/edit/{id}")
    public String editBook(@Valid @ModelAttribute("book") BookReqDTO bookReqDTO, BindingResult result, Model model, @PathVariable Integer id) {
        if (result.hasErrors()) {
            model.addAttribute("books", bookReqDTO);

            return "update-book";
        }
        bookService.modifyBookById(id, bookReqDTO);
        return "redirect:/books/index";
    }

    @GetMapping("/remove/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        bookService.deleteBookById(id);
        return "redirect:/books/index";
    }

}
