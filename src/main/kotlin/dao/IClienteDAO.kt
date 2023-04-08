package dao

import entidades.Cliente

interface IClienteDAO {
    fun aniadirCliente(cliente:Cliente):Boolean
    fun eliminarCliente(id:Int):Boolean
    fun verListaCliente():List<Cliente>
    fun buscarCliente(id: Int):Cliente?
}