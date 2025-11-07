package servlet; // Asegúrate de usar el paquete correcto

import models.Producto; // Ajusta el paquete si es necesario
import service.ProductoService; // Ajusta el paquete si es necesario
import service.ProductoServiceImplement; // Ajusta el paquete si es necesario

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper; // Importa el serializador de Jackson

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet para exportar la lista de productos en formato JSON.
 */
@WebServlet("/productos.json") // Mapea este servlet a la URL que genera el JSON
public class ProductoJsonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Obtener la lista de productos
        ProductoService productoService = new ProductoServiceImplement();
        List<Producto> productos = productoService.listar();

        // 2. Configurar el encabezado de la respuesta para JSON y forzar la descarga

        // Content-Type: Indica al navegador que el contenido es JSON
        resp.setContentType("application/json;charset=UTF-8");

        // Content-Disposition: Fuerza al navegador a descargar el archivo con el nombre especificado
        resp.setHeader("Content-Disposition", "attachment; filename=productos.json");

        // 3. Serializar y escribir el JSON
        try (PrintWriter out = resp.getWriter()) {

            // Crea el objeto serializador de Jackson
            ObjectMapper mapper = new ObjectMapper();

            // Convierte la lista de Java a JSON y la escribe directamente al flujo de respuesta
            mapper.writeValue(out, productos);

        } catch (IOException e) {
            // Manejo básico de errores de serialización o escritura
            System.err.println("Error al serializar o escribir JSON: " + e.getMessage());
            e.printStackTrace();
            // Envía un error 500 al cliente
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al generar el archivo JSON.");
        }
    }
}