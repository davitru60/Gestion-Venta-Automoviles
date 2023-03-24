import dao.ClienteImpDAO
import funcionalidades.VentasFuncionalidades
import funcionalidades.AutomovilFuncionalidades
import funcionalidades.ClienteFuncionalidades

fun main(){
    menuGeneral()
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
            1 -> menuAutomoviles()
            2 -> menuClientes()
            3 -> menuVentas()
            4 -> break

            else -> println("Opcion incorrecta")
        }
    } while (seleccion != 0)
}

fun menuAutomoviles() {
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
            1 -> automovilFuncionalidades.insertarAutomovil()
            2 -> automovilFuncionalidades.actualizarPrecioAutomovil()
            3 -> automovilFuncionalidades.obtenerAutomovilesPorRangoDePrecio()
            4 -> automovilFuncionalidades.obtenerAutomovilesPorMarca()
            5 -> automovilFuncionalidades.obtenerTodosLosAutomoviles()
            6 -> automovilFuncionalidades.eliminarAutomovil()
            else -> println("Opcion incorrecta")
        }
    } while (seleccion != 0)

}

fun menuVentas(){
    var s:Int=0
    val venta= VentasFuncionalidades()
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

fun menuClientes(){
    var seleccion: Int
    var str: String
    val cliente= ClienteFuncionalidades()
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
            1 -> cliente.aniadirCliente()
            2 -> cliente.eliminarCliente()
            3 -> cliente.verClientes()
            4 -> cliente.buscarCliente()

            else -> println("Opcion incorrecta")
        }
    } while (seleccion != 0)
}