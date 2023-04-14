# ED_Automoviles
<p align="center">
  <img src="https://user-images.githubusercontent.com/84265707/231301643-492a1d09-fbb8-4e31-8c1a-ec8a07d59402.png" alt="Descripción de la imagen" style="display: block; margin: auto;" width="700" height="400">
</p>

El proyecto al ejecutarse se genera un menú general para moverse un las distintas funciones

# Integrantes del proyecto
- David Trujillo Carrero
- Laura María Pedraza Gomez
- Raúl Gutierrez Merino

# Para más información sobre nuestro proyecto
https://github.com/davitru60/ED_Automoviles/wiki

Link de trello: https://trello.com/b/FxAfmaI7/edautomoviles

# Obtencion del código

`clone https://github.com/davitru60/ED_Automoviles.git`


# Explicación de los elementos principales del programa
## Automoviles
La clase AutomovilFuncionalidades tiene métodos para realizar diversas operaciones en la base de datos, como insertar automóviles, obtener automóviles por rango de precio, obtener automóviles por marca y obtener todos los automóviles. También tiene métodos auxiliares para comprobar si se han obtenido resultados de la base de datos y para verificar el rango de precios.

El método insertarAutomovil lee un archivo de texto que contiene información sobre automóviles y llama al método comprobarInsercionAutomovil para insertar cada uno de ellos en la base de datos.

El método comprobarInsercionAutomovil inserta un objeto Automovil en la base de datos y muestra un mensaje de éxito o fracaso en consecuencia.

El método obtenerAutomovilesPorRangoDePrecio pide al usuario que ingrese un rango de precios y llama al método comprobarRangoDePrecios para verificar que el rango ingresado sea válido. Luego llama al método obtenerAutomovilPorRangoDePrecio de la clase AutomovilImpDAO para obtener todos los automóviles de la base de datos que se encuentren en el rango de precios especificado y llama al método comprobarExistenciaAutomoviles para verificar si se han obtenido resultados de la base de datos.

El método obtenerAutomovilesPorMarca pide al usuario que ingrese una marca y llama al método obtenerAutomovilesPorMarca de la clase AutomovilImpDAO para obtener todos los automóviles de la base de datos que corresponden a esa marca y llama al método comprobarExistenciaAutomoviles para verificar si se han obtenido resultados de la base de datos.

El método obtenerTodosLosAutomoviles llama al método obtenerTodosLosAutomoviles de la clase AutomovilImpDAO para obtener todos los automóviles de la base de datos y llama al método comprobarExistenciaAutomoviles para verificar si se han obtenido resultados de la base de datos.

El método comprobarRangoDePrecios pide al usuario que ingrese un rango de precios y verifica si el límite inferior es menor o igual que el límite superior. Retorna un objeto Pair con los límites del rango de precios ingresado.

## Cliente
"buscarCliente": Esta función solicita al usuario que ingrese el identificador del cliente y utiliza el método "buscarCliente" de la clase "ClienteImpDAO" para buscar el cliente correspondiente en el sistema.

"verClientes": Esta función obtiene la lista de todos los clientes registrados en el sistema utilizando el método "verListaCliente" de la clase "ClienteImpDAO" y luego llama al método "comprobarExistenciaClientes" para mostrar los clientes en pantalla.

"eliminarCliente": Esta función solicita al usuario que ingrese el identificador del cliente y utiliza el método "eliminarCliente" de la clase "ClienteImpDAO" para eliminar el cliente correspondiente del sistema.

"aniadirCliente": Esta función solicita al usuario que ingrese el nombre, apellido, email y teléfono del nuevo cliente y utiliza el método "aniadirCliente" de la clase "ClienteImpDAO" para agregar el nuevo cliente al sistema.

"Volver al menú": Regresa al usuario el menú principal

## Ventas
La clase utiliza una instancia de la clase VentaImpDAO del paquete dao para realizar operaciones de base de datos relacionadas con las ventas. También utiliza la clase Venta del paquete entidades para representar objetos de ventas.

La clase VentasFuncionalidades define cinco funciones públicas:

"borrarVenta()": Esta función pide al usuario un ID de venta y luego utiliza el objeto ventaDAO para eliminar la venta correspondiente de la base de datos.

"actualizarVentas()": Esta función solicita al usuario información sobre una venta, crea un objeto Venta y utiliza el objeto ventaDAO para actualizar la venta correspondiente en la base de datos.

"obtenerTodasLasVentas()": Esta función utiliza el objeto ventaDAO para obtener todas las ventas de la base de datos y las imprime en la consola.

"obtenerVentaMedianteID()": Esta función solicita al usuario un ID de venta y utiliza el objeto ventaDAO para buscar la venta correspondiente en la base de datos. Si la venta se encuentra, se imprime en la consola.

"insertarListaVentas()": Esta función solicita al usuario información sobre varias ventas y crea objetos Venta para cada una. Luego utiliza el objeto ventaDAO para insertar las ventas en la base de datos.

