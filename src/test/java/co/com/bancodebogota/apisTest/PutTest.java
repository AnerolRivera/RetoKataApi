package co.com.bancodebogota.apisTest;

import co.com.bancodebogota.apis.Put;
import org.testng.annotations.Test;

/**
 * Clase de pruebas para validar las peticiones PUT a la API FakeStore.
 * Incluye validaciones para actualizar un carrito con éxito y manejar errores.
 */
public class PutTest {

    // Instancia de la clase Put para ejecutar las pruebas
    private final Put putTest = new Put();

    /**
     * Prueba que valida la actualización de un carrito con éxito.
     */
    @Test(priority = 6)
    public void testPutSuccess() {
        System.out.println(">>> Ejecutando prueba para actualizar un carrito...");
        putTest.putRequest("/carts/6", "src/test/resources/json/cart_update_success.json", 200);
    }

    /**
     * Prueba que valida un consumo fallido de un PUT.
     */
    @Test(priority = 7)
    public void testPutFailure() {
        System.out.println(">>> Ejecutando prueba con datos inválidos...");
        putTest.putRequest("/carts_99999", "src/test/resources/json/cart_update_invalid.json", 404);
    }
}
