package ua.demo.service.entity;

public class RegexMappings {

    public static final String EXCLUDE_RUSSIAN_CHARS_CLASS = "[^ёЁЫыЪъЭэ]";
    public static final String ALLOWED_LETTERS = "A-ZА-ЯІЇЄҐa-zа-яіїєґ";
    public static final String NAME_SURNAME_REGEX =
            "^[" + EXCLUDE_RUSSIAN_CHARS_CLASS + "&&[" + ALLOWED_LETTERS + "'\\s-]]{2,40}$";

    public static final String AMOUNT_REGEX = "^(\\d{0,9}.?\\d{1,4})$";

    public static final String EMAIL_REGEX =
            "(?:[a-z0-9!#$%&'*+=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+=?^_`{|}~-]+)*"
                    + "|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]"
                    + "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")"
                    + "@(?:(?:[a-zа-я0-9](?:[a-zа-я0-9-]*[a-zа-я0-9])?\\.)"
                    + "+[a-zа-я0-9](?:[a-zа-я0-9-]*[a-zа-я0-9])?|\\[(?:(?:25[0-5]"
                    + "|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]"
                    + "|[01]?[0-9][0-9]?|[a-zа-я0-9-]*[a-zа-я0-9]:"
                    + "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]"
                    + "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
}
