package models.forms;

public class FormUtils {

    public static final String DATE_REGEX =
            "(0[1-9]|1[012])/" + // month
            "(0[1-9]|1[0-9]|2[0-9]|3[01])/" + //day
            "19[0-9][0-9]|200[0-9]|201[0-2]"; //year

    public static final String PHONE_REGEX = "[0-9]{10}";

    public static final String LATIN_CHARACTERS_REGEX = "\\p{Alpha}+";

    public static final String DEPARTMENT_REGEX = "[A-Z]{2,4}";

    public static final String MONEY_REGEX = "\\d{0,4}(?:.\\d{2})?";
}
