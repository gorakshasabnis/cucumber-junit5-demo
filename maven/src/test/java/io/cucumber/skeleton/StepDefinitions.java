package io.cucumber.skeleton;

import com.google.common.base.Splitter;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.Keys;

import java.util.List;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class StepDefinitions {

    private final Target PAGE_TITLE = Target.the("Page title").locatedBy(".ng-scope h1");
    private final Target ADD_TODO = Target.the("New todo").locatedBy(".new-todo");
    private final Target ADDED_TODO_ITEMS = Target.the("Added todo items").locatedBy(".view label");


    @Given("{actor} is on todomvc")
    public void go_is_on_todomvc(Actor actor) {
        actor.wasAbleTo(Open.browserOn().the(TodoPage.class));
    }
    @Then("{actor} should see title of the page as {string}")
    public void he_should_see_title_of_the_page_as(Actor actor, String titleName) {
        actor.attemptsTo(Ensure.that(PAGE_TITLE).text().isEqualTo(titleName));
    }
    @Then("option to add todos")
    public void option_to_add_todos() {
        theActorInTheSpotlight().attemptsTo(Ensure.that(ADD_TODO).isDisplayed());
    }

    @Then("{actor} should see add todos option with placeholder as {string}")
    public void he_should_see_add_todos_option_with_placeholder_as(Actor actor, String value) {
        actor.attemptsTo(Ensure.that(ADD_TODO).attribute("placeholder").isEqualTo(value));
    }

    @ParameterType(".*")
    public List<String> items(String listOfItems) {
        return Splitter.on(",").trimResults().omitEmptyStrings().splitToList(listOfItems);
    }

    @When("{actor} adds todos as {items}")
    public void he_adds_todos_as_buy_some_milk_walk_the_dog(Actor actor, List<String> items) {
        items.forEach(
                todo -> actor.attemptsTo(
                        Enter.theValue(todo).into(ADD_TODO)
                                .thenHit(Keys.ENTER)
                )
        );
    }
    @Then("todos list should show items as {items}")
    public void todos_list_should_show_items_as_buy_some_milk_walk_the_dog(List<String> items) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(ADDED_TODO_ITEMS).textContentValues().isEqualTo(items),
                Ensure.thatTheListOf(ADDED_TODO_ITEMS).hasSize(items.size())
        );
    }

}
