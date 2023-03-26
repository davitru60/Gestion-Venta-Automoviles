package dao
import no_dao.Automovil

interface IAutomovilDAO {
    fun insertarAutomovil(automovil: Automovil):Boolean
    fun actualizarPrecioAutomovil(automovil:Automovil):Boolean
    fun obtenerAutomovilPorRangoDePrecio(limiteInferior:Double,limiteSuperior:Double):ArrayList<Automovil>?
    fun obtenerAutomovilesPorMarca(marca: String):ArrayList<Automovil>?
    fun obtenerTodosLosAutomoviles(): ArrayList<Automovil>?
    fun eliminarAutomovil(id:Int):Boolean
}