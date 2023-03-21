import conexion.ConexionBD
import dao.AutomovilImpDAO
import no_dao.Automovil

fun main(){
    var conexion= ConexionBD()

    if(conexion !=null){
        println("Conectado con exito")
    }else{
        println("Error al conectar")
    }

    val automovilDAO=AutomovilImpDAO()
    insertarAutomovil(automovilDAO)

}


fun insertarAutomovil(automovilDAO: AutomovilImpDAO){
    var marca=""
    var modelo=""
    var anio=0
    var color=""
    var precio=0.0
    val regex = Regex("[a-zA-Z]+")

     println("Inserta la marca")
     marca= readln().toString()
     do{
         println("Marca inválida. Inserta la marca nuevamente")
         marca= readln().toString()
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
        println("Se insertó correctamente el automovil $nuevoAutomovil")
    }else{
        println("No se insertó correctamente el automovil")
    }
}