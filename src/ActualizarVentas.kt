import no_dao.Venta
class ActualizarVentas {
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
}