import conexion.ConexionBD
import dao.AutomovilImpDAO
import dao.ClienteImpDAO
import dao.VentaImpDAO
import no_dao.Automovil
import no_dao.Venta
import java.lang.NumberFormatException

fun main(){
    var conexion= ConexionBD()
    val automovilDAO=AutomovilImpDAO()
    val clienteDAO=ClienteImpDAO()

    if(conexion !=null){
        println("Conectado con exito")
        menuGeneral(automovilDAO,clienteDAO)
    }else{
        println("Error al conectar")
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



fun menuGeneral(automovilDAO: AutomovilImpDAO,clienteDAO: ClienteImpDAO){
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
            1 -> menuAutomoviles(automovilDAO)
            2 -> menuClientes(clienteDAO)
            3 -> menuVentas()
            4 -> break

            else -> println("Opcion incorrecta")
        }
    } while (seleccion != 0)
}