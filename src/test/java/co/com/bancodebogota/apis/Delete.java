package co.com.bancodebogota.apis;
import co.com.bancodebogota.Utils.Configuration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import io.restassured.response.Response;
import org.testng.Assert;
import static io.restassured.RestAssured.given;
/**
 * Clase que gestiona peticiones DELETE a la API FakeStore.
 * Implementa validaciones de estado y maneja la eliminación de recursos.
 */
public class Delete {
    // Instancia de Gson para formatear JSON de manera legible
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    /**
     * Método para eliminar un recurso en la API.
     *
     * @param urlComplement      Endpoint que se concatenará a la URL base.
     * @param expectedStatusCode Código de estado HTTP esperado.
     */
    public void deleteRequest(String urlComplement, int expectedStatusCode) {
        // Realizar la solicitud DELETE
        Response response = given()
                .baseUri(Configuration.BASE_URL)
                .when()
                .delete(urlComplement);

        // Obtener código de estado y respuesta
        int actualStatusCode = response.statusCode();
        String responseBody = response.asString();

        // Mostrar información en consola
        System.out.println("\n===============================");
        System.out.println(">> Ejecutando DELETE: " + Configuration.BASE_URL + urlComplement);
        System.out.println(">> Código de estado esperado: " + expectedStatusCode);
        System.out.println(">> Código de estado recibido: " + actualStatusCode);
        System.out.println(">> Respuesta del API:\n" + responseBody);
        // Formatear la respuesta si es un JSON válido
        try {
            String formattedJson = gson.toJson(gson.fromJson(responseBody, Object.class));
            System.out.println("Respuesta JSON formateada:\n" + formattedJson);
        } catch (JsonSyntaxException e) {
            System.out.println("La respuesta no es un JSON válido. Respuesta cruda:\n" + responseBody);
        }
        System.out.println("===============================\n");

        // Validar código de estado
        Assert.assertEquals(actualStatusCode, expectedStatusCode,
                "ERROR: Se esperaba el código " + expectedStatusCode + " pero se recibió " + actualStatusCode);
    }
}
