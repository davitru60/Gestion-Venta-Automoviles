
package dao

import entidades.Venta

/**
Esta interfaz define las operaciones básicas que se pueden realizar en una base de datos para una entidad Venta.
 */
interface IVentaDAO {

    /**
    Inserta una lista de ventas en la base de datos.
    @param v Lista de ventas a insertar.
    @return Lista de ventas insertadas en la base de datos.
     */
    fun insertarLista(v:ArrayList<Venta>):ArrayList<Venta>

    /**
    Obtiene una venta mediante su ID.
    @param id ID de la venta a obtener.
    @return Venta encontrada o null si no se encontró ninguna venta con ese ID.
     */
    fun obtenerVentaMedianteID(id:Int):Venta?

    /**
    Obtiene todas las ventas almacenadas en la base de datos.
    @return Lista de ventas almacenadas en la base de datos.
     */
    fun obtenerTodasLasVentas():List<Venta>

    /**
    Actualiza una venta en la base de datos.
    @param venta Venta a actualizar.
    @return true si la venta se actualizó correctamente, false en caso contrario.
     */
    fun actualizarVentas(venta: Venta):Boolean

    /**
    Borra una venta de la base de datos.
    @param id ID de la venta a borrar.
    @return true si la venta se borró correctamente, false en caso contrario.
     */
    fun borrarVenta(id: Int):Boolean
}