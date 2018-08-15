package ua.demo.service.exceptions;

/**
 * Created by JohnUkraine on 5/07/2018.
 */
public class NotAvailableLoginException extends RuntimeException {

  private static final String MESSAGE = "Login not available";

  public NotAvailableLoginException() {
    super(MESSAGE);
  }

  public NotAvailableLoginException(String message) {
    super(message);
  }

  public NotAvailableLoginException(Throwable source) {
    super(source);
  }

  public NotAvailableLoginException(String message, Throwable source) {
    super(message, source);
  }

}
