package no_dao

import java.time.LocalDate
// LocalDate es una clase que ofrece fechas (año, mes, dia) , y LocalDate.now rellena automaticamente la fecha poniendo la de cuando es insertado el dato


class Venta {
    override fun toString(var id:Int, var automovil_id:Int, var cliente_id:Int, var fecha: LocalDate , var precio_venta: float): String {
        return "Venta [id: $id, id del automóvil: $automovil_id, id del cliente: $cliente_id, fecha: $fecha, precio: $precio_venta"
    }
}