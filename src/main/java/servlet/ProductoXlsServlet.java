package servlet;

import models.Producto;
import service.ProductoService;
import service.ProductoServiceImplement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

// Implementamos la anotación que mapea el Servlet a dos rutas: para XLS y para HTML.
@WebServlet({"/productos.xls", "/productos.html"})
public class ProductoXlsServlet extends HttpServlet {

    // Sobreescribe el metodo doGet para manejar peticiones GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Inicializa el servicio para obtener los datos
        ProductoService service = new ProductoServiceImplement();
        // Obtiene la lista de todos los productos
        List<Producto> productos = service.listar();

        // Lógica de Condición y Configuración de Respuesta
        // Inicializa el Content-Type para HTML por defecto (se cambiará si es XLS)
        resp.setContentType("text/html;charset=UTF-8");

        // Obtiene la parte del path del Servlet solicitada (ej: /productos.xls o /productos.html)
        String servletPath=req.getServletPath();

        // Determina si la solicitud es para exportar a XLS
        boolean esXls=servletPath.endsWith(".xls");

        // Bloque de configuración si la solicitud es para XLS
        if (esXls){
            // 1. Cambia el Content-Type a formato Excel (application/vnd.ms-excel)
            resp.setContentType("application/vnd.ms-excel");
            // 2. Configura el encabezado Content-Disposition para forzar la descarga con nombre 'productos.xls'
            resp.setHeader("Content-Disposition", "attachment; filename=productos.xls");
        }

        // Generación de la Tabla y Plantilla
        // Usa un bloque try-with-resources para asegurar el cierre del PrintWriter
        try (PrintWriter out = resp.getWriter()) {

            // Si NO es XLS, imprime el esqueleto de la página HTML
            if (!esXls) {
                // Creo la plantilla html
                out.print("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"utf-8\">");
                out.println("<title>Listado de Productos</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Listado de productos</h1>");

                // Enlace para exportar a XLS (usa el Context Path de la aplicación)
                out.println("<p><a href=\"" + req.getContextPath() + "/productos.xls" + "\">exportar a excel</a></p>");

                // Enlace para exportar a JSON (usa el Context Path de la aplicación)
                out.println("<p><a href=\"" + req.getContextPath() + "/productos.json" + "\">mostrar json</a></p>");
            }

            // Inicio de la tabla (Esta parte es común para HTML y XLS)
            out.println("<table>");
            // Fila de encabezados de la tabla
            out.println("<tr>");
            out.println("<th>id</th>");
            out.println("<th>nombre</th>");
            out.println("<th>tipo</th>");
            out.println("<th>precio</th>");
            out.println("</tr>");

            // Itera sobre la lista de productos para imprimir cada fila (<tr>)
            productos.forEach(p->{
                out.println("<tr>");
                out.println("<td>"+p.getId()+"</td>");
                out.println("<td>"+p.getNombre()+"</td>");
                out.println("<td>"+p.getTipo()+"</td>");
                out.println("<td>"+p.getPrecio()+"</td>");
                out.println("</tr>");
            });
            out.println("</table>"); // Fin de la tabla

            // Si NO es XLS, cierra las etiquetas del HTML
            if (!esXls) {
                out.println("</body>");
                out.println("</html>");
            }
        }
    }
}