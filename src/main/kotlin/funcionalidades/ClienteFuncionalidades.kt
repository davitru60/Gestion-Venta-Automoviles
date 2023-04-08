package funcionalidades

import dao.ClienteImpDAO
import entidades.Cliente
class ClienteFuncionalidades{
    private val clienteDAO= ClienteImpDAO()
    /**
     * Busca un cliente en el sistema a partir de su identificador único.
     * Se solicita al usuario que ingrese el identificador del cliente y se utiliza el método buscarCliente de la clase
     * ClienteImpDAO para buscar el cliente correspondiente.
     */
    fun buscarCliente() {
        var id: Int
        println("Dime la id del cliente que desea buscar")
        id = readln().toInt()
        val buscarCliente = clienteDAO.buscarCliente(id)
    }

    /**
     * Muestra la lista de todos los clientes registrados en el sistema.
     * Se utiliza el método verListaCliente de la clase ClienteImpDAO para obtener la lista de todos los clientes y se
     * llama al método comprobarExistenciaClientes para comprobar si existen clientes en la lista y mostrarlos en pantalla.
     */
    fun verClientes() {
        val verCliente = clienteDAO.verListaCliente()
        comprobarExistenciaClientes(verCliente)
    }

    /**
     * Comprueba si existen clientes en la lista y los muestra en pantalla.
     * @param clientes la lista de clientes obtenida del método verListaCliente de la clase ClienteImpDAO.
     */
    private fun comprobarExistenciaClientes(clientes: List<Cliente>) {
        for (cliente in clientes) {
            println(cliente)
        }
    }

    /**
     * Elimina un cliente del sistema a partir de su identificador único.
     * Se solicita al usuario que ingrese el identificador del cliente y se utiliza el método eliminarCliente de la clase
     * ClienteImpDAO para eliminar el cliente correspondiente.
     */
    fun eliminarCliente() {
        var id: Int
        println("Dime la id del cliente que desea eliminar")
        id = readln().toInt()
        val eliminarCliente = clienteDAO.eliminarCliente(id)
    }

    /**
     * Añade un cliente al sistema.
     * Se solicita al usuario que ingrese el nombre, apellido, email y teléfono del cliente y se utiliza el método aniadirCliente de la clase
     * ClienteImpDAO para añadir el cliente correspondiente.
     */
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