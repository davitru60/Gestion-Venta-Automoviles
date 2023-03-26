package dao

import no_dao.Cliente

interface IClienteDAO {
    fun aniadirCliente(cliente:Cliente)
    fun eliminarCliente(id:Int)
    fun verListaCliente():List<Cliente>
    fun buscarCliente(id: Int):Cliente?
}