package com.redhat.pizza.catalogue.controller;

import com.redhat.pizza.catalogue.entity.Menu;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static io.restassured.RestAssured.given;

/**
 * <pre>
 *     com.redhat.pizza.catalogue.controller.MenuControllerTest
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 02 Sep 2022 22:35
 */
@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Epic("Customer Ordering Pizza")
public class MenuControllerTest {

    /**
     *  initialize a Menu which consist of 7 different foods
     */
    @Transactional
    @BeforeAll
    public static void init(){
        Menu.persist(new Menu(null, "food 01", new BigDecimal(1000)));
        Menu.persist(new Menu(null, "food 02", new BigDecimal(1200)));
        Menu.persist(new Menu(null, "food 03", new BigDecimal(1400)));
        Menu.persist(new Menu(null, "food 04", new BigDecimal(1500)));
        Menu.persist(new Menu(null, "food 05", new BigDecimal(1800)));
        Menu.persist(new Menu(null, "food 06", new BigDecimal(1800)));
        Menu.persist(new Menu(null, "food 07", new BigDecimal(1800)));
    }

    @Test
    @Order(1)
    @Feature("Display Menu")
    @Description("Rest API Menu test to an existing Menu table")
    @Story( "AS A user, " +
            "I WANT to fill the Menu with 7 food options, " +
            "I WANT to display 5 food options per-page, " +
            "I WANT to display the first page of the Menu, " +
            "SO THAT the Menu are displaying 5 food options")
    public void displayMenu_firstPage_test() {
        given()
                .when()
                .get("/api/v1/menu?page=0")
                .then()
                .statusCode(200)
                .body("$", Matchers.hasSize(5));
    }

    @Test
    @Order(2)
    @Feature("Display Menu")
    @Description("Rest API Menu test to an existing Menu table")
    @Story( "AS A user, " +
            "I WANT to fill the Menu with 7 food options, " +
            "I WANT to display 5 food options per-page, " +
            "I WANT to display the second page of the Menu, " +
            "SO THAT the Menu are displaying 2 food options")
    public void displayMenu_secondPage_test() {
        given()
                .when()
                .get("/api/v1/menu?page=1")
                .then()
                .statusCode(200)
                .body("$", Matchers.hasSize(2));
    }

    @Test
    @Order(3)
    @Feature("Display Menu")
    @Description("Rest API Menu test to an existing Menu table")
    @Story( "AS A user, " +
            "I WANT to fill the Menu with 7 food options, " +
            "I WANT to display 5 food options per-page, " +
            "I WANT to display the third page of the Menu, " +
            "SO THAT the Menu are displaying 0 food options")
    public void displayMenu_thirdPage_test() {
        given()
                .when()
                .get("/api/v1/menu?page=2")
                .then()
                .statusCode(200)
                .body("$", Matchers.hasSize(0));
    }

    @Test
    @Order(4)
    @Feature("Display Menu")
    @Description("Rest API Menu test to an existing Menu table")
    @Story( "AS A user, " +
            "I WANT to fill the Menu with 7 food options, " +
            "I WANT to display 5 food options per-page, " +
            "I WANT to display the hundredth page of the Menu, " +
            "SO THAT the Menu are displaying 0 food options")
    public void displayMenu_hundredPage_test() {
        given()
                .when()
                .get("/api/v1/menu?page=99")
                .then()
                .statusCode(200)
                .body("$", Matchers.hasSize(0));
    }

    @Test
    @Order(5)
    @Feature("Display Menu")
    @Description("Rest API Menu test to an existing Menu table")
    @Story( "AS A user, " +
            "I WANT to fill the Menu with 7 food options, " +
            "I WANT to display 5 food options per-page, " +
            "I WANT to display the negative one page of the Menu, " +
            "SO THAT the Menu are displaying 0 food options and with HTTP 400 error code")
    public void displayMenu_negativeOnePage_test() {
        given()
                .when()
                .get("/api/v1/menu?page=-1")
                .then()
                .statusCode(400)
                .body("$", Matchers.hasSize(0));
    }

    @Test
    @Order(6)
    @Feature("Display Menu")
    @Description("Rest API Menu test to an existing Menu table")
    @Story( "AS A user, " +
            "I WANT to fill the Menu with 7 food options, " +
            "I WANT to display 5 food options per-page, " +
            "I WANT to display the null page of the Menu, " +
            "SO THAT the Menu are displaying 0 food options and with HTTP 400 error code")
    public void displayMenu_nullPage_test() {
        given()
                .when()
                .get("/api/v1/menu")
                .then()
                .statusCode(400)
                .body("$", Matchers.hasSize(0));
    }

    @Test
    @Order(7)
    @Feature("Display Menu")
    @Description("Rest API Menu test to an existing Menu table")
    @Story( "AS A user, " +
            "I WANT to fill the Menu with 7 food options, " +
            "I WANT to display 5 food options per-page, " +
            "I WANT to display the abc page of the Menu, " +
            "SO THAT the Menu are displaying HTTP 404 error code")
    public void displayMenu_abcPage_test() {
        given()
                .when()
                .get("/api/v1/menu?page=abc")
                .then()
                .statusCode(404);
    }
}
