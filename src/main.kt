import conexion.ConexionBD
import dao.AutomovilImpDAO
import dao.ClienteImpDAO
import dao.VentaImpDAO
import no_dao.*

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

fun menuAutomoviles(automovilDAO: AutomovilImpDAO){
    val automovilFuncionalidades=AutomovilFuncionalidades()
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
        when(seleccion) {
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
fun menuClientes(clienteDAO: ClienteImpDAO){
    var seleccion: Int
    var str: String
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
            1 -> aniadirCliente(clienteDAO)
            2 -> eliminarCliente(clienteDAO)
            3 -> verClientes(clienteDAO)
            4 -> buscarCliente(clienteDAO)

            else -> println("Opcion incorrecta")
        }
    } while (seleccion != 0)
}

private fun buscarCliente(clienteDAO: ClienteImpDAO) {
    var id:Int
    println("Dime la id del cliente que desea buscar")
    id= readln().toInt()
    val buscarCliente= clienteDAO.buscarCliente(id)
}

private fun verClientes(clienteDAO: ClienteImpDAO):List<Cliente> {
    val verCliente= clienteDAO.verListaCliente()
    return verCliente
}

private fun eliminarCliente(clienteDAO: ClienteImpDAO) {
    var id:Int
    println("Dime la id del cliente que desea eliminar")
    id= readln().toInt()
    val eliminarCliente= clienteDAO.eliminarCliente(id)
}

private fun aniadirCliente(clienteDAO: ClienteImpDAO) {
    var nombre:String
    var apellido:String
    var email:String
    var telefono:Int
    println("Dime el nombre del cliente")
    nombre= readln()
    println("Dime el apellido del cliente")
    apellido= readln()
    println("Dime el email del cliente")
    email= readln()
    println("Dime el telefono del cliente")
    telefono= readln().toInt()
    val aniadirCliente= clienteDAO.aniadirCliente(Cliente(nombre, apellido, email, telefono))
}

val ventaDAO = VentaImpDAO()


fun insertarListaVentas(){
    var automovil_id:Int=0
    var cliente_id:Int=0
    var fecha:String=""
    var precio_venta:Double=00.00

    val ventas = ArrayList<Venta>()
    var cont:Int=0

    println("Inserte el número de ventas que quiera introducir")
    try {
        cont=readln().toInt()
    } catch (e:Exception){
        println("ERROR: El dato introducido es incorrecto.")
    }

    while (cont>0){
        try {

            println("Introduce el ID del automóvil:")
            automovil_id=readln().toInt()

            println("Introduce el ID del cliente:")
            cliente_id=readln().toInt()

            println("Introduce la fecha de la venta en formato YYYY-MM-DD")
            fecha= readln()

            println("Introduce el precio de la venta:")
            precio_venta=readln().toDouble()

        } catch (e:Exception){
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

fun obtenerVentaMedianteID(){

    var id:Int=0

    println("Introduce el ID:")
    try {
        id=readln().toInt()
    } catch (e:Exception){
        println("ERROR: Tipo de dato introducio incorrecto")
    }

    val ventaObtenida = ventaDAO.obtenerVentaMedianteID(id)
    if (ventaObtenida != null) {
        println("Venta obtenida: $ventaObtenida")
    } else {
        println("No se encontró ninguna venta con el ID especificado")
    }
}

fun obtenerTodasLasVentas(){
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

fun actualizarVentas(){
    var id:Int=0
    var automovil_id:Int=0
    var cliente_id:Int=0
    var fecha:String=""
    var precio_venta:Double=00.00

    try {

        println("Introduce el ID:")
        id=readln().toInt()

        println("Introduce el ID del automóvil:")
        automovil_id=readln().toInt()

        println("Introduce el ID del cliente:")
        cliente_id=readln().toInt()

        println("Introduce la fecha de la venta en formato YYYY-MM-DD")
        fecha= readln()

        println("Introduce el precio de la venta:")
        precio_venta=readln().toDouble()

    } catch (e:Exception){
        println("ERROR: Datos introducidos incorrectos")
    }

    val ventaActualizar = Venta(
        id=id,
        automovil_id = automovil_id,
        cliente_id = cliente_id,
        fecha = fecha,
        precio_venta = precio_venta,)

    val actualizado = ventaDAO.actualizarVentas(ventaActualizar)
    println("Venta actualizada: $actualizado")
}

fun borrarVenta(){

    var id:Int=0

    println("Introduce el ID:")
    try {
        id=readln().toInt()
    } catch (e:Exception){
        println("ERROR: Tipo de dato introducido incorrecto")
    }

    val borrado = ventaDAO.borrarVenta(id)
    println("Venta borrada: $borrado")
}

fun menuVentas(){
    var s:Int=0
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
        1->insertarListaVentas()
        2->obtenerVentaMedianteID()
        3->obtenerTodasLasVentas()
        4->actualizarVentas()
        5->borrarVenta()
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

