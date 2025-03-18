package co.com.bancodebogota.Utils;

/**
 * Clase de configuración donde almacenar información
 * general del proyecto, como la URL base de la API.
 */
public class Configuration {
    public static final String BASE_URL = System.getProperty("api.base.url", "https://fakestoreapi.com");
}
