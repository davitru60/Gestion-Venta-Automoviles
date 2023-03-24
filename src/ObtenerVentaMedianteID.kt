class ObtenerVentaMedianteID {
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
}