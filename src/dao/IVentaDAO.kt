
package dao

import Venta

interface IVentaDAO {
    fun insertarLista(v:ArrayList<Venta>):ArrayList<Venta>
    fun obtenerVentaMedianteID(id:Int):Venta?
    fun obtenerTodasLasVentas():List<Venta>
    fun actualizarVentas(venta: Venta):Boolean
    fun borrarVenta(id: Int):Boolean
}