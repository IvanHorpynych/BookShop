package ua.demo.service.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.demo.service.entity.dto.TokenDto;
import ua.demo.service.entity.models.Book;
import ua.demo.service.entity.models.DataWrapper;
import ua.demo.service.entity.models.User;
import ua.demo.service.services.BookService;
import ua.demo.service.services.BasketService;
import ua.demo.service.services.TokenService;
import ua.demo.service.services.UsersService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;


@Service
@AllArgsConstructor(onConstructor =  @__(@Autowired))
public class BasketServiceImpl implements BasketService {

    TokenService tokenService;

    BookService bookService;

    UsersService usersService;


    @Override
    public DataWrapper getFrontData(TokenDto token) {
        return DataWrapper.builder()
                .currentUser(tokenService.findByValue(token.getValue())
                        .orElseThrow(IllegalArgumentException::new).getUser())
                .allowedBooks(bookService.findAll()).build();
    }

    @Override
    @Transactional
    public DataWrapper makePurchase(List<Long> booksInBasket, TokenDto tokenDto) {
        Map<Long, Book> books = new HashMap<>(new HashSet<>(booksInBasket).size(),1);
        Optional<Book> bookOpt;
        Book tempBook;
        BigDecimal earnedMoney = BigDecimal.ZERO;

        for (long id : booksInBasket) {
            if (books.containsKey(id)) {
                tempBook = books.get(id);
                tempBook.incrementTimesBought();
                earnedMoney = earnedMoney.add(tempBook.getPrice());
            } else {
                bookOpt = bookService.findById(id);
                if (bookOpt.isPresent()) {
                    tempBook = bookOpt.get();
                    books.put(id, tempBook);
                    tempBook.incrementTimesBought();
                    earnedMoney = earnedMoney.add(tempBook.getPrice());
                }
            }
        }

        List<Book> updatedBooks;
        if (!earnedMoney.equals(BigDecimal.ZERO))
            updatedBooks = bookService.save(new ArrayList<>(books.values()));
        else updatedBooks = new ArrayList<>(books.values());

        User user = tokenService.findByValue(
                tokenDto.getValue()).get().getUser();

        user.setEarnedMoney(user.getEarnedMoney().add(earnedMoney));


        return DataWrapper.builder()
                .allowedBooks(updatedBooks)
                .currentUser(usersService.save(user))
                .build();

    }

}

