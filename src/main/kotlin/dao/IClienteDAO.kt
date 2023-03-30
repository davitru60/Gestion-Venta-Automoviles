package dao

import no_dao.Cliente

interface IClienteDAO {
    fun aniadirCliente(cliente:Cliente):Boolean
    fun eliminarCliente(id:Int):Boolean
    fun verListaCliente():List<Cliente>
    fun buscarCliente(id: Int):Cliente?
}