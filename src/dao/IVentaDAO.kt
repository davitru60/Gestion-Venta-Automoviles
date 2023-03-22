
package dao

import Venta

interface IVentaDAO {
    fun insertarLista(v:ArrayList<Venta>):ArrayList<Venta>
    fun obtenerVentaMedianteID(id:Int):Venta?
    fun obtenerTodasLasVentas(ventas: Venta):List<Venta>
    fun insertarVenta(ventas: Venta):Boolean
    fun actualizarVentas(venta: Venta):Boolean
    fun borrarVenta(id: Int):Boolean
}