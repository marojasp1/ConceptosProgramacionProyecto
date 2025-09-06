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
import java.util.Random;

public class GenerateInfoFiles {
     // Estos arreglos contienen los datos  con datos de prueba para generar información aleatoria

    private static final String[] CLASE_ID = {"CC", "CE", "TI"};

    private static final String[] NUMERO_ID = {"34567543", "89765345", "34523", "1044567654", "9876541", "65412", "102652654", "65465", "76545767", "8765469"};

    private static final String[] NOMBRE_VENDEDOR = {"CLAUDIO", "DANIEL", "ESTEBAN", "DIANA", "GERMAN", "ALBERTO", "LAURA", "DAYANA", "FRANCY", "MIGUEL"};

    private static final String[] APELLIDO_VENDEDOR = {"ORTEGA", "RODRIGUEZ", "CHAVEZ", "GIRALDO", "AYALA", "SINISTERRA", "QUNTERO", "OVALLE", "CHAPARRO", "MARTINEZ"};

    private static final String[] NOMBRE_PRODUCTO = {"samsung galaxy a16 256gb 5g", "samsung galaxy s25 256gb 5g", "samsung galaxy s24 fe 512gb 5g", "samsung galaxy a56 256gb", "samsung galaxy a26 256gb 5g", "iphone 16 128gb 5g","iphone 13 128gb 5g", "iphone 14 128gb 5g", "iphone 16 plus 128gb 5g", "iphone 15 128gb 5g"};

    private static final double[] PRECIO_PRODUCTO = {799900.0, 4499950.0, 3199950.0, 1899950.0, 899900.0, 4591960.0, 2599960.0, 3409960.0, 5162960.0, 3979960.0};


    public static void main(String[] args) {

        try {

            System.out.println("Iniciando generación de archivos...");

            createProductsFile(NOMBRE_PRODUCTO.length);

            createSalesManInfoFile(10); // Crear 10 vendedores

            System.out.println("¡Archivos generados exitosamente!");

        } catch (Exception e) { System.err.println("ERROR: " + e.getMessage()); }

    }


    public static void createProductsFile(int productsCount) throws Exception {

        try (PrintWriter writer = new PrintWriter("productos.csv", "UTF-8")) {

            for (int i = 0; i < productsCount; i++) {

                writer.println((i + 1) + ";" + NOMBRE_PRODUCTO[i] + ";" + PRECIO_PRODUCTO[i]);

            }

        }

    }

    

    public static void createSalesManInfoFile(int salesmanCount) throws Exception {

        Random rand = new Random();
        
        String fileName = "vendedor_" + id + ".csv";

        try (PrintWriter writer = new PrintWriter("vendedores.csv", "UTF-8")) {

            for (int i = 0; i < salesmanCount; i++) {
                
                String[] id = NUMERO_ID;
                
                String[] nombre = NOMBRE_VENDEDOR;

                writer.println(CLASE_ID[rand.nextInt(CLASE_ID.length)] + ";" + NUMERO_ID[i] +  ";" + NOMBRE_VENDEDOR[i] + ";" + APELLIDO_VENDEDOR[i]);

                createSalesMenFile(rand.nextInt(4) + 2, nombre, id);

            }

        }

    }


    public static void createSalesMenFile(int randomSalesCount, String[] name, String[] id) throws Exception {

        Random rand = new Random();

        String fileName = "vendedor_" + id + ".csv";

        try (PrintWriter writer = new PrintWriter(fileName, "UTF-8")) {

            writer.println(CLASE_ID + ";" + id);

            for (int i = 0; i < randomSalesCount; i++) {

                writer.println((rand.nextInt(NOMBRE_PRODUCTO.length) + 1) + ";" + (rand.nextInt(10) + 1) + ";");

            }

        }

    }

}
    

