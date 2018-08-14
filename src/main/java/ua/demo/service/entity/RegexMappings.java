package ua.demo.service.entity;

public class RegexMappings {

    public static final String EXCLUDE_RUSSIAN_CHARS_CLASS = "[^ёЁЫыЪъЭэ]";
    public static final String ALLOWED_LETTERS = "A-ZА-ЯІЇЄҐa-zа-яіїєґ";
    public static final String NAME_SURNAME_REGEX =
            "^[" + EXCLUDE_RUSSIAN_CHARS_CLASS + "&&[" + ALLOWED_LETTERS + "'\\s-]]{2,40}$";
}
