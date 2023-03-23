import conexion.ConexionBD
import dao.AutomovilImpDAO
import dao.ClienteImpDAO
import dao.VentaImpDAO
import no_dao.Automovil
import no_dao.Cliente

fun main(){
    var conexion= ConexionBD()
    val automovilDAO=AutomovilImpDAO()
    val clienteDAO=ClienteImpDAO()
    val ventaDAO=VentaImpDAO()

    if(conexion !=null){
        println("Conectado con exito")
        menuGeneral(automovilDAO)
    }else{
        println("Error al conectar")
    }
}


fun menuGeneral(automovilDAO: AutomovilImpDAO,clienteDAO: ClienteImpDAO,ventaDAO: VentaImpDAO){
    var seleccion: Int
    var str: String
    do {
        println("¿Qué deseas hacer?")
        println("1. Realizar acciones con los automoviles")
        println("2. Realizar acciones con los clientes")
        println("3. Realizar accioens con las ventas")

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
            3 -> menuVentas(ventaDAO)

            else -> println("Opcion incorrecta")
        }
    } while (seleccion != 0)

}
fun menuAutomoviles(automovilDAO: AutomovilImpDAO){
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
            1 -> insertarAutomovil(automovilDAO)
            2 -> actualizarPrecioAutomovil(automovilDAO)
            3 -> obtenerAutomovilesPorRangoDePrecio(automovilDAO)
            4 -> obtenerAutomovilesPorMarca(automovilDAO)
            5 -> obtenerTodosLosAutomoviles(automovilDAO)
            6 -> eliminarAutomovil(automovilDAO)
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
    eliminarCliente
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
    aniadirCliente
}

fun menuVentas(){

}


/*Automovil*/
fun insertarAutomovil(automovilDAO: AutomovilImpDAO){
    var marca=""
    var modelo=""
    var anio=0
    var color=""
    var precio=0.0
    val regex = Regex("[a-zA-Z]{2,}")

    println("Inserta la marca")
     marca= readln().toString()
     do{
         if(!regex.matches(marca)){
             println("Marca inválida. Inserta la marca nuevamente")
             marca= readln().toString()
         }

     }while(!regex.matches(marca))


     println("Inserta el modelo")
     modelo=readln().toString()

     println("Inserta el año")
     anio=readln().toInt()

     println("Inserta el color")
     color=readln().toString()

     println("Inserta el precio")
     precio=readln().toDouble()

    val nuevoAutomovil= Automovil(marca,modelo,anio,color,precio)


    if(automovilDAO.insertarAutomovil(nuevoAutomovil)){
        println("Se insertó correctamente el automovil")
    }else{
        println("No se insertó correctamente el automovil")
    }
}

fun obtenerAutomovilesPorRangoDePrecio(automovilDAO: AutomovilImpDAO){
    println("Inserta el rango inferior")
    var limiteInferior=readln().toDouble()

    println("Inserta el rango superior")
    var limiteSuperior=readln().toDouble()


    var automoviles=automovilDAO.obtenerAutomovilPorRangoDePrecio(limiteInferior,limiteSuperior)

    if (automoviles != null) {
        for(automovil in automoviles){
            println(automovil)
        }
    }else{
        println("Se ha producido un error en la obtencion de los vehiculos")
    }

}

fun obtenerAutomovilesPorMarca(automovilDAO: AutomovilImpDAO){
    println("Inserta marca")
    var marca=readln().toString()
    var automoviles=automovilDAO.obtenerAutomovilesPorMarca(marca)

    if (automoviles != null) {
        for(automovil in automoviles){
            println(automovil)
        }
    }else{
        println("Se ha producido un error en la obtencion de los vehiculos")
    }

}

fun obtenerTodosLosAutomoviles(automovilDAO: AutomovilImpDAO){
    var automoviles=automovilDAO.obtenerTodosLosAutomoviles()
    if (automoviles != null) {
        for(automovil in automoviles){
            println(automovil)
        }
    }
}
fun actualizarPrecioAutomovil(automovilDAO: AutomovilImpDAO){
    println("¿Que vehiculo deseas actualizar?")
    obtenerTodosLosAutomoviles(automovilDAO)

}

fun eliminarAutomovil(automovilDAO: AutomovilImpDAO){

}