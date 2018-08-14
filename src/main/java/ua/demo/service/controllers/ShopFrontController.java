package ua.demo.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.demo.service.entity.dto.TokenDto;
import ua.demo.service.entity.models.Book;
import ua.demo.service.entity.models.DataWrapper;
import ua.demo.service.services.BookService;
import ua.demo.service.services.FrontMetaDataService;
import ua.demo.service.services.TokenService;

import java.util.List;

@RestController
@RequestMapping("/list")
public class ShopFrontController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private BookService bookService;


    @Autowired
    FrontMetaDataService frontMetaDataService;

    @GetMapping("/list")
    public DataWrapper getFrontData(@RequestHeader("token") TokenDto tokenDto) {
        return frontMetaDataService.getFrontData(tokenDto);
    }

    @PostMapping("/list/buy")
    public DataWrapper buyBooks(@RequestHeader("token") TokenDto tokenDto, @RequestBody List<Long> booksInBasket) {
        return frontMetaDataService.buyBooks(booksInBasket, tokenDto);
    }



    /*@DeleteMapping("/logout")
    public ResponseEntity<Object> logout(@RequestHeader("token") TokenDto tokenDto) {

        Optional<Token> tokenCandidate = tokenService.findByValue(tokenDto.getValue());

        if (tokenCandidate.isPresent()) {
            tokenService.deleteAllByUser(tokenCandidate.get().getUser());
            return ResponseEntity.ok("Correct logout");
        }
        return ResponseEntity.badRequest().build();
    }*/
}
