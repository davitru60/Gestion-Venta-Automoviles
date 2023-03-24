package no_dao

class Venta(var id:Int=0,
            var automovil_id:Int = 0,
            var cliente_id:Int = 0,
            var fecha: String = "",
            var precio_venta: Double = 00.00) {


    constructor(automovil_id:Int, cliente_id:Int, fecha:String, precio_venta:Double) : this() {
        this.automovil_id=automovil_id
        this.cliente_id=cliente_id
        this.fecha=fecha
        this.precio_venta=precio_venta
    }

    override fun toString(): String {
        return "Venta [id: $id, id del automóvil: $automovil_id, id del cliente: $cliente_id, fecha: $fecha, precio: $precio_venta"
    }
}