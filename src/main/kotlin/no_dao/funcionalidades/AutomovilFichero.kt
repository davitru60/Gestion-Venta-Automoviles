package no_dao.funcionalidades

import no_dao.Automovil
import java.io.File
import java.io.IOException

/**
 * Esta clase proporciona una funcionalidad para leer un archivo de automóviles y
 * almacenar los datos en una lista de objetos Automovil.
 */
class AutomovilFichero {

    /**
     * Lee un archivo de automóviles y devuelve una lista de objetos Automovil.
     *
     * @param ruta la ruta del archivo que se va a leer
     * @return una lista de objetos Automovil
     */
    fun leerArchivoAutomovil(ruta:String):ArrayList<Automovil>{
        val automoviles=ArrayList<Automovil>()
        try{
            var marca:String
            var modelo:String
            var ano:Int
            var color:String
            var precio:Double
            val file= File(ruta)

            val lineas=file.readLines()
            if(file.canRead()){
                for(linea in lineas){
                    val camposFichero=linea.split(",")
                    marca=camposFichero[0]
                    modelo=camposFichero[1]
                    ano=camposFichero[2].toInt()
                    color=camposFichero[3]
                    precio=camposFichero[4].toDouble()

                    val auto=Automovil(marca, modelo, ano, color, precio)
                    automoviles.add(auto)
                }
            }else{
                println("No se puede leer el archivo")
            }


        }catch(e:IOException){
            println(e.printStackTrace())
        }
        return automoviles
    }
}