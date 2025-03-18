package co.com.bancodebogota.apisTest;

import co.com.bancodebogota.apis.Delete;
import org.testng.annotations.Test;

/**
 * Clase de pruebas para validar las peticiones DELETE a la API FakeStore.
 * Incluye validaciones para eliminar un carrito con éxito y manejar errores.
 */
public class DeleteTest {

    // Instancia de la clase Delete para ejecutar las pruebas
    private final Delete deleteTest = new Delete();

    /**
     * Prueba que valida la eliminación exitosa de un carrito.
     */
    @Test(priority = 8)
    public void testDeleteSuccess() {
        System.out.println(">>> Ejecutando prueba para eliminar un carrito...");
        deleteTest.deleteRequest("/carts/6", 200);
    }

    /**
     * Prueba que valida un consumo fallido de un DELETE.
     */
    @Test(priority = 9)
    public void testDeleteFailure() {
        System.out.println(">>> Ejecutando prueba de eliminación de un carrito inexistente...");
        deleteTest.deleteRequest("/carts_99999", 404);
    }
}
