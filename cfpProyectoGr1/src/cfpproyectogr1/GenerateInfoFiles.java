package cfpproyectogr1;

/**
*
* @author Grupo 1 - Conceptos fundamentales de programamcion
* Daniela Gonzalez Gomez
* Orlando Gomez Fajardo
* Miguel Alejandro Rojas Perez
*
**/
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Random;

public class GenerateInfoFiles {

//Datos de los vendedores, Ids y prodcutos ingresados dentro del codigo
    
    private static final String[] NUMERO_ID = {
        "34567543", "89765345", "34523", "1044567654", "9876541",
        "65412987", "102652654", "65465", "76545767", "8765469"
    };

    private static final String[] NOMBRE_VENDEDOR = {
        "CLAUDIO", "DANIEL", "ESTEBAN", "DIANA", "GERMAN",
        "ALBERTO", "LAURA", "DAYANA", "FRANCY", "MIGUEL"
    };

    private static final String[] APELLIDO_VENDEDOR = {
        "ORTEGA", "RODRIGUEZ", "CHAVEZ", "GIRALDO", "AYALA",
        "SINISTERRA", "QUNTERO", "OVALLE", "CHAPARRO", "MARTINEZ"
    };

    private static final String[] NOMBRE_PRODUCTO = {
        "samsung galaxy a16 256gb 5g", "samsung galaxy s25 256gb 5g",
        "samsung galaxy s24 fe 512gb 5g", "samsung galaxy a56 256gb",
        "samsung galaxy a26 256gb 5g", "iphone 16 128gb 5g",
        "iphone 13 128gb 5g", "iphone 14 128gb 5g",
        "iphone 16 plus 128gb 5g", "iphone 15 128gb 5g"
    };

    private static final double[] PRECIO_PRODUCTO = {
        799900.0, 4499950.0, 3199950.0, 1899950.0, 899900.0,
        4591960.0, 2599960.0, 3409960.0, 5162960.0, 3979960.0
    };

//Estos son los unicos datos que van a ser elegidos al azar a partir de un arreglo
//Cada vendedor tendra una clase de documento de identificacion asignado al azar tomado del arreglo CLASE_ID
    
    private static final String[] CLASE_ID = {"CC", "CE", "TI"};
    private static final Random RAND = new Random(); 
    
//Esta variable muestra los ids de los productos vendidos por el vendedor por cada linea
//Entre mas numero de lines tenga, mas productos ha vendido el vendedor, por ahora dejamos solo una linea

    private static final int LINEAS_VENTA_POR_VENDEDOR = 1;
    
    
//Metodo que crea los archivos createProductFile y createSalesManInfoFile llamando cada metodo desarrollado abajo

    public static void main(String[] args) {
        try {
            System.out.println("Iniciando generación de archivos...");


            createProductsFile();
            createSalesManInfoFile();

            System.out.println("¡Archivos generados exitosamente!");
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

//Metodo que crea el archivo createProductFile
    private static void createProductsFile() throws Exception {
        try (PrintWriter writer = new PrintWriter("productos.csv", StandardCharsets.UTF_8)) {
            writer.println("productId;nombre;precio");
            for (int i = 0; i < NOMBRE_PRODUCTO.length; i++) {
                int productId = i + 1;
                double precioUnitario = PRECIO_PRODUCTO[i];
                writer.println(productId + ";" + NOMBRE_PRODUCTO[i] + ";" + precioUnitario);
            }
        }
    }


    private static void createSalesManInfoFile() throws Exception {
        try (PrintWriter writer = new PrintWriter("vendedores.csv", StandardCharsets.UTF_8)) {
            writer.println("claseId;id;nombre;apellido");

            for (int i = 0; i < NUMERO_ID.length; i++) {
                String id = NUMERO_ID[i];               
                String nombre = NOMBRE_VENDEDOR[i];     
                String apellido = APELLIDO_VENDEDOR[i]; 

 
                String claseIdAsignado = CLASE_ID[RAND.nextInt(CLASE_ID.length)];


                writer.println(claseIdAsignado + ";" + id + ";" + nombre + ";" + apellido);


                createSalesMenFile(claseIdAsignado, id, LINEAS_VENTA_POR_VENDEDOR);
            }
        }
    }

    private static void createSalesMenFile(String claseIdAsignado, String id, int lineasVenta) throws Exception {
        String fileName = "vendedor_" + id + ".csv";

        try (PrintWriter writer = new PrintWriter(fileName, StandardCharsets.UTF_8)) {
            // Identificación del vendedor
            writer.println("claseId;id");
            writer.println(claseIdAsignado + ";" + id);

            writer.println("productoId;cantidad;precioUnitario;totalVendido");

            for (int i = 0; i < lineasVenta; i++) {

                int productIndex = i % NOMBRE_PRODUCTO.length;
                int productId = productIndex + 1;


                int cantidad = RAND.nextInt(10) + 1;

                double precioUnit = PRECIO_PRODUCTO[productIndex];
                double total = precioUnit * cantidad;



                writer.println(productId + ";" + cantidad + ";" + precioUnit + ";" + total);
            }
        }
    }
}
