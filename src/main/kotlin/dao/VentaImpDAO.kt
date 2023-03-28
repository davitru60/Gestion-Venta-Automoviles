package dao

import conexionBD.ConexionBD
import conexionBD.ConstantesBD
import no_dao.Venta
import java.sql.PreparedStatement
import java.sql.SQLException

class VentaImpDAO: IVentaDAO {
    private val conexionBD = ConexionBD(ConstantesBD.URL, ConstantesBD.USUARIO, ConstantesBD.CONTRASENIA)

    override fun insertarLista(v:ArrayList<Venta>):ArrayList<Venta>{
        conexionBD.conectar()
        var result: Int? = null
        var ps: PreparedStatement? = null

        val query = "INSERT INTO ventas (automovil_id, cliente_id, fecha, precio_venta) VALUES (?, ?, ?, ?)"
        ps = conexionBD.getPreparedStatement(query)
        for (i in v) {
            try {
                ps?.setInt(1, i.automovil_id)
                ps?.setInt(2, i.cliente_id)
                ps?.setString(3, i.fecha)
                ps?.setDouble(4, i.precio_venta)
                result = ps?.executeUpdate()
            } catch (e: Exception) {

            } finally {
                ps?.close()
            }
        }

        return v
    }

    override fun obtenerVentaMedianteID(id:Int):Venta? {
        conexionBD.conectar()
        val query = "SELECT * FROM ventas WHERE id = ?"
        val ps = conexionBD.getPreparedStatement(query)
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
        conexionBD.desconectar()
        return venta
    }

    override fun obtenerTodasLasVentas(): List<Venta> {
        conexionBD.conectar()
        val query = "SELECT * FROM ventas"
        val st = conexionBD.getStatement()
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
        conexionBD.desconectar()
        return ventas
    }

    override fun actualizarVentas(venta: Venta): Boolean {
        conexionBD.conectar()
        var result:Int?= null
        val query = "UPDATE ventas SET id=?, automovil_id = ?, cliente_id = ?, fecha = ?, precio_venta = ? WHERE id = ?"
        val ps = conexionBD.getPreparedStatement(query)
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
            conexionBD.desconectar()
        }
        return result == 1
    }

    override fun borrarVenta(id: Int): Boolean {
        conexionBD.conectar()
        var existe = false
        var result: Int? = null
        val queryConsulta = "SELECT id FROM ventas WHERE id = ?"
        val psConsulta = conexionBD.getPreparedStatement(queryConsulta)
        psConsulta?.setInt(1, id)
        val rsConsulta = psConsulta?.executeQuery()
        if (rsConsulta?.next() == true) {
            existe = true
        }
        psConsulta?.close()

        if (existe) {
            val queryDelete = "DELETE FROM ventas WHERE id = ?"
            val psDelete = conexionBD.getPreparedStatement(queryDelete)
            psDelete?.setInt(1, id)
            result = psDelete?.executeUpdate()
            psDelete?.close()
        }

        conexionBD.desconectar()

        return result == 1
    }
}

