package dao

import no_dao.Cliente
import java.sql.*

class ClienteImpDAO : IClienteDAO{
    private val url = "jdbc:mysql://localhost:3307/coches.sql"
    private val user = "myuser"
    private val password = "mypassword"

    override fun añadirCliente(nombre: String, apellido: String, email: String, telefono: String) {
        val sql = "insert into clientes (nombre, apellido, email, telefono) values (?, ?, ?, ?)"
        val conn = ConexionBD
        conn.conectar().setString(1, nombre)
        conn.conectar().setString(2, apellido)
        conn.conectar().setString(3, email)
        conn.conectar().setString(4, telefono)
        conn.conectar().executeUpdate()
        conn.desconectar()
    }
    override fun eliminarCliente(id:Int){
        val sql = "delete from clientes where nombre = ?;"
        val conn = ConexionBD
        conn.conectar().setString(1, nombre)
        conn.desconectar()
    }

    override fun verListaCliente(): List<Cliente>{
        val sql = "select * from clientes;"
        val clientes = ArrayList<Cliente>()
        val conn = ConexionBD
        val rs = conn.conectar.executeQuery(sql)
        while (rs.next()) {
            val id= rs.getString("id")
            val nombre = rs.getString("nombre")
            val apellido = rs.getString("apellido")
            val email = rs.getString("email")
            val telefono = rs.getString("telefono")
            clientes.add(Cliente(id,nombre, apellido, email, telefono))
        }
        conn.desconectar()
        return clientes
    }

    override fun buscarCliente(id: Int): Cliente? {
        val sql = "select * from clientes where nombre = ?;"
            val conn = ConexionBD
            conn.conectar.setString(1, nombre)
            val rs = conn.conectar.executeQuery(sql)
            if (rs.next()) {
                val id= rs.getString("id")
                val apellido = rs.getString("apellido")
                val email = rs.getString("email")
                val telefono = rs.getString("telefono")
                return Cliente(id,nombre, apellido, email, telefono)
            }
            conn.desconectar()
    }
}