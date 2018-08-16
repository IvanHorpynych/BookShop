package ua.demo.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.demo.service.entity.dto.TokenDto;
import ua.demo.service.entity.models.Book;
import ua.demo.service.entity.models.DataWrapper;
import ua.demo.service.services.BookService;
import ua.demo.service.services.BasketService;

import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*",exposedHeaders = "error-message")
public class ShopFrontController {

    @Autowired
    private BookService bookService;


    @Autowired
    BasketService basketService;

    @GetMapping("/list")
    public DataWrapper getFrontData(@RequestHeader("token") TokenDto tokenDto) {
        return basketService.getFrontData(tokenDto);
    }

    @PostMapping("/buy")
    public DataWrapper buyBooks(@RequestHeader("token") TokenDto tokenDto, @RequestBody List<Long> booksInBasket) {
        return basketService.makePurchase(booksInBasket, tokenDto);
    }

    @PostMapping("/addBook")
    public DataWrapper addBook(@RequestHeader("token") TokenDto tokenDto, @RequestBody Book book){
        return bookService.addBook(book, tokenDto);
    }


}
