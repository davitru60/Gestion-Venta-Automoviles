package funcionalidades

import dao.VentaImpDAO
import entidades.Venta

/**
Clase que contiene las funciones que manejan las ventas mediante el uso del objeto de la clase [VentaImpDAO].
 */
class VentasFuncionalidades {

    private val ventaDAO= VentaImpDAO()

    /**
    Función que borra una venta mediante la obtención del ID por medio de entrada de datos por teclado.
    @return Boolean que indica si se borró la venta o no.
     */
    fun borrarVenta() {
        var id: Int = 0
        println("Introduce el ID:")
        try {
            id = readln().toInt()
        } catch (e: Exception) {
            println("ERROR: Tipo de dato introducido incorrecto")
        }

        val borrado = ventaDAO.borrarVenta(id)
        println("Venta borrada: $borrado")
    }

    /**
    Función que actualiza una venta mediante la obtención de los datos necesarios por medio de entrada de datos por teclado.
    @return Boolean que indica si se actualizó la venta o no.
    */
    fun actualizarVentas() {
        var id: Int = 0
        var automovil_id: Int = 0
        var cliente_id: Int = 0
        var fecha: String = ""
        var precio_venta: Double = 00.00

        try {

            println("Introduce el ID:")
            id = readln().toInt()

            println("Introduce el ID del automóvil:")
            automovil_id = readln().toInt()

            println("Introduce el ID del cliente:")
            cliente_id = readln().toInt()

            println("Introduce la fecha de la venta en formato YYYY-MM-DD")
            fecha = readln()

            println("Introduce el precio de la venta:")
            precio_venta = readln().toDouble()

        } catch (e: Exception) {
            println("ERROR: Datos introducidos incorrectos")
        }

        val ventaActualizar = Venta(
            id = id,
            automovil_id = automovil_id,
            cliente_id = cliente_id,
            fecha = fecha,
            precio_venta = precio_venta,
        )

        val actualizado = ventaDAO.actualizarVentas(ventaActualizar)
        println("Venta actualizada: $actualizado")
    }

    /**
    Función que obtiene todas las ventas existentes en la base de datos.
     */
    fun obtenerTodasLasVentas() {
        val todasLasVentas = ventaDAO.obtenerTodasLasVentas()
        println("Todas las ventas: $todasLasVentas")

        for (venta in todasLasVentas) {
            println("ID: ${venta.id}")
            println("Automóvil ID: ${venta.automovil_id}")
            println("Cliente ID: ${venta.cliente_id}")
            println("Fecha: ${venta.fecha}")
            println("Precio de venta: ${venta.precio_venta}")
            println()
        }

    }

    /**
    Función que obtiene una venta específica mediante la entrada de un ID por teclado.
     */
    fun obtenerVentaMedianteID() {

        var id: Int = 0

        println("Introduce el ID:")
        try {
            id = readln().toInt()
        } catch (e: Exception) {
            println("ERROR: Tipo de dato introducio incorrecto")
        }

        val ventaObtenida = ventaDAO.obtenerVentaMedianteID(id)
        if (ventaObtenida != null) {
            println("Venta obtenida: $ventaObtenida")
        } else {
            println("No se encontró ninguna venta con el ID especificado")
        }
    }

    /**
    Inserta una lista de ventas en la base de datos.

    @return el número de ventas insertadas exitosamente en la base de datos.
    */
    fun insertarListaVentas() {
        var automovil_id: Int = 0
        var cliente_id: Int = 0
        var fecha: String = ""
        var precio_venta: Double = 00.00

        val ventas = ArrayList<Venta>()
        var cont: Int = 0

        println("Inserte el número de ventas que quiera introducir")
        try {
            cont = readln().toInt()
        } catch (e: Exception) {
            println("ERROR: El dato introducido es incorrecto.")
        }

        while (cont > 0) {
            try {

                println("Introduce el ID del automóvil:")
                automovil_id = readln().toInt()

                println("Introduce el ID del cliente:")
                cliente_id = readln().toInt()

                println("Introduce la fecha de la venta en formato YYYY-MM-DD")
                fecha = readln()

                println("Introduce el precio de la venta:")
                precio_venta = readln().toDouble()

            } catch (e: Exception) {
                println("ERROR: Datos introducidos incorrectos")
            }

            val venta = Venta(
                automovil_id = automovil_id,
                cliente_id = cliente_id,
                fecha = fecha,
                precio_venta = precio_venta,
            )
            ventas.add(venta)

            cont--

        }


        val exito = ventaDAO.insertarLista(ventas)
        println("Ventas insertadas: $exito")

    }
}