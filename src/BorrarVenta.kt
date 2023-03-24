class BorrarVenta {
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
}