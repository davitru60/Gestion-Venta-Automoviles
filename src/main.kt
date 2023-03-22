import conexion.ConexionBD
import dao.AutomovilImpDAO
import no_dao.Automovil
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
    var eleccion=0
    var precio=0.0
    println("Vehiculos disponibles")

    var automoviles=automovilDAO.obtenerTodosLosAutomoviles()
    if (automoviles != null) {
        for(automovil in automoviles){
            println(automovil)
        }
    }

    try{
        println("¿Que vehiculo deseas actualizar?")
        eleccion=readln().toInt()
        var automovilElegido= automoviles!![eleccion]

        println("Introduce el nuevo precio")
        precio=readln().toDouble()

        automovilElegido= Automovil(automovilElegido.id,automovilElegido.marca,automovilElegido.modelo,
            automovilElegido.ano,automovilElegido.color,precio)

        if(automovilDAO.actualizarPrecioAutomovil(automovilElegido)){
            println("Se actualizó correctamente el automovil")
        }else{
            println("No se actualizó correctamente el automovil")
        }


    }catch(e:NumberFormatException){
        actualizarPrecioAutomovil(automovilDAO)
    }



}

fun eliminarAutomovil(automovilDAO: AutomovilImpDAO){
    var eleccion=0
    println("Vehiculos disponibles")

    obtenerTodosLosAutomoviles(automovilDAO)

    try{
        println("¿Que vehiculo deseas eliminar? (Debes elegir un codigo)")
        eleccion=readln().toInt()


        if(automovilDAO.eliminarAutomovil(eleccion)){
            println("Se eliminó correctamente el automovil")
        }else{
            println("No se eliminó correctamente el automovil")
        }

    }catch(e:NumberFormatException){
        eliminarAutomovil(automovilDAO)
    }
}



