package no_dao.funcionalidades
import dao.AutomovilFichero
import dao.AutomovilImpDAO
import no_dao.Automovil
import java.lang.NumberFormatException
import kotlin.collections.ArrayList

/**
 * Clase que llama a los métodos del objeto de acceso a datos , esta posee las reglas
 * de negocio referentes a los automoviles.
 */
class AutomovilFuncionalidades {
    private val automovilDAO=AutomovilImpDAO()

    /**

    *Inserta los automóviles de un fichero en la base de datos.
    *@param rutaFichero la ruta del archivo que contiene los datos de los automóviles a insertar.
     */
    fun insertarAutomovil(rutaFichero:String){
        val automovilFichero=AutomovilFichero()
        val automoviles=automovilFichero.leerArchivoAutomovil(rutaFichero)

        for(nuevoAutomovil in automoviles){
            comprobarInsercionAutomovil(nuevoAutomovil)
        }
    }

    /**

    * Verifica si un automóvil se inserta correctamente en la base de datos y muestra un mensaje en consecuencia.
    *@param nuevoAutomovil el objeto Automovil que se va a insertar en la base de datos.
     */
    private fun comprobarInsercionAutomovil(nuevoAutomovil: Automovil) {
        if (automovilDAO.insertarAutomovil(nuevoAutomovil)) {
            println("Se insertó correctamente el automovil")
        } else {
            println("No se insertó correctamente el automovil")
        }
    }

    /**

    * Obtiene los automóviles de la base de datos que están dentro del rango de precios especificado.
     */
    fun obtenerAutomovilesPorRangoDePrecio(){
        var limiteInferior=0.0
        var limiteSuperior=0.0
        val pair = comprobarRangoDePrecios(limiteInferior, limiteSuperior)
        limiteInferior = pair.first
        limiteSuperior = pair.second

        val automoviles=automovilDAO.obtenerAutomovilPorRangoDePrecio(limiteInferior,limiteSuperior)

        comprobarExistenciaAutomoviles(automoviles)

    }

    /**
    *Pide al usuario que ingrese un rango de precios y verifica si el límite inferior es menor o igual que el límite superior.
    *@param limiteInferior el límite inferior del rango de precios.
    *@param limiteSuperior el límite superior del rango de precios.
    *@return un objeto Pair con los límites del rango de precios ingresado.
     */
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

    /**

    * Obtiene los automóviles de la base de datos que corresponden a una marca especificada.
     */
    fun obtenerAutomovilesPorMarca(){
        println("Inserta marca")
        val marca= readln()
        val automoviles=automovilDAO.obtenerAutomovilesPorMarca(marca)
        comprobarExistenciaAutomoviles(automoviles)

    }

    /**

    *Verifica si se obtuvieron automóviles de la base de datos y muestra los automóviles en consecuencia.
    *Si no se obtuvieron automóviles, muestra un mensaje de error.
    *@param automoviles una lista de objetos Automovil obtenida de la base de datos, que puede ser nula.
     */
    private fun comprobarExistenciaAutomoviles(automoviles: ArrayList<Automovil>?) {
        if (automoviles != null) {
            for (automovil in automoviles) {
                println(automovil)
            }
        } else {
            println("Se ha producido un error en la obtencion de los vehiculos")
        }
    }

    /**

    Obtiene todos los automóviles de la base de datos.
     */
    fun obtenerTodosLosAutomoviles(){
        val automoviles=automovilDAO.obtenerTodosLosAutomoviles()
        comprobarExistenciaAutomoviles(automoviles)
    }

    /**
    * Método que permite actualizar el precio de un automóvil en la base de datos.
    * Se muestran todos los vehículos disponibles y se solicita al usuario que seleccione el código del vehículo a actualizar.
    * Posteriormente se solicita al usuario que introduzca el nuevo precio del vehículo.
    * Si el código introducido no es válido o no existe en la base de datos, se muestra un mensaje de error y se vuelve a solicitar al usuario que introduzca un código válido.
    * Si el usuario introduce un valor no numérico para el precio, se muestra un mensaje de error y se vuelve a solicitar al usuario que introduzca un valor válido.
     */
    fun actualizarPrecioAutomovil(){
        var eleccionAutomovil=0
        var precioAutomovil=0.0
        var automovilExiste=0
        println("Vehiculos disponibles")
        obtenerTodosLosAutomoviles()

        try{
            do{
                println("¿Que vehiculo deseas actualizar? (Debes elegir un codigo)")
                eleccionAutomovil=readln().toInt()
                automovilExiste=automovilDAO.comprobarExistenciaDelRegistroPorID(eleccionAutomovil)

                if(automovilExiste==0||eleccionAutomovil<0){
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

/**
 * Función para comprobar si se actualizó correctamente el precio de un automóvil
 * @param automovilElegido Automóvil seleccionado para
 *
 */
    private fun comprobarActualizacionPrecioAutomovil(automovilElegido: Automovil) {
        if (automovilDAO.actualizarPrecioAutomovil(automovilElegido)) {
            println("Se actualizó correctamente el automovil")
        } else {
            println("No se actualizó correctamente el automovil")
        }
    }


    /**

    *Esta función permite eliminar un registro de la base de datos de automóviles.
    *Primero se muestra la lista de todos los automóviles disponibles y se solicita el código del automóvil que se desea eliminar.
    *Luego se verifica si el código del automóvil ingresado existe en la base de datos.
    *Si el código ingresado es inválido o no existe, se muestra un mensaje de error y se solicita al usuario que ingrese un código válido.
    *Si el código ingresado es válido, se llama a la función "comprobarEliminacionAutomovil" para realizar la eliminación.
    *Si se ingresa un valor no numérico, se llama de nuevo a esta función para solicitar un valor válido.
     */
    fun eliminarAutomovil(){
        var eleccionAutomovil=0
        var automovilExiste=0
        println("Vehiculos disponibles")
        obtenerTodosLosAutomoviles()

        try{
            do{
                println("¿Que vehiculo deseas eliminar? (Debes elegir un codigo)")
                eleccionAutomovil=readln().toInt()
                automovilExiste=automovilDAO.comprobarExistenciaDelRegistroPorID(eleccionAutomovil)
                if(automovilExiste==0||eleccionAutomovil<0){
                    println("Este registro no existe , escribe uno existente")
                }
            }while(automovilExiste==0)
            comprobarEliminacionAutomovil(eleccionAutomovil)

        }catch(e: NumberFormatException){
            eliminarAutomovil()
        }
    }

/**

*Verifica si el automovil seleccionado existe y si es así, llama al método "eliminarAutomovil" de la clase "AutomovilDAO"
*para eliminar el registro correspondiente.
*@param eleccion el código del automovil seleccionado para eliminar
*/
    private fun comprobarEliminacionAutomovil(eleccion: Int) {
        if (automovilDAO.eliminarAutomovil(eleccion)) {
            println("Se eliminó correctamente el automovil")
        } else {
            println("No se eliminó correctamente el automovil")
        }
    }
}
