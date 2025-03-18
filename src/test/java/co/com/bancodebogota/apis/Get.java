package co.com.bancodebogota.apis;

import co.com.bancodebogota.Utils.Configuration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

/**
 * Clase que gestiona peticiones GET a la API FakeStore.
 * Implementa validaciones de estado, manejo de errores y formateo de respuestas.
 */
public class Get {

    // Instancia de Gson para formatear JSON de manera legible
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Método genérico para realizar una petición GET y validar el código de estado esperado.
     *
     * @param urlComplement      Endpoint que se concatenará a la URL base.
     * @param expectedStatusCode Código de estado HTTP esperado.
     */
    public void getRequest(String urlComplement, int expectedStatusCode) {
        System.out.println("\n===============================");
        System.out.println(">> Ejecutando GET: " + Configuration.BASE_URL + urlComplement);

        // Realizar la solicitud GET
        Response response = given()
                .baseUri(Configuration.BASE_URL)
                .header("Accept", "application/json")
                .when()
                .get(urlComplement);

        // Obtener código de estado y respuesta
        int actualStatusCode = response.statusCode();
        String responseBody = response.asString();

        // Mostrar información en consola
        System.out.println(">> Código de estado esperado: " + expectedStatusCode);
        System.out.println(">> Código de estado recibido: " + actualStatusCode);

        // Validación con mensaje detallado
        Assert.assertEquals(actualStatusCode, expectedStatusCode,
                "ERROR: Se esperaba el código " + expectedStatusCode + " pero se recibió " + actualStatusCode);

        // Formatear la respuesta si es un JSON válido
        try {
            String formattedJson = gson.toJson(gson.fromJson(responseBody, Object.class));
            System.out.println("Respuesta JSON formateada:\n" + formattedJson);
        } catch (JsonSyntaxException e) {
            System.out.println("La respuesta no es un JSON válido. Respuesta cruda:\n" + responseBody);
        }

        System.out.println("===============================\n");
    }

    /**
     * Método que obtiene todos los carritos.
     */
    public void getAllCarts() {
        getRequest("/carts", 200);
    }

    /**
     * Método que obtiene un carrito específico por ID.
     */
    public void getCartById(int cartId) {
        getRequest("/carts/" + cartId, 200);
    }

    /**
     * Método que valida una petición a un ID de carrito inexistente.
     */
    public void getInvalidCart() {
        getRequest("/carts_99999", 404);
    }
}
