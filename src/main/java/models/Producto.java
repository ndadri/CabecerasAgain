package models;

/**
 * Clase que representa la entidad "Producto" en el sistema.
 * Es un POJO (Plain Old Java Object) que encapsula los datos.
 * Esta clase es crucial para la serialización a JSON/XLS y la vista HTML.
 */
public class Producto {

    // Atributos privados (campos) que definen las características del producto
    private Long id;
    private String nombre;
    private String tipo;
    private double precio;

    /**
     * Constructor por defecto (necesario para frameworks como Jackson o Servlets
     * que instancian la clase sin argumentos).
     */
    public Producto() {
    }

    /**
     * Constructor con argumentos, usado para inicializar el objeto con todos sus valores
     * (como se ve en ProductoServiceImplement).
     */
    public Producto(Long id, String nombre, String tipo, double precio) {
        this.id = id; // Inicializa el campo id
        this.nombre = nombre; // Inicializa el campo nombre
        this.tipo = tipo; // Inicializa el campo tipo
        this.precio = precio; // Inicializa el campo precio
    }

    // Métodos Accesores (Getters y Setters)
    // Estos métodos son el estándar para acceder/modificar los atributos privados.

    public Long getId() {
        // Metodo 'getter' para obtener el valor del ID
        return id;
    }
    ///id= 3 tengo acceso al 3 // Este comentario indica que el getter permite acceder al valor

    public void setId(Long id) {
        // Metodo 'setter' para establecer el valor del ID
        this.id = id;
    }

    public String getNombre() {
        // Getter para el nombre
        return nombre;
    }

    public void setNombre(String nombre) {
        // Setter para el nombre
        this.nombre = nombre;
    }

    public String getTipo() {
        // Getter para el tipo
        return tipo;
    }

    public void setTipo(String tipo) {
        // Setter para el tipo
        this.tipo = tipo;
    }

    public double getPrecio() {
        // Getter para el precio
        return precio;
    }

    public void setPrecio(double precio) {
        // Setter para el precio
        this.precio = precio;
    }
}