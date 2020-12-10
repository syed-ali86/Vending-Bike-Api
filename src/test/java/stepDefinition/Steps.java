package stepDefinition;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Steps {

    @When("I hit the biker Api")
    public void i_hit_the_biker_api() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("I hit the biker Api");
    }

    @Then("I should get valid response code as 200")
    public void i_should_get_valid_response_code_as() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("I should get valid response code as 200");
    }
}
