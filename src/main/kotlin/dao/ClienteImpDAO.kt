package dao

import conexionBD.ConexionBD
import constantes.ConstantesBD
import entidades.Cliente

class ClienteImpDAO : IClienteDAO {
    private val conexionBD = ConexionBD(ConstantesBD.URL, ConstantesBD.USUARIO, ConstantesBD.CONTRASENIA)
    /**
     * Agrega un nuevo cliente a la base de datos.
     * @param cliente el objeto Cliente que contiene los datos del cliente a agregar.
     */
    override fun aniadirCliente(cliente:Cliente): Boolean {
        conexionBD.conectar()
        var ejecucionConsulta:Int
        var resultado:Int?=1
        try{
            val sql = "insert into clientes (nombre, apellido, email, telefono) values (?, ?, ?, ?);"
            val ps = conexionBD.getPreparedStatement(sql)
            ps?.setString(1, cliente.nombre)
            ps?.setString(2, cliente.apellido)
            ps?.setString(3, cliente.email)
            ps?.setString(4, cliente.telefono)
            ps?.executeUpdate()
            conexionBD.desconectar()
            ejecucionConsulta=1
    }catch(e:Exception){
        println("Error al añadir el Cliente:${e.message}")
        e.printStackTrace()
        conexionBD.desconectar()
        ejecucionConsulta=0
    }
        return resultado==ejecucionConsulta
    }

    /**
     * Elimina un cliente de la base de datos a partir de su identificador único.
     * @param id el identificador único del cliente a eliminar.
     */
    override fun eliminarCliente(id:Int):Boolean{
        var ejecucionConsulta:Int
        var resultado:Int?=1
        conexionBD.conectar()
        try{
            val sql = "delete from clientes where id = ?;"
            val ps = conexionBD.getPreparedStatement(sql)
            ps?.setInt(1, id)
            ps?.executeUpdate()
            conexionBD.desconectar()
            ejecucionConsulta=1
    }catch(e:Exception){
        println("Error al eliminar el Cliente:${e.message}")
        e.printStackTrace()
        conexionBD.desconectar()
        ejecucionConsulta=0
    }
        return resultado==ejecucionConsulta
    }

    /**
     * Obtiene una lista con todos los clientes registrados en la base de datos.
     * @return la lista de clientes obtenida de la base de datos.
     */
    override fun verListaCliente(): List<Cliente>{
        val sql = "select * from clientes;"
        val clientes = ArrayList<Cliente>()
        conexionBD.conectar()
        val ps = conexionBD.getPreparedStatement(sql)
        val rs = ps?.executeQuery()
        while (rs?.next() == true) {
            val id = rs.getInt("id")
            val nombre = rs.getString("nombre")
            val apellido = rs.getString("apellido")
            val email = rs.getString("email")
            val telefono = rs.getString("telefono")
            clientes.add(Cliente(id,nombre, apellido, email, telefono))
        }
        rs?.close()
        ps?.close()
        conexionBD.desconectar()
        return clientes
    }

    /**
     * Busca un cliente en la base de datos a partir de su identificador único.
     * @param id el identificador único del cliente a buscar.
     * @return el objeto Cliente correspondiente al identificador ingresado o null si no se encontró ningún cliente.
     */
    override fun buscarCliente(id: Int): Cliente? {
        val sql = "select * from clientes where id = ?;"
        conexionBD.conectar()
        val ps = conexionBD.getPreparedStatement(sql)
        ps?.setInt(1, id)
        val rs = ps?.executeQuery()
        var cliente: Cliente? = null
        if (rs?.next() == true) {
            val nombre = rs.getString("nombre")
            val apellido = rs.getString("apellido")
            val email = rs.getString("email")
            val telefono = rs.getString("telefono")
            cliente = Cliente(id, nombre, apellido, email, telefono)
        }
        rs?.close()
        ps?.close()
        conexionBD.desconectar()
        return cliente
    }
}