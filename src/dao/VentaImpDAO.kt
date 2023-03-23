package dao

import conexion.ConexionBD
import dao.IVentaDAO
import java.sql.PreparedStatement
import java.sql.SQLException

class VentaImpDAO: IVentaDAO {
    private val conexion = ConexionBD()

    override fun insertarLista(v:ArrayList<Venta>):ArrayList<Venta>{
        conexion.conectar()
        var result: Int? = null
        var ps: PreparedStatement? = null

        val query = "INSERT INTO ventas (automovil_id, cliente_id, fecha, precio_venta) VALUES (?, ?, ?, ?)"
        ps = conexion.getPreparedStatement(query)
        for (i in v) {
            try {
                ps?.setInt(2, i.automovil_id)
                ps?.setInt(3, i.cliente_id)
                ps?.setString(4, i.fecha)
                ps?.setDouble(5, i.precio_venta)
                result = ps?.executeUpdate()
            } catch (e: Exception) {

            } finally {
                ps?.close()
                conexion.desconectar()
            }
        }
        return v
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
                rs.getDouble("precio_venta")
            )
        }
        ps?.close()
        conexion.desconectar()
        return venta
    }

    override fun obtenerTodasLasVentas(): List<Venta> {
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
                rs.getDouble("precio_venta")
            )
            ventas.add(venta)
        }
        st?.close()
        conexion.desconectar()
        return ventas
    }

    override fun actualizarVentas(venta: Venta): Boolean {
        conexion.conectar()
        var result:Int?= null
        val query = "UPDATE ventas SET id=?, automovil_id = ?, cliente_id = ?, fecha = ?, precio_venta = ? WHERE id = ?"
        val ps = conexion.getPreparedStatement(query)
        try {
            ps?.setInt(1, venta.id)
            ps?.setInt(2, venta.automovil_id)
            ps?.setInt(3, venta.cliente_id)
            ps?.setString(4, venta.fecha)
            ps?.setDouble(5, venta.precio_venta)
            result = ps?.executeUpdate()
            // Obtener el valor del ID generado automáticamente
            val rs = ps?.generatedKeys
            if (rs?.next() == true) {
                val idGenerado = rs.getInt(1)
                venta.id = idGenerado // Actualizar el ID en el objeto Venta
            }
        } catch (e: SQLException) {
            println(e.message)
        } finally {
            ps?.close()
            conexion.desconectar()
        }
        return result == 1
    }

    override fun borrarVenta(id: Int): Boolean {
        conexion.conectar()
        var existe = false
        var result: Int? = null
        val queryConsulta = "SELECT id FROM ventas WHERE id = ?"
        val psConsulta = conexion.getPreparedStatement(queryConsulta)
        psConsulta?.setInt(1, id)
        val rsConsulta = psConsulta?.executeQuery()
        if (rsConsulta?.next() == true) {
            existe = true
        }
        psConsulta?.close()

        if (existe) {
            val queryDelete = "DELETE FROM ventas WHERE id = ?"
            val psDelete = conexion.getPreparedStatement(queryDelete)
            psDelete?.setInt(1, id)
            result = psDelete?.executeUpdate()
            psDelete?.close()
        }

        conexion.desconectar()

        return result == 1
    }
}

