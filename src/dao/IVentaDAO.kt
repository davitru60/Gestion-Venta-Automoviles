package dao

interface IVentaDAO {
    fun insertarLista(v:ArrayList<Venta>):ArrayList<Venta>
    fun getVentaByID(id:Int):Empleado?
    fun getAllVentas(ventas: Venta):List<Venta>
    fun insertVenta(ventas: Venta):Boolean
    fun updateVenta(venta: Venta):Boolean
    fun deleteVenta(id: Int):Boolean
}