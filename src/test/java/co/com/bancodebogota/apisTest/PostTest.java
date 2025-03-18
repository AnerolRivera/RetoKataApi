package co.com.bancodebogota.apisTest;

import co.com.bancodebogota.apis.Post;
import org.testng.annotations.Test;

/**
 * Clase de pruebas para validar las peticiones POST a la API FakeStore.
 * Incluye validaciones de consumo exitoso y fallido, además de lectura de JSON.
 */
public class PostTest {

    // Instancia de la clase Post para ejecutar las pruebas
    private final Post postTest = new Post();

    /**
     * Prueba que valida el consumo exitoso de un POST.
     */
    @Test(priority = 4)
    public void testPostSuccess() {
        System.out.println(">>> Ejecutando prueba para creación de carrito...");
        postTest.postRequest("/carts", "src/test/resources/json/cart_invalid.json", 200);
    }

    /**
     * Prueba que valida un consumo fallido de un POST.
     */
    @Test(priority = 5)
    public void testPostFailure() {
        System.out.println(">>> Ejecutando prueba con datos incorrectos...");
        postTest.postRequest("/carts_", "src/test/resources/json/cart_invalid.json", 404);
    }
}
