package dao

import conexion.ConexionBD
import dao.IVentaDAO
import java.sql.PreparedStatement
import java.sql.SQLException

class VentaImpDAO: IVentaDAO {
    private val conexion = ConexionBD()

    override fun insertarLista(v:ArrayList<Venta>):ArrayList<Venta> {
        conexion.conectar()
        var result: Int? = null
        var ps: PreparedStatement? = null
        val listaNoInsertados = ArrayList<Venta>()

        val query = "INSERT INTO ventas (automovil_id, cliente_id, fecha, precio_venta) VALUES (?, ?, ?, ?)"
        ps = conexion.getPreparedStatement(query)
        for (i in v) {
            try {
                ps?.setInt(1, i.automovil_id)
                ps?.setInt(2, i.cliente_id)
                ps?.setString(3, i.fecha)
                ps?.setFloat(4, i.precio_venta)
                result = ps?.executeUpdate()
            } catch (e: Exception) {
                listaNoInsertados.add(i)
            }
        }
        ps?.close()
        conexion.desconectar()
        return listaNoInsertados
    }

    override fun obtenerVentaMedianteID(id:Int):Venta? {
        conexion.conectar()
        val query = "SELECT * FROM ventas WHERE id = ?"
        val ps = conexion.getPreparedStatement(query)
        ps?.setInt(1, id)
        val rs = ps?.executeQuery()
        var venta: Venta? = null
        if (rs?.next() == true) {
            venta = Venta(
                rs.getInt("id"),
                rs.getInt("automovil_id"),
                rs.getInt("cliente_id"),
                rs.getString("fecha"),
                rs.getFloat("precio_venta")
            )
        }
        ps?.close()
        conexion.desconectar()
        return venta
    }

    override fun obtenerTodasLasVentas(ventas: Venta): List<Venta> {
        conexion.conectar()
        val query = "SELECT * FROM ventas"
        val st = conexion.getStatement()
        val rs = st?.executeQuery(query)
        val ventas = ArrayList<Venta>()
        while (rs?.next() == true) {
            val venta = Venta(
                rs.getInt("id"),
                rs.getInt("automovil_id"),
                rs.getInt("cliente_id"),
                rs.getString("fecha"),
                rs.getFloat("precio_venta")
            )
            ventas.add(venta)
        }
        st?.close()
        conexion.desconectar()
        return ventas
    }

    override fun insertarVenta(venta: Venta): Boolean {
        var result: Int? = null
        var ps: PreparedStatement? = null
        try {
            conexion.conectar()
            val query = "INSERT INTO ventas (automovil_id, cliente_id, fecha, precio_venta) VALUES (?, ?, ?, ?)"
            ps = conexion.getPreparedStatement(query)
            ps?.setInt(1, venta.automovil_id)
            ps?.setInt(2, venta.cliente_id)
            ps?.setString(3, venta.fecha)
            ps?.setFloat(4, venta.precio_venta)
            result = ps?.executeUpdate()
        } catch (e: SQLException) {
            println(e.message)
        } finally {
            ps?.close()
            conexion.desconectar()
        }
        return result == 1
    }

    override fun actualizarVentas(venta: Venta): Boolean {
        conexion.conectar()
        val query = "UPDATE ventas SET automovil_id = ?, cliente_id = ?, fecha = ?, precio_venta = ? WHERE id = ?"
        val ps = conexion.getPreparedStatement(query)
        ps?.setInt(1, venta.id)
        ps?.setInt(2, venta.automovil_id)
        ps?.setInt(3, venta.cliente_id)
        ps?.setString(4, venta.fecha)
        ps?.setFloat(5, venta.precio_venta)
        val result = ps?.executeUpdate()
        ps?.close()
        conexion.desconectar()
        return result == 1
    }

    override fun borrarVenta(id: Int): Boolean {
        conexion.conectar()
        val query = "DELETE FROM ventas WHERE id = ?"
        val ps = conexion.getPreparedStatement(query)
        ps?.setInt(1, id)
        val result = ps?.executeUpdate()
        ps?.close()
        conexion.desconectar()
        return result == 1
    }
}

