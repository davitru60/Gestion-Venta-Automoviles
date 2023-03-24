package funcionalidades

import dao.VentaImpDAO
import no_dao.Venta

class VentasFuncionalidades {
    private val ventaDAO= VentaImpDAO()
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