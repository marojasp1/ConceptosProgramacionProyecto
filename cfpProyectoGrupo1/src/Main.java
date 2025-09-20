import java.io.*;

import java.nio.file.*;

import java.util.*;

import java.util.stream.Collectors;

import java.util.function.Function;



//Esta clase colecciona los artículos de venta, tomando el archivo productos.csv como entrada.
class Producto {

    String id, nombre; double precio; int cantidadVendida = 0;

    Producto(String id, String n, double p) { this.id = id; this.nombre = n; this.precio = p; }

    public String getId() { return id; }

}
 //Esta clase construye los vendedores, cuyos datos son recuperados usando el archivo vendedores.csv
class Vendedor {

    String tipoDoc, numDoc, nombres, apellidos; double ventasTotales = 0.0;

    Vendedor(String td, String nd, String n, String a) { this.tipoDoc = td; this.numDoc = nd; this.nombres = n; this.apellidos = a; }

    public String getNumDoc() { return numDoc; }

}



public class Main {

    public static void main(String[] args) {

        

        try {

            System.out.println("Iniciando...");

            
            //Almacena los objetos de Producto.
            Map<String, Producto> mapaProductos = cargarDatos("productos.csv", linea -> {

                String[] d = linea.split(";"); return new Producto(d[0], d[1], Double.parseDouble(d[2]));

            }, Producto::getId);

            
            //Almacena objetos Vendedor.
            Map<String, Vendedor> mapaVendedores = cargarDatos("vendedores.csv", linea -> {

                 String[] d = linea.split(";"); return new Vendedor(d[0], d[1], d[2], d[3]);

            }, Vendedor::getNumDoc);

            //Recorre el contenido de los informes individuales de cada vendedor
            Files.walk(Paths.get("."))

                .filter(path -> path.getFileName().toString().startsWith("vendedor_"))

                .forEach(path -> procesarArchivoVenta(path, mapaProductos, mapaVendedores));


            generarReportes(mapaVendedores, mapaProductos);

            

            System.out.println("¡Reportes generados!");

        } catch (Exception e) { System.err.println("ERROR: " + e.getMessage()); }

    }


    private static  <T, K> Map< K, T> cargarDatos(String archivo, Function<String, T> constructor, Function<T, K> getKey) throws IOException {

        //Procesa las líneas contenidas en los archivos reporte_productos.csv y reporte_vendedores.csv
  try (java.util.stream.Stream<String> lines = Files.lines(Paths.get(archivo))) {
        return lines
            .skip(1) //Salta el encabezado de los .csv.
            .map(String::trim)
            .filter(l -> !l.isEmpty())
            .map(constructor)
            .collect(Collectors.toMap(getKey, Function.identity()));
    }



    }

    

    private static void procesarArchivoVenta(Path archivo, Map prods, Map vends) {

        //Procesa los datos recogidos y procesa totales y orden decendente de ventas para cada informe.

        try {

            List<String> lineas = Files.readAllLines(archivo);
            if (lineas.size() < 4) return;

            String[] cabeceraVendedor = lineas.get(1).split(";");
            if (cabeceraVendedor.length < 2) return;

            String idVendedor = cabeceraVendedor[1].trim();
            Vendedor vendedor = (Vendedor) vends.get(idVendedor);
            if (vendedor == null) {
                System.err.println("ADVERTENCIA: vendedor no encontrado: " + idVendedor + " en " + archivo.getFileName());
                return;
            }

            for (int i = 3; i < lineas.size(); i++) { //Salta los encabezados de los informes individuales de los vendedores
                
                String linea = lineas.get(i).trim();
                if (linea.isEmpty()) continue;                

                String[] datos = lineas.get(i).split(";");
                if (datos.length < 2) continue;

                String prodIdStr = datos[0].trim();
                String cantStr   = datos[1].trim();

                if (!cantStr.matches("-?\\d+")) continue;

                Producto producto = (Producto) prods.get(prodIdStr);

                if (producto == null) {
                System.err.println("ADVERTENCIA: producto no encontrado: " + prodIdStr + " en " + archivo.getFileName());
                continue;
                }

                int cantidad = Integer.parseInt(cantStr);

                if (producto != null) {

                    vendedor.ventasTotales += producto.precio * cantidad;

                    producto.cantidadVendida += cantidad;

                }

            }

        } catch (Exception e) { System.err.println("ADVERTENCIA: " + archivo.getFileName()); }

    }

    
    //Se crea los archivos con la informacion ordenada
    private static void generarReportes(
            Map<String, Vendedor> mapaVendedores,
            Map<String, Producto> mapaProductos) throws IOException {

        List<Vendedor> vendedoresOrdenados = mapaVendedores.values().stream()
            .sorted(Comparator.comparingDouble((Vendedor v) -> v.ventasTotales).reversed())
            .collect(Collectors.toList());

        try (PrintWriter writer = new PrintWriter("reporte_vendedores.csv")) {

            writer.println("NombreVendedor;TotalVendido");//Encabezado

            for (Vendedor v : vendedoresOrdenados) {

                writer.printf("%s %s;%.2f\n", v.nombres, v.apellidos, v.ventasTotales);

            }

        }

        List<Producto> productosOrdenados = mapaProductos.values().stream()
            .sorted(Comparator.comparingInt((Producto p) -> p.cantidadVendida).reversed())
            .collect(Collectors.toList());

        try (PrintWriter writer = new PrintWriter("reporte_productos.csv")) {
            
            writer.println("NombreProducto;TotalVendido");//Encabezado

            for (Producto p : productosOrdenados) {

                writer.printf("%s;%.2f\n", p.nombre, p.precio);

            }

        }

    }

}

