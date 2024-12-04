import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.CreateUserPojo;
import static io.restassured.RestAssured.given;

public class ApiClient {

    private final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    String bearerToken;


    public String registerUser(CreateUserPojo CreateUserPojo) {
        Response response = given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(CreateUserPojo)
                .post("/api/auth/register");
        return bearerToken = response.jsonPath().getString("accessToken");
    }



    public void deleteUser() {
        given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .header("Authorization", bearerToken)
                .delete("/api/auth/user");
    }
}
