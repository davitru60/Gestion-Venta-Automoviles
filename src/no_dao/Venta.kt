package no_dao

class Venta(var id:Int, var automovil_id:Int, var cliente_id:Int, var fecha: String , var precio_venta: Float) {
    override fun toString(): String {
        return "Venta [id: $id, id del automóvil: $automovil_id, id del cliente: $cliente_id, fecha: $fecha, precio: $precio_venta"
    }
}