package dao
import no_dao.Automovil

interface IAutomovilDAO {
    fun insertarAutomovil(automovil: Automovil):Boolean
    fun actualizarAutomovil(automovil:Automovil):Boolean
    fun eliminarAutomovil(id:Int):Boolean
    fun obtenerAutomovilPorPrecio(precio: Double):Boolean
    fun obtenerAutomovilesPorMarca(marca: String):Boolean
}