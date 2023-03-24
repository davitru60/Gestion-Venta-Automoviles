import dao.ClienteImpDAO
import no_dao.Cliente
class ClienteFuncionalidad{
    fun buscarCliente(clienteDAO: ClienteImpDAO) {
        var id: Int
        println("Dime la id del cliente que desea buscar")
        id = readln().toInt()
        val buscarCliente = clienteDAO.buscarCliente(id)
    }

    fun verClientes(clienteDAO: ClienteImpDAO): List<Cliente> {
        val verCliente = clienteDAO.verListaCliente()
        return verCliente
    }

    fun eliminarCliente(clienteDAO: ClienteImpDAO) {
        var id: Int
        println("Dime la id del cliente que desea eliminar")
        id = readln().toInt()
        val eliminarCliente = clienteDAO.eliminarCliente(id)
    }

    fun aniadirCliente(clienteDAO: ClienteImpDAO) {
        var nombre: String
        var apellido: String
        var email: String
        var telefono: Int
        println("Dime el nombre del cliente")
        nombre = readln()
        println("Dime el apellido del cliente")
        apellido = readln()
        println("Dime el email del cliente")
        email = readln()
        println("Dime el telefono del cliente")
        telefono = readln().toInt()
        val aniadirCliente = clienteDAO.aniadirCliente(Cliente(nombre, apellido, email, telefono))
    }
}