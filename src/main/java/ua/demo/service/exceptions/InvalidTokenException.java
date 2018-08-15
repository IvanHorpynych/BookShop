package ua.demo.service.exceptions;

/**
 * Created by JohnUkraine on 5/07/2018.
 */
public class InvalidTokenException extends RuntimeException {

  private static final String MESSAGE = "Invalid token";

  public InvalidTokenException() {
    super(MESSAGE);
  }

  public InvalidTokenException(String message) {
    super(message);
  }

  public InvalidTokenException(Throwable source) {
    super(source);
  }

  public InvalidTokenException(String message, Throwable source) {
    super(message, source);
  }

}
