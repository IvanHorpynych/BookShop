package ua.demo.service.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.demo.service.entity.dto.TokenDto;
import ua.demo.service.entity.forms.OrderForm;
import ua.demo.service.entity.models.Book;
import ua.demo.service.entity.models.DataWrapper;
import ua.demo.service.entity.models.User;
import ua.demo.service.exceptions.InvalidTokenException;
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
                .currentUser(tokenService.findByValue(token.getToken())
                        .orElseThrow(InvalidTokenException::new).getUser())
                .allowedBooks(bookService.findAll()).build();
    }

    @Override
    @Transactional
    public DataWrapper makePurchase(List<OrderForm> booksInBasket, TokenDto tokenDto) {
        Optional<Book> bookOpt;
        List<Book> updatedBooks = new LinkedList<>();
        Book tempBook;
        BigDecimal earnedMoney = BigDecimal.ZERO;

        for (OrderForm bookOrder : booksInBasket) {
            bookOpt = bookService.findById(bookOrder.getId());
            if (bookOpt.isPresent()) {
                tempBook = bookOpt.get();
                tempBook.setTimesBought(tempBook.getTimesBought()+bookOrder.getCount());
                earnedMoney = earnedMoney.add(tempBook.getPrice().multiply(BigDecimal.valueOf(bookOrder.getCount())));
                updatedBooks.add(tempBook);
            }
        }

        if (!earnedMoney.equals(BigDecimal.ZERO))
           bookService.save(updatedBooks);

        User user = tokenService.findByValue(
                tokenDto.getToken()).get().getUser();

        user.setEarnedMoney(user.getEarnedMoney().add(earnedMoney));


        return DataWrapper.builder()
                .allowedBooks(bookService.findAll())
                .currentUser(usersService.save(user))
                .build();

    }

}

