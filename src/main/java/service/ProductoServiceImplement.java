package service;

import models.Producto;

import java.util.Arrays;
import java.util.List;

// Esta clase implementa la interfaz ProductoService, proporcionando la lógica de negocio
public class ProductoServiceImplement implements ProductoService{

    // Sobreescribe el metodo 'listar()' definido en la interfaz
    @Override
    public List<Producto> listar() {
        // En una aplicación real, aquí iría la lógica para conectarse a una DB.

        // Simulación: Se usa Arrays.asList para crear y devolver una lista inmutable de objetos Producto
        return Arrays.asList(
                // Creación del primer producto (ID, Nombre, Tipo, Precio)
                new Producto(1L,"laptop", "computacion",523.21),
                // Creación del segundo producto
                new Producto(2L,"Mouse","inalambrico",15.25),
                // Creación del tercer producto
                new Producto(3L,"Impresora", "tinta continua",256.25));
        // El metodo devuelve esta lista al Servlet que la solicitó.
    }
}