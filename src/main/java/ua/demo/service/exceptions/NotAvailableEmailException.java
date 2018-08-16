package ua.demo.service.exceptions;

/**
 * Created by JohnUkraine on 5/07/2018.
 */
public class NotAvailableEmailException extends RuntimeException {

  private static final String MESSAGE = "Email not available";

  public NotAvailableEmailException() {
    super(MESSAGE);
  }

  public NotAvailableEmailException(String message) {
    super(message);
  }

  public NotAvailableEmailException(Throwable source) {
    super(source);
  }

  public NotAvailableEmailException(String message, Throwable source) {
    super(message, source);
  }

}
