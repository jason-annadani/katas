package com.yja.noughts.cuke;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class Steps {
	@Given("^a new game$")
	public void a_new_game() throws Throwable {
		System.out.println("a new game!");
	}
	
	@Then("^the displayed board should have:$")
	public void the_displayed_board_should_have(DataTable table) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
	    System.out.println(table.toString());
//	    throw new Error("SNAFU");
	}
}
