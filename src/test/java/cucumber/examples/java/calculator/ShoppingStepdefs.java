package cucumber.examples.java.calculator;

import cucumber.api.Transformer;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.deps.com.thoughtworks.xstream.annotations.XStreamConverter;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * This file is copy/pasted from cucumber-jvm java calculator example
 */
public class ShoppingStepdefs {
    private static final RpnCalculator CALC = new RpnCalculator();

    @Given("^the following groceries:$")
    public void the_following_groceries(List<Grocery> groceries) {
        for (Grocery grocery : groceries) {
            CALC.push(grocery.price.value);
            CALC.push("+");
        }
    }

    @When("^I pay (\\d+)$")
    public void i_pay(int amount) {
        CALC.push(amount);
        CALC.push("-");
    }

    @Then("^my change should be (\\d+)$")
    public void my_change_should_be_(int change) {
        assertEquals(-CALC.value().intValue(), change);
    }

    public static class Grocery {
        public String name;
        @XStreamConverter(Price.Converter.class)
        public Price price;

        public Grocery() {
            super();
        }
    }

    public static class Price {
        final int value;

        Price(int value) {
            this.value = value;
        }

        public static class Converter extends Transformer<Price> {
            @Override
            public Price transform(String value) {
                return new Price(Integer.parseInt(value));
            }
        }
    }
}
