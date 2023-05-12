package be.hogent.nthiers;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;

@QuarkusTest
public class People5ResourceTest {
    @Inject
    PeopleInMemoryRepository peopleInMemoryRepository;

    @Test
    public void testPeople5Endpoint() {
        containsInAnyOrder(peopleInMemoryRepository.getPeople5(2))
                .matches(
                        given()
                                .when().get("/people5/2")
                                .then()
                                .statusCode(200)
                                .and()
                                .extract()
                                .body().as(Person5[].class)
                );
    }

    @Test
    public void testPeople5Endpoint_SameListOnEveryCall() {
        var people = peopleInMemoryRepository.getPeople5(2);

        containsInAnyOrder(people)
                .matches(
                        given()
                                .when().get("/people5/2")
                                .then()
                                .statusCode(200)
                                .and()
                                .extract()
                                .body().as(Person5[].class)
                );

        containsInAnyOrder(people)
                .matches(
                        given()
                                .when().get("/people5/2")
                                .then()
                                .statusCode(200)
                                .and()
                                .extract()
                                .body().as(Person5[].class)
                );
    }

    @Test
    public void testPeople5SupportedAmountsEndpoint_InMemoryEmpty() {
        peopleInMemoryRepository.clearData();

        containsInAnyOrder(Collections.emptyList())
                .matches(
                        given()
                                .when().get("/people5/supportedamounts")
                                .then()
                                .statusCode(200)
                                .extract()
                                .body()
                );
    }

    @Test
    public void testPeople5SupportedAmountsEndpoint_InMemoryNotEmpty() {
        peopleInMemoryRepository.clearData();
        peopleInMemoryRepository.getPeople5(5);
        peopleInMemoryRepository.getPeople5(10);

        containsInAnyOrder(new Integer[]{5, 10})
                .matches(
                        given()
                                .when().get("/people5/supportedamounts")
                                .then()
                                .statusCode(200)
                                .and()
                                .extract()
                                .body().as(Integer[].class)
                );
    }

}