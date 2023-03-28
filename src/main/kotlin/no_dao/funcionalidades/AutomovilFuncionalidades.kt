package no_dao.funcionalidades
import dao.AutomovilFichero
import dao.AutomovilImpDAO
import no_dao.Automovil
import java.lang.NumberFormatException
import java.util.*
import kotlin.collections.ArrayList

class AutomovilFuncionalidades {
    private val automovilDAO=AutomovilImpDAO()
    fun insertarAutomovil(rutaFichero:String){
        val automovilFichero=AutomovilFichero()
        val automoviles=automovilFichero.leerArchivoAutomovil(rutaFichero)

        for(nuevoAutomovil in automoviles){
            comprobarInsercionAutomovil(nuevoAutomovil)
        }
    }
    private fun comprobarInsercionAutomovil(nuevoAutomovil: Automovil) {
        if (automovilDAO.insertarAutomovil(nuevoAutomovil)) {
            println("Se insertó correctamente el automovil")
        } else {
            println("No se insertó correctamente el automovil")
        }
    }

    fun obtenerAutomovilesPorRangoDePrecio(){
        var limiteInferior=0.0
        var limiteSuperior=0.0
        val pair = comprobarRangoDePrecios(limiteInferior, limiteSuperior)
        limiteInferior = pair.first
        limiteSuperior = pair.second

        val automoviles=automovilDAO.obtenerAutomovilPorRangoDePrecio(limiteInferior,limiteSuperior)

        comprobarExistenciaAutomoviles(automoviles)

    }

    private fun comprobarRangoDePrecios(limiteInferior: Double, limiteSuperior: Double): Pair<Double, Double> {
        var limiteInferior1 = limiteInferior
        var limiteSuperior1 = limiteSuperior
        do {
            println("Inserta el rango inferior")
            limiteInferior1 = readln().toDouble()

            println("Inserta el rango superior")
            limiteSuperior1 = readln().toDouble()

            if (limiteInferior1 > limiteSuperior1) {
                println("El precio inferior es mayor que el superior")
            }

        } while (limiteInferior1 > limiteSuperior1)
        return Pair(limiteInferior1, limiteSuperior1)
    }

    fun obtenerAutomovilesPorMarca(){
        println("Inserta marca")
        val marca= readln()
        val automoviles=automovilDAO.obtenerAutomovilesPorMarca(marca)
        comprobarExistenciaAutomoviles(automoviles)

    }

    private fun comprobarExistenciaAutomoviles(automoviles: ArrayList<Automovil>?) {
        if (automoviles != null) {
            for (automovil in automoviles) {
                println(automovil)
            }
        } else {
            println("Se ha producido un error en la obtencion de los vehiculos")
        }
    }

    fun obtenerTodosLosAutomoviles(){
        val automoviles=automovilDAO.obtenerTodosLosAutomoviles()
        comprobarExistenciaAutomoviles(automoviles)
    }

    fun actualizarPrecioAutomovil(){
        var eleccionAutomovil=0
        var precioAutomovil=0.0
        var automovilExiste=0
        println("Vehiculos disponibles")
        val automoviles=automovilDAO.obtenerTodosLosAutomoviles()
        comprobarExistenciaAutomoviles(automoviles)

        try{
            automovilExiste=automovilDAO.comprobarExistenciaDelRegistroPorID(eleccionAutomovil)
            do{
                println("¿Que vehiculo deseas actualizar?")
                eleccionAutomovil=readln().toInt()

                if(automovilExiste==0){
                    println("Este registro no existe , escribe uno existente")
                }
            }while(automovilExiste==0)

            println("Introduce el nuevo precio")
            precioAutomovil=readln().toDouble()

            val automovilElegido= Automovil(eleccionAutomovil,precioAutomovil)


            comprobarActualizacionPrecioAutomovil(automovilElegido)

        }catch(e: NumberFormatException){
            println("Has introducido una letra en vez de un numero")
            actualizarPrecioAutomovil()
        }
    }

    private fun comprobarActualizacionPrecioAutomovil(automovilElegido: Automovil) {
        if (automovilDAO.actualizarPrecioAutomovil(automovilElegido)) {
            println("Se actualizó correctamente el automovil")
        } else {
            println("No se actualizó correctamente el automovil")
        }
    }

    fun eliminarAutomovil(){
        var eleccion=0
        println("Vehiculos disponibles")

        obtenerTodosLosAutomoviles()

        try{
            println("¿Que vehiculo deseas eliminar? (Debes elegir un codigo)")
            eleccion=readln().toInt()
            comprobarEliminacionAutomovil(eleccion)

        }catch(e: NumberFormatException){
            eliminarAutomovil()
        }
    }

    private fun comprobarEliminacionAutomovil(eleccion: Int) {
        if (automovilDAO.eliminarAutomovil(eleccion)) {
            println("Se eliminó correctamente el automovil")
        } else {
            println("No se eliminó correctamente el automovil")
        }
    }
}
