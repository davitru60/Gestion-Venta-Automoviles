import conexion.ConexionBD
import dao.AutomovilImpDAO
import dao.ClienteImpDAO
import dao.VentaImpDAO
import no_dao.Automovil
import no_dao.AutomovilFuncionalidades
import no_dao.Cliente
import java.lang.NumberFormatException

fun main(){
    var conexion= ConexionBD()


    if(conexion !=null){
        println("Conectado con exito")
        menuGeneral()
    }else{
        println("Error al conectar")
    }


}

fun menuGeneral(){
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

fun menuAutomoviles(automovilDAO: AutomovilImpDAO) {
    val automovilFuncionalidades = AutomovilFuncionalidades()
    var seleccion: Int
    var str: String
    do {
        println("\n")
        println("¿Qué deseas hacer?")
        println("1. Insertar automovil")
        println("2. Obtener automoviles por rango de precios")
        println("3. Obtener automovil por marca")
        println("4. Obtener todos los automoviles")
        println("5. Actualizar precio de un automovil")
        println("6. Eliminar automovil")

        str = readln()
        seleccion =
            try {
                str.toInt()
            } catch (e: Exception) {
                -1
            }
        when (seleccion) {
            1 -> automovilFuncionalidades.insertarAutomovil(automovilDAO)
            2 -> automovilFuncionalidades.actualizarPrecioAutomovil(automovilDAO)
            3 -> automovilFuncionalidades.obtenerAutomovilesPorRangoDePrecio(automovilDAO)
            4 -> automovilFuncionalidades.obtenerAutomovilesPorMarca(automovilDAO)
            5 -> automovilFuncionalidades.obtenerTodosLosAutomoviles(automovilDAO)
            6 -> automovilFuncionalidades.eliminarAutomovil(automovilDAO)
            else -> println("Opcion incorrecta")
        }
    } while (seleccion != 0)

}

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

fun menuClientes(clienteDAO: ClienteImpDAO){
    var seleccion: Int
    var str: String
    var cliente=ClienteFuncionalidad()
    do {
        println("¿Qué deseas hacer con el cliente?")
        println("1. Añadir un cliente")
        println("2. Eliminar un cliente")
        println("3. Ver la lista de clientes")
        println("4. Buscar un cliente")

        str = readln()
        seleccion =
            try {
                str.toInt()
            } catch (e: Exception) {
                -1
            }
        when(seleccion) {
            1 -> cliente.aniadirCliente(clienteDAO)
            2 -> cliente.eliminarCliente(clienteDAO)
            3 -> cliente.verClientes(clienteDAO)
            4 -> cliente.buscarCliente(clienteDAO)

            else -> println("Opcion incorrecta")
        }
    } while (seleccion != 0)
}