package funcionalidades

import dao.ClienteImpDAO
import no_dao.Cliente
class ClienteFuncionalidades{
    private val clienteDAO= ClienteImpDAO()
    fun buscarCliente() {
        var id: Int
        println("Dime la id del cliente que desea buscar")
        id = readln().toInt()
        val buscarCliente = clienteDAO.buscarCliente(id)

    }

    fun verClientes() {
        val verCliente = clienteDAO.verListaCliente()
        comprobarExistenciaClientes(verCliente)
    }

    private fun comprobarExistenciaClientes(clientes: List<Cliente>) {
        for (cliente in clientes) {
            println(cliente)
        }
    }

    fun eliminarCliente() {
        var id: Int
        println("Dime la id del cliente que desea eliminar")
        id = readln().toInt()
        val eliminarCliente = clienteDAO.eliminarCliente(id)
    }

    fun aniadirCliente() {
        var nombre: String
        var apellido: String
        var email: String
        var telefono: String
        println("Dime el nombre del cliente")
        nombre = readln()
        println("Dime el apellido del cliente")
        apellido = readln()
        println("Dime el email del cliente")
        email = readln()
        println("Dime el telefono del cliente")
        telefono = readln()
        val aniadirCliente = clienteDAO.aniadirCliente(Cliente(nombre, apellido, email, telefono))
    }
}