package dao

import conexionBD.ConexionBD
import conexionBD.ConstantesBD
import no_dao.Cliente

class ClienteImpDAO : IClienteDAO {
    private val conexionBD = ConexionBD(ConstantesBD.URL, ConstantesBD.USUARIO, ConstantesBD.CONTRASENIA)
    override fun aniadirCliente(cliente:Cliente) {
        val sql = "insert into clientes (nombre, apellido, email, telefono) values (?, ?, ?, ?);"
        conexionBD.conectar()
        val ps = conexionBD.getPreparedStatement(sql)
        ps?.setString(1, cliente.nombre)
        ps?.setString(2, cliente.apellido)
        ps?.setString(3, cliente.email)
        ps?.setString(4, cliente.telefono)
        ps?.executeUpdate()
        conexionBD.desconectar()
    }

    override fun eliminarCliente(id:Int){
        val sql = "delete from clientes where id = ?;"
        conexionBD.conectar()
        val ps = conexionBD.getPreparedStatement(sql)
        ps?.setInt(1, id)
        ps?.executeUpdate()
        conexionBD.desconectar()
    }

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