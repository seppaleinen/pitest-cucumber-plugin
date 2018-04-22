package cucumber.examples.java.calculator;

import java.util.Date;

/**
 * This file is copy/pasted from cucumber-jvm java calculator example
 */
public class DateCalculator {

    public static final ThreadLocal<Boolean> FAIL_MODE = new ThreadLocal<>();

    private final Date now;

    DateCalculator(Date now) {
        this.now = now;
    }

    String isDateInThePast(Date date) {
        if (FAIL_MODE.get() != null) {
            return "error";
        }
        return (date.before(now)) ? "yes" : "no";
    }
}
