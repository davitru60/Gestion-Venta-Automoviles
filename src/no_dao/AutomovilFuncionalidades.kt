package no_dao

import dao.AutomovilImpDAO
import java.lang.NumberFormatException

class AutomovilFuncionalidades {
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

        val nuevoAutomovil= Automovil(marca, modelo, anio, color, precio)

        comprobarInsercionAutomovil(automovilDAO, nuevoAutomovil)
    }

    private fun comprobarInsercionAutomovil(automovilDAO: AutomovilImpDAO, nuevoAutomovil: Automovil) {
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

    private fun comprobarExistenciaAutomoviles(automoviles: ArrayList<Automovil>?) {
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

            automovilElegido= Automovil(
                automovilElegido.id, automovilElegido.marca, automovilElegido.modelo,
                automovilElegido.ano, automovilElegido.color, precioAutomovil
            )

            comprobarActualizacionPrecioAutomovil(automovilDAO, automovilElegido)


        }catch(e: NumberFormatException){
            actualizarPrecioAutomovil(automovilDAO)
        }
    }

    private fun comprobarActualizacionPrecioAutomovil(automovilDAO: AutomovilImpDAO, automovilElegido: Automovil) {
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

    private fun comprobarEliminacionAutomovil(automovilDAO: AutomovilImpDAO, eleccion: Int) {
        if (automovilDAO.eliminarAutomovil(eleccion)) {
            println("Se eliminó correctamente el automovil")
        } else {
            println("No se eliminó correctamente el automovil")
        }
    }
}
