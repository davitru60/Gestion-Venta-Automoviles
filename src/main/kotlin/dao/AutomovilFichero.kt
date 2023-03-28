package dao

import no_dao.Automovil
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

class AutomovilFichero {

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