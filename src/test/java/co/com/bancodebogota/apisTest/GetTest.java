package co.com.bancodebogota.apisTest;

import co.com.bancodebogota.apis.Get;
import org.testng.annotations.Test;

/**
 * Clase de pruebas para validar las peticiones GET a la API FakeStore.
 * Incluye validaciones para obtener todos los carritos, obtener un carrito por ID y manejar errores.
 */
public class GetTest {

    // Instancia de la clase Get para ejecutar las pruebas
    private final Get getTest = new Get();

    /**
     * Prueba que valida el consumo exitoso de todos los carritos.
     */
    @Test(priority = 1)
    public void testGetAllCarts() {
        System.out.println(">>> Ejecutando prueba para consultar todos los carritos...");
        getTest.getAllCarts();
    }

    /**
     * Prueba que valida la obtención de un carrito específico por ID.
     */
    @Test(priority = 2)
    public void testGetCartById() {
        System.out.println(">>> Ejecutando prueba para consultar un carrito por ID...");
        getTest.getCartById(6); // Se puede cambiar el ID si es necesario.
    }

    /**
     * Prueba que valida un consumo con error (consulta de un carrito inexistente).
     */
    @Test(priority = 3)
    public void testGetInvalidCart() {
        System.out.println(">>> Ejecutando prueba para consulta fallida...");
        getTest.getInvalidCart();
    }
}
