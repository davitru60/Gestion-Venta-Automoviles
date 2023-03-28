import funcionalidades.VentasFuncionalidades
import no_dao.funcionalidades.AutomovilFuncionalidades
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
    var seleccion=0
    var str: String
    do {
        println()
        println("¿Qué deseas hacer?")
        println("1. Insertar automovil")
        println("2. Obtener automoviles por rango de precios")
        println("3. Obtener automovil por marca")
        println("4. Obtener todos los automoviles")
        println("5. Actualizar precio de un automovil")
        println("6. Eliminar automovil")
        println("7. Volver al menu inicial")

        str = readln()
        seleccion=comprobarSeleccionMenu(str)

        when (seleccion) {
            1 -> automovilFuncionalidades.insertarAutomovil("./src/main/kotlin/Automoviles.txt")
            2 -> automovilFuncionalidades.obtenerAutomovilesPorRangoDePrecio()
            3 -> automovilFuncionalidades.obtenerAutomovilesPorMarca()
            4 -> automovilFuncionalidades.obtenerTodosLosAutomoviles()
            5 -> automovilFuncionalidades.actualizarPrecioAutomovil()
            6 -> automovilFuncionalidades.eliminarAutomovil()
            7 -> menuGeneral()
            else -> println("Opcion incorrecta")
        }
    } while (seleccion != 0)

}

fun menuClientes(){
    var seleccion=0
    var str: String
    val cliente= ClienteFuncionalidades()
    do {
        println()
        println("¿Qué deseas hacer con el cliente?")
        println("1. Añadir un cliente")
        println("2. Eliminar un cliente")
        println("3. Ver la lista de clientes")
        println("4. Buscar un cliente")
        println("5. Volver al menu inicial")

        str = readln()
        seleccion = comprobarSeleccionMenu(str)
        when(seleccion) {
            1 -> cliente.aniadirCliente()
            2 -> cliente.eliminarCliente()
            3 -> cliente.verClientes()
            4 -> cliente.buscarCliente()
            5 -> menuGeneral()

            else -> println("Opcion incorrecta")
        }
    } while (seleccion != 0)
}

fun menuVentas(){
    val venta= VentasFuncionalidades()
    var seleccion =0
    var str: String

    do{
        println()
        println("¿Qué opción de venta desea?")
        println("1. Insertar ventas")
        println("2. Obtener los datos de alguna venta.")
        println("3. Obtener los datos de todas las ventas")
        println("4. Actualizar una venta.")
        println("5. Borrar una venta")
        println("6. Volver al menu inicial")

        str = readln()
        seleccion=comprobarSeleccionMenu(str)

        when (seleccion){
            1->venta.insertarListaVentas()
            2->venta.obtenerVentaMedianteID()
            3->venta.obtenerTodasLasVentas()
            4->venta.actualizarVentas()
            5->venta.borrarVenta()
            6 -> menuGeneral()
        }

    }while(seleccion !=0)

}


private fun comprobarSeleccionMenu(str: String): Int {
    val seleccion1: Int = try {
        str.toInt()
    } catch (e: Exception) {
        -1
    }
    return seleccion1
}