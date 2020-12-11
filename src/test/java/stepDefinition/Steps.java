package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


public class Steps {

    @When("I hit the biker Api")
    public void i_hit_the_biker_api() {
        // Write code here that turns the phrase above into concrete actions
        boolean hitResult = Utils.hitURL();
        Assert.assertTrue("Unable to hit Api",hitResult);
    }

    @Then("I should get valid response code as 200")
    public void i_should_get_valid_response_code_as() {
        // Write code here that turns the phrase above into concrete actions
        boolean response = Utils.getResponse()==200;
        Assert.assertTrue("Status is not 200",response);
        //System.out.println("I should get valid response code as "+response);
    }
    @Then("I should get valid response data")
    public void i_should_get_valid_response_data() {
        // Write code here that turns the phrase above into concrete actions
        boolean response = Utils.getResponseData() != null;
        Assert.assertTrue("Invalid_response_data",response);
    }

    @Given("developers pass network id {string}")
    public void developersPassNetworkId(final String networkId) {

        Boolean response = Utils.getStationInfo(networkId);
        Assert.assertTrue("Invalid_network_id",response);
    }

    @Then("network location corresponded {string} in {string}")
    public void networkLocationCorrespondedIn(final String city, final String country) {

        Boolean response = Utils.getLocationInfo(city,country);
        Assert.assertTrue("Invalid_location_info",response);
    }

    @Then("corresponded location {string}, {string} returned")
    public void correspondedLocationReturned(final String lat, final String lon) {

        Boolean response = Utils.getLocationPosition(lat,lon);
        Assert.assertTrue("Invalid_location_position",response);
    }
}
