package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

// Mapea este Servlet a la URL "/cabeceras-request".
@WebServlet("/cabeceras-request")
public class CabecerasHttpRequestServlet extends HttpServlet {
    // Sobreescribe el mEtodo doGet para manejar peticiones GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Configura el tipo de contenido de la respuesta a HTML con codificación UTF-8
        resp.setContentType("text/html;charset=UTF-8");

        // Obtención de parámetros y datos de la Solicitud HTTP (Request)
        // Obtiene el mEtodo HTTP utilizado (ej: GET, POST)
        String metodoHttp = req.getMethod();
        // Obtiene la parte URI de la solicitud (ej: /nombre-app/cabeceras-request)
        String requestUri = req.getRequestURI();
        // Obtiene la URL completa de la solicitud (como String)
        String requestUrl = req.getRequestURL().toString();
        // Obtiene el path del contexto (nombre de la aplicación, ej: /nombre-app)
        String contextPath = req.getContextPath();
        // Obtiene el path del Servlet (la parte mapeada, ej: /cabeceras-request)
        String servletPath = req.getServletPath();
        // Obtiene la dirección IP local del servidor
        String ip= req.getLocalAddr();
        // Obtiene el puerto local del servidor (ej: 8080)
        int port = req.getLocalPort();
        // Obtiene el esquema de protocolo (ej: http, https)
        String scheme = req.getScheme();
        // Obtiene el valor de la cabecera "Host" de la solicitud (nombre de dominio/IP del cliente)
        String host = req.getHeader("host");

        // Construye una URL completa usando el nombre del host
        String url=scheme+"://"+host+contextPath+servletPath;
        // Construye una URL completa usando la IP del servidor y el puerto local
        String url2=scheme+"://"+ip+":"+port+contextPath+servletPath;
        // Obtiene la dirección IP del cliente que realizó la solicitud
        String ipCLiente=req.getRemoteAddr();

        // Generación de la Respuesta HTML
        // Usa un bloque try-with-resources para asegurar que el PrintWriter se cierre automáticamente
        try (PrintWriter out = resp.getWriter()) {
            // Comienzo de la plantilla HTML
            out.print("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"utf-8\">");
            out.println("<title>Cabeceras Http Request</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Cabeceras HTTP Request!</h1>");
            out.println("<ul>"); // Inicia lista de propiedades

            // Imprime las propiedades de la solicitud obtenidas previamente
            out.println("<li>Obtenniendo el método: "+ metodoHttp+"</li>");
            out.println("<li>Request URI: "+ requestUri +"</li>");
            out.println("<li>Request URL: "+ requestUrl+"</li>");
            out.println("<li>Context Path: "+ contextPath+"</li>");
            out.println("<li>Servlet Path: "+ servletPath+ "</li>");
            out.println("<li>IP Servidor: "+ ip +"</li>");
            out.println("<li>Puerto Servidor: "+port+"</li>");
            out.println("<li>Esquema: "+ scheme+"</li>");
            out.println("<li>Host: "+ host+"</li>");
            out.println("<li>URL (Host): "+ url+"</li>");
            out.println("<li>URL (IP:Puerto): "+ url2+"</li>");
            out.println("<li>IP Cliente Remoto: "+ ipCLiente+"</li>");

            // Obtiene una enumeración con los nombres de todas las cabeceras HTTP enviadas por el cliente
            Enumeration<String> headerNames=req.getHeaderNames();
            // Itera sobre los nombres de las cabeceras
            while(headerNames.hasMoreElements()){
                // Obtiene el nombre de la cabecera actual
                String cabecera = headerNames.nextElement();
                // Imprime el nombre de la cabecera y su valor
                out.println("<li>" +cabecera + ": " +req.getHeader(cabecera)+"</li>");
            }
            out.println("</ul>"); // Fin de la lista
            out.println("</body>");
            out.println("</html>");
        }
    }
}