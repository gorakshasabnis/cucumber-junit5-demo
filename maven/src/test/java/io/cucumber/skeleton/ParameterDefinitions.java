package io.cucumber.skeleton;

import io.cucumber.java.ParameterType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;

public class ParameterDefinitions {

    @ParameterType(".*")
    public static Actor actor(String actorName) {
        return OnStage.theActorCalled(actorName);
    }
}
