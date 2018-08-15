package ua.demo.service.exceptions;

/**
 * Created by JohnUkraine on 5/07/2018.
 */
public class UserNotFoundException extends RuntimeException {

  private static final String MESSAGE = "User not found";

  public UserNotFoundException() {
    super(MESSAGE);
  }

  public UserNotFoundException(String message) {
    super(message);
  }

  public UserNotFoundException(Throwable source) {
    super(source);
  }

  public UserNotFoundException(String message, Throwable source) {
    super(message, source);
  }

}
