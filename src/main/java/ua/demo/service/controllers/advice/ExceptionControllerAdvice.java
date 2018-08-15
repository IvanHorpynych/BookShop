package ua.demo.service.controllers.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.demo.service.exceptions.Constants;
import ua.demo.service.exceptions.InvalidTokenException;
import ua.demo.service.exceptions.NotAvailableLoginException;
import ua.demo.service.exceptions.UserNotFoundException;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionControllerAdvice {


    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<?> handleInvalidTokenException(HttpServletRequest request, Exception ex){
        return ResponseEntity.badRequest().header(Constants.ERROR_HEADER, ex.getMessage()).build();
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(HttpServletRequest request, Exception ex) {
        return ResponseEntity.badRequest().header(Constants.ERROR_HEADER, ex.getMessage()).build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpServletRequest request, Exception ex) {
        return ResponseEntity.badRequest().header(Constants.ERROR_HEADER, Constants.JSON_FORMAT_ERROR).build();
    }

    @ExceptionHandler(NotAvailableLoginException.class)
    public ResponseEntity<?> handleNotAvailableLoginException(HttpServletRequest request, Exception ex) {
        return ResponseEntity.badRequest().header(Constants.ERROR_HEADER, ex.getMessage()).build();
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleGeneralException(HttpServletRequest request, Exception ex) {
        return ResponseEntity.badRequest().header(Constants.ERROR_HEADER, Constants.INTERNAL_ERROR).build();

    }

    /*@ModelAttribute
    public void setAccessControlResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:63342");
    }*/

}
