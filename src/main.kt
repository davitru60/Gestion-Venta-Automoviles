import conexion.ConexionBD
import dao.AutomovilImpDAO
import dao.ClienteImpDAO
import dao.VentaImpDAO
import no_dao.Automovil
import no_dao.Cliente
import java.lang.NumberFormatException

fun main(){
    var conexion= ConexionBD()
    val automovilDAO=AutomovilImpDAO()

    if(conexion !=null){
        println("Conectado con exito")
        menuGeneral(automovilDAO)
    }else{
        println("Error al conectar")
    }


}
val ventaDAO = VentaImpDAO()


fun menuVentas(){
    var s:Int=0
    var venta=MenuVentas()
    println()
    println("¿Qué opción de venta desea?")
    println("1. Insertar ventas")
    println("2. Obtener los datos de alguna venta.")
    println("3. Obtener los datos de todas las ventas")
    println("4. Actualizar una venta.")
    println("5. Borrar una venta")
    try {
        s = readln().toInt()
    } catch (e:Exception){
        println("ERROR: La opción introducida es incorrecta")
    }
    when (s){
        1->venta.insertarListaVentas()
        2->venta.obtenerVentaMedianteID()
        3->venta.obtenerTodasLasVentas()
        4->venta.actualizarVentas()
        5->venta.borrarVenta()
    }
}


fun menuGeneral(automovilDAO: AutomovilImpDAO){
    var seleccion: Int
    var str: String
    do {
        println("¿Qué deseas hacer?")
        println("1. Realizar acciones con los automoviles")
        println("2. Realizar acciones con los clientes")
        println("3. Realizar acciones con las ventas")
        println("4. Salir del programa")

        val clienteDAO= ClienteImpDAO()
        str = readln()
        seleccion =
            try {
                str.toInt()
            } catch (e: Exception) {
                -1
            }

        when(seleccion) {
            1 -> menuAutomoviles(automovilDAO)
            2 -> menuClientes(clienteDAO)
            3 -> menuVentas()
            4 -> break

            else -> println("Opcion incorrecta")
        }
    } while (seleccion != 0)
}

