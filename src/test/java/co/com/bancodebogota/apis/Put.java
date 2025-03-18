package co.com.bancodebogota.apis;

import co.com.bancodebogota.Utils.Configuration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import io.restassured.response.Response;
import org.testng.Assert;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;

/**
 * Clase que gestiona peticiones PUT a la API FakeStore.
 * Implementa validaciones de estado y permite actualizar recursos usando un archivo JSON.
 */
public class Put {
    // Instancia de Gson para formatear JSON de manera legible
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    /**
     * Método para actualizar un recurso con una petición PUT.
     *
     * @param urlComplement      Endpoint que se concatenará a la URL base.
     * @param jsonFilePath       Ruta del archivo JSON que contiene el body de la solicitud.
     * @param expectedStatusCode Código de estado HTTP esperado.
     */
    public void putRequest(String urlComplement, String jsonFilePath, int expectedStatusCode) {
        try {
            // Leer el archivo JSON
            String requestBody = new String(Files.readAllBytes(Paths.get(jsonFilePath)));

            // Realizar la solicitud PUT
            Response response = given()
                    .baseUri(Configuration.BASE_URL)
                    .header("Content-Type", "application/json")
                    .body(requestBody)
                    .when()
                    .put(urlComplement);

            // Obtener código de estado y respuesta
            int actualStatusCode = response.statusCode();
            String responseBody = response.asString();

            // Mostrar información en consola
            System.out.println("\n===============================");
            System.out.println(">> Ejecutando PUT: " + Configuration.BASE_URL + urlComplement);
            System.out.println(">> Código de estado esperado: " + expectedStatusCode);
            System.out.println(">> Código de estado recibido: " + actualStatusCode);
            System.out.println(">> Respuesta del API:\n" + responseBody);
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
        } catch (Exception e) {
            System.out.println("ERROR al leer el archivo JSON: " + e.getMessage());
        }
    }
}
