// Define el paquete donde se encuentra la interfaz
package service;

// Importa la clase del modelo Producto
import models.Producto;

// Importa la interfaz List de Java util
import java.util.List;

/**
 * Interfaz que define el contrato de la capa de servicio para la entidad Producto.
 * Esta interfaz desacopla la lógica de negocio de su implementación.
 */
public interface ProductoService {

    /**
     * Metodo que define la operación para obtener una lista de todos los productos.
     * En la implementación (ProductoServiceImplement), se define cómo se obtienen estos datos.
     *
     * @return Una lista de objetos Producto.
     */
    List<Producto> listar();
}