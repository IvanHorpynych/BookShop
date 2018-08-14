package ua.demo.service.services.impl;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.demo.service.entity.dto.TokenDto;
import ua.demo.service.entity.models.Book;
import ua.demo.service.entity.models.DataWrapper;
import ua.demo.service.services.BookService;
import ua.demo.service.services.FrontMetaDataService;
import ua.demo.service.services.TokenService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FrontMetaDataServiceImpl implements FrontMetaDataService {

    @Autowired
    TokenService tokenService;

    @Autowired
    BookService bookService;


    @Override
    public DataWrapper getFrontData(TokenDto token) {
        return DataWrapper.builder()
                .currentUser(tokenService.findByValue(token.getValue())
                        .orElseThrow(IllegalArgumentException::new).getUser())
                .allowedBooks(bookService.findAll()).build();
    }

    @Override
    @Transactional
    public DataWrapper buyBooks(List<Long> booksInBasket, TokenDto tokenDto) {
        Map<Long, Book> books = new HashMap<>(booksInBasket.size()-1);
        Optional<Book> bookOpt;
        BigDecimal earnedMoney = BigDecimal.ZERO;
        for (long id: booksInBasket){
            if(books.containsKey(id))
                books.get(id).incrementTimesBought();
            earnedMoney.add()
            else {
                tempBook = bookService.findById(id);
                if(tempBook.isPresent()) {
                    tempBook.get().incrementTimesBought();
                    books.put(id, tempBook.get()boo);
                }
            }
        }

        return null;
    }
}
