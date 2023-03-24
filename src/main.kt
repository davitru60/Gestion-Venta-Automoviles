import conexion.ConexionBD
import dao.AutomovilImpDAO
import no_dao.Automovil
import java.lang.NumberFormatException

fun main(){
    val conexion= ConexionBD()
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
        println("2. Actualizar precio de un automovil")
        println("3. Obtener automoviles por rango de precios")
        println("4. Obtener automovil por marca")
        println("5. Obtener todos los automoviles")
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

fun menuVentas(){

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


    }catch(e:NumberFormatException){
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

    }catch(e:NumberFormatException){
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



