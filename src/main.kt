import conexion.ConexionBD
import dao.AutomovilImpDAO
import dao.VentaImpDAO
import no_dao.Automovil
import no_dao.Venta
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

fun menuClientes(automovilDAO: AutomovilImpDAO){

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




/*Automovil*/
fun insertarAutomovil(automovilDAO: AutomovilImpDAO){
    var marca:String
    val regex = Regex("[a-zA-Z]{2,}")

    println("Inserta la marca")
    marca= readln()
    do{
        if(!regex.matches(marca)){
            println("Marca inválida. Inserta la marca nuevamente")
            marca= readln()
        }

    }while(!regex.matches(marca))


    println("Inserta el modelo")
    val modelo  = readln()

    do{
        if(!regex.matches(marca)){
            println("Modelo inválido. Inserta el modelo nuevamente")
            marca= readln()
        }

    }while(!regex.matches(marca))


    println("Inserta el año")
    val anio = readln().toInt()

    println("Inserta el color")
    val color = readln()

    println("Inserta el precio")
    var precio = readln().toDouble()

    val nuevoAutomovil= Automovil(marca,modelo,anio,color,precio)

    comprobarInsercionAutomovil(automovilDAO, nuevoAutomovil)
}

fun comprobarInsercionAutomovil(automovilDAO: AutomovilImpDAO, nuevoAutomovil: Automovil) {
    if (automovilDAO.insertarAutomovil(nuevoAutomovil)) {
        println("Se insertó correctamente el automovil")
    } else {
        println("No se insertó correctamente el automovil")
    }
}

fun obtenerAutomovilesPorRangoDePrecio(automovilDAO: AutomovilImpDAO){
    println("Inserta el rango inferior")
    val limiteInferior=readln().toDouble()

    println("Inserta el rango superior")
    val limiteSuperior=readln().toDouble()


    val automoviles=automovilDAO.obtenerAutomovilPorRangoDePrecio(limiteInferior,limiteSuperior)

    comprobarExistenciaAutomoviles(automoviles)

}

fun obtenerAutomovilesPorMarca(automovilDAO: AutomovilImpDAO){
    println("Inserta marca")
    val marca= readln()
    val automoviles=automovilDAO.obtenerAutomovilesPorMarca(marca)
    comprobarExistenciaAutomoviles(automoviles)

}

fun comprobarExistenciaAutomoviles(automoviles: ArrayList<Automovil>?) {
    if (automoviles != null) {
        for (automovil in automoviles) {
            println(automovil)
        }
    } else {
        println("Se ha producido un error en la obtencion de los vehiculos")
    }
}

fun obtenerTodosLosAutomoviles(automovilDAO: AutomovilImpDAO){
    val automoviles=automovilDAO.obtenerTodosLosAutomoviles()
    comprobarExistenciaAutomoviles(automoviles)
}
fun actualizarPrecioAutomovil(automovilDAO: AutomovilImpDAO){
    var eleccionAutomovil=0
    var precioAutomovil=0.0
    println("Vehiculos disponibles")

    val automoviles=automovilDAO.obtenerTodosLosAutomoviles()
    comprobarExistenciaAutomoviles(automoviles)

    try{
        println("¿Que vehiculo deseas actualizar?")
        eleccionAutomovil=readln().toInt()
        var automovilElegido= automoviles!![eleccionAutomovil]

        println("Introduce el nuevo precio")
        precioAutomovil=readln().toDouble()

        automovilElegido= Automovil(automovilElegido.id,automovilElegido.marca,automovilElegido.modelo,
            automovilElegido.ano,automovilElegido.color,precioAutomovil)

        comprobarActualizacionPrecioAutomovil(automovilDAO, automovilElegido)


    }catch(e: NumberFormatException){
        actualizarPrecioAutomovil(automovilDAO)
    }
}

fun comprobarActualizacionPrecioAutomovil(automovilDAO: AutomovilImpDAO, automovilElegido: Automovil) {
    if (automovilDAO.actualizarPrecioAutomovil(automovilElegido)) {
        println("Se actualizó correctamente el automovil")
    } else {
        println("No se actualizó correctamente el automovil")
    }
}

fun eliminarAutomovil(automovilDAO: AutomovilImpDAO){
    var eleccion=0
    println("Vehiculos disponibles")

    obtenerTodosLosAutomoviles(automovilDAO)

    try{
        println("¿Que vehiculo deseas eliminar? (Debes elegir un codigo)")
        eleccion=readln().toInt()
        comprobarEliminacionAutomovil(automovilDAO, eleccion)

    }catch(e: NumberFormatException){
        eliminarAutomovil(automovilDAO)
    }
}

fun comprobarEliminacionAutomovil(automovilDAO: AutomovilImpDAO, eleccion: Int) {
    if (automovilDAO.eliminarAutomovil(eleccion)) {
        println("Se eliminó correctamente el automovil")
    } else {
        println("No se eliminó correctamente el automovil")
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
            2 -> menuClientes(automovilDAO)
            3 -> menuVentas()
            4 -> break

            else -> println("Opcion incorrecta")
        }
    } while (seleccion != 0)
}

