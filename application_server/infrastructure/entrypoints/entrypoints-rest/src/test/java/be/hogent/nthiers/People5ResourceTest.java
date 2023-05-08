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
    public void testPeople5Endpoint_InMemoryEmpty() {
        containsInAnyOrder(Collections.emptyList())
                .matches(
                        given()
                                .when().post("/people5/cleardata")
                                .then()
                                .statusCode(200)
                                .and()
                                .extract()
                                .body().as(Integer[].class)
                );

        containsInAnyOrder(Collections.emptyList())
                .matches(
                        given()
                                .when().get("/people5/2")
                                .then()
                                .statusCode(200)
                );
    }

    @Test
    public void testPeople5Endpoint_InMemoryNotEmpty() {
        containsInAnyOrder(DataGenerator.NUMBER_LIST)
                .matches(
                        given()
                                .when().post("/people5/loaddata")
                                .then()
                                .statusCode(200)
                                .and()
                                .extract()
                                .body().as(Integer[].class)
                );

        containsInAnyOrder(DataGenerator.NUMBER_LIST.get(0))
                .matches(
                        given()
                                .when().get("/people5/" + DataGenerator.NUMBER_LIST.get(0))
                                .then()
                                .statusCode(200)
                                .and()
                                .extract()
                                .body().as(Person5[].class)
                );
    }

    @Test
    public void testPeople5Endpoint_InMemoryNotEmpty_unsupportedAmount() {
        containsInAnyOrder(DataGenerator.NUMBER_LIST)
                .matches(
                        given()
                                .when().post("/people5/loaddata")
                                .then()
                                .statusCode(200)
                                .and()
                                .extract()
                                .body().as(Integer[].class)
                );

        containsInAnyOrder(Collections.emptyList())
                .matches(
                        given()
                                .when().get("/people5/5")
                                .then()
                                .statusCode(200)
                                .extract()
                                .body()
                );
    }

    @Test
    public void testPeople5SupportedAmountsEndpoint_InMemoryEmpty() {
        containsInAnyOrder(Collections.emptyList())
                .matches(
                        given()
                                .when().post("/people5/cleardata")
                                .then()
                                .statusCode(200)
                                .and()
                                .extract()
                                .body().as(Integer[].class)
                );

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
        containsInAnyOrder(DataGenerator.NUMBER_LIST)
                .matches(
                        given()
                                .when().post("/people5/loaddata")
                                .then()
                                .statusCode(200)
                                .and()
                                .extract()
                                .body().as(Integer[].class)
                );

        containsInAnyOrder(DataGenerator.NUMBER_LIST)
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