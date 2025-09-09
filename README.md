PROYECTO CONCEPTO FUNDAMENTALES DE PROGRAMACIÓN 


INTRODUCCIÓN


Nuestro sistema de programación nos ayudará a facilitar la contabilización de las ventas realizadas por un equipo comercial basados en unas referencias de modelos de celulares de las marcas Apple y Samsung, lo que nos permitirá recaudar el valor de las ventas totales, sobre que modelos y marca y lo que cada vendedor vendió en un periodo de tiempo determinado. 

El enlace del repositorio donde se encuentra el proyecto alojado es: 


ARREGLOS USADOS Y TIPOS DE DATOS USADOS.

Cadenas usadas (String):
•	Tipo de documento – String [] CLASE_ID = {}: 
Cédula de ciudadanía o cédula de extranjería.
•	Número de cédula  - String [] NUMERO_ID = {} : 
Números de identificación de cada venderdor.
•	Nombre del vendedor -  String [] NOMBRE_VENDEROR = {}: 
Primer nombre de cada vendedor. Para esta entrega serían 10 vendedores. 
•	Apellido del vendedor - String [] APELLIDO_VENDEROR = {}: 
Primeros apellidos de cada vendedor, para esta entrega serían 10 vendedores. 
•	Marca de celular – String [] NOMBRE_PRODUCTO = {}: 
Dos marcas de celulares: Apple y Samsung. Se escribieron cinco modelos diferentes. 


Dato nativo en java de 64bits (Double)
•	Precio – String [] PRECIO_PRODUCTO = {}: 
Precios de cada uno de los modelos de las marcas mencionadas. 



LIBRERÍAS USADAS

•	java.io.PrintWriter: Esta librería se encarga de escribir texto legible dentro de un archivo.
•	java.nio.charset.StandardCharsets: Esta librería ofrece ayuda para codificar caracteres en formato UTF-8, el cual se usa ampliamente en lenguajes web, procesadores de texto en varios idiomas.
•	java.util.Random: Esta librería se encarga de general números pseudoaleatorios.


MÉTODOS IMPLEMENTADOS

•	Main{}: Crea los archivos productos.csv, vendedores.csv y el informe individual de cada vendedor en formato .cvs.

•	createProductsFile(){}: Crea el archivo productos.csv, donde se muestran todos los productos a la venta y sus precios. El archivo retornado cuenta con tres encabezados: productId;nombre;precio.

•	createSalesManInfoFile(){}: Crea el archivo vendedores.csv, donde se muestran los datos de todos los vendedores. El archivo retornado cuenta con tres encabezados: claseId;id;nombre;apellido.


•	createSalesMenFile(){}: crea el informe individual de ventas de cada vendedor. El archivo se nombra con el tipo de documento del vendedor, el número del documento y finaliza con la extensión .csv. El archivo retornado cuenta con dos encabezados inciales: claseId;id. Del mismo modo, el archivo retornado tiene un cálculo de la cantidad de productos y el monto total de ventas de cada vendedor. Esta parte tiene los siguientes encabezados: productoId;cantidad;precioUnitario;totalVendido.
