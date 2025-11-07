package servlet;

import models.Producto;
import service.ProductoService;
import service.ProductoServiceImplement;

// Importa las clases necesarias de Jakarta Servlet para manejar peticiones web
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet para exportar la lista de productos en formato JSON.
 */
// Mapea este Servlet a la URL "/productos.json". Cuando se acceda a esta URL, se ejecutará el Servlet.
@WebServlet("/productos.json")
public class ProductoJsonServlet extends HttpServlet {

    // Sobreescribe el metodo doGet para manejar peticiones GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Inicializa el servicio para obtener la lista de productos
        ProductoService productoService = new ProductoServiceImplement();
        // Llama al metodo listar() para obtener todos los productos de la base de datos o fuente de datos
        List<Producto> productos = productoService.listar();

        // Configurar el encabezado de la respuesta para JSON y forzar la descarga
        // Content-Type: Indica al navegador que el contenido de la respuesta es JSON con codificación UTF-8
        resp.setContentType("application/json;charset=UTF-8");

        // Content-Disposition: Indica al navegador que la respuesta debe tratarse como un archivo adjunto
        // y fuerza la descarga con el nombre "productos.json"
        resp.setHeader("Content-Disposition", "attachment; filename=productos.json");

        // Serializar y escribir el JSON
        // Usa un bloque try-with-resources para asegurar que el PrintWriter se cierre automáticamente
        try (PrintWriter out = resp.getWriter()) {

            // Crea una instancia de ObjectMapper de Jackson, la herramienta para serializar
            ObjectMapper mapper = new ObjectMapper();

            // Convierte la lista de objetos 'productos' a formato JSON
            // y escribe directamente el resultado en el flujo de salida 'out' del HttpServletResponse
            mapper.writeValue(out, productos);

        } catch (IOException e) {
            // Manejo básico de errores de serialización o escritura
            System.err.println("Error al serializar o escribir JSON: " + e.getMessage());
            e.printStackTrace(); // Imprime la traza completa del error en la consola del servidor

            // Envía un código de estado de error 500 (Internal Server Error) al cliente
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al generar el archivo JSON.");
        }
    }
}