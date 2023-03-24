import no_dao.Venta

class InsertarListaVentas () {
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