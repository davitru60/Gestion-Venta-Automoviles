class ObtenerTodasLasVentas {
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
}