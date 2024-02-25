package demoqa.tests;

import com.codeborne.selenide.Configuration;
import demoqa.model.CredentialsModel;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {


    private String userName =  "test123456",
            password = "Test123456@";
    public CredentialsModel credentials = new CredentialsModel(userName, password);

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        RestAssured.baseURI = "https://demoqa.com";
    }
}