package no_dao
/**
 * Esta clase representa una Venta de un automóvil a un cliente.
 *
 * @property id El id de la venta.
 * @property automovil_id El id del automóvil vendido.
 * @property cliente_id El id del cliente que compró el automóvil.
 * @property fecha La fecha en que se realizó la venta.
 * @property precio_venta El precio de venta del automóvil.
 *
 * @constructor Crea una instancia de la clase Venta.
 * @param id El id de la venta (por defecto es 0).
 * @param automovil_id El id del automóvil vendido (por defecto es 0).
 * @param cliente_id El id del cliente que compró el automóvil (por defecto es 0).
 * @param fecha La fecha en que se realizó la venta (por defecto es una cadena vacía).
 * @param precio_venta El precio de venta del automóvil (por defecto es 00.00).
 */
class Venta(var id:Int=0,
            var automovil_id:Int = 0,
            var cliente_id:Int = 0,
            var fecha: String = "",
            var precio_venta: Double = 00.00) {

    /**
     * Crea una instancia de la clase Venta a partir de los datos proporcionados.
     *
     * @param automovil_id El id del automóvil vendido.
     * @param cliente_id El id del cliente que compró el automóvil.
     * @param fecha La fecha en que se realizó la venta.
     * @param precio_venta El precio de venta del automóvil.
     */
    constructor(automovil_id:Int, cliente_id:Int, fecha:String, precio_venta:Double) : this() {
        this.automovil_id=automovil_id
        this.cliente_id=cliente_id
        this.fecha=fecha
        this.precio_venta=precio_venta
    }

    /**
     * Devuelve una cadena que representa la Venta en formato legible para el usuario.
     *
     * @return Una cadena con el id de la venta, el id del automóvil vendido, el id del cliente que compró el automóvil, la fecha en que se realizó la venta y el precio de venta del automóvil.
     */
    override fun toString(): String {
        return "Venta [id: $id, id del automóvil: $automovil_id, id del cliente: $cliente_id, fecha: $fecha, precio: $precio_venta"
    }
}