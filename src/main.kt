import conexion.ConexionBD
import dao.AutomovilImpDAO
import no_dao.Automovil

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


fun menuGeneral(automovilDAO: AutomovilImpDAO){
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
            2 -> menuClientes(automovilDAO)
            3 -> obtenerAutomovilesPorMarca(automovilDAO)

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

fun menuVentas(lista: ArrayList<Venta>, venta:Venta, id:Int ){
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