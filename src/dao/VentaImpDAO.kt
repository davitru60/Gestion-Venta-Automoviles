package dao

class VentaImpDAO: IVentaDAO {
    override fun insertarLista(v:ArrayList<Venta>):ArrayList<Venta> {
        conexion.conectar()
        var result: Int? = null
        var ps: PreparedStatement? = null
        val listaNoInsertados = ArrayList<Venta>()

        val query = "INSERT INTO ventas (automovil_id, cliente_id, fecha, precio_venta) VALUES (?, ?, ?, ?)"
        ps = conexion.getPreparedStatement(query)
        for (venta in ventas) {
            try {
                ps?.setInt(1, venta.automovil_id)
                ps?.setInt(2, venta.cliente_id)
                ps?.setDate(3, java.sql.Date.valueOf(venta.fecha))
                ps?.setBigDecimal(4, venta.precio_venta)
                result = ps?.executeUpdate()
            } catch (e: Exception) {
                listaNoInsertados.add(venta)
            }
        }
        ps?.close()
        conexion.desconectar()
        return listaNoInsertados
    }

    override fun getVentaById(id: Int): Venta? {
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
                rs.getDate("fecha").toLocalDate(),
                rs.getBigDecimal("precio_venta")
            )
        }
        ps?.close()
        conexion.desconectar()
        return venta
    }

    override fun getAllVentas(): List<Venta> {
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
                rs.getDate("fecha").toLocalDate(),
                rs.getBigDecimal("precio_venta")
            )
            ventas.add(venta)
        }
        st?.close()
        conexion.desconectar()
        return ventas
    }

    override fun insertVenta(venta: Venta): Boolean {
        var result: Int? = null
        var ps: PreparedStatement? = null
        try {
            conexion.conectar()
            val query = "INSERT INTO ventas (automovil_id, cliente_id, fecha, precio_venta) VALUES (?, ?, ?, ?)"
            ps = conexion.getPreparedStatement(query)
            ps?.setInt(1, venta.automovilId)
            ps?.setInt(2, venta.clienteId)
            ps?.setDate(3, Date.valueOf(venta.fecha))
            ps?.setBigDecimal(4, venta.precioVenta)
            result = ps?.executeUpdate()
        } catch (e: SQLException) {
            println(e.message)
        } finally {
            ps?.close()
            conexion.desconectar()
        }
        return result == 1
    }

    override fun updateVenta(venta: Venta): Boolean {
        conexion.conectar()
        val query = "UPDATE ventas SET automovil_id = ?, cliente_id = ?, fecha = ?, precio_venta = ? WHERE id = ?"
        val ps = conexion.getPreparedStatement(query)
        ps?.setInt(1, venta.automovilId)
        ps?.setInt(2, venta.clienteId)
        ps?.setDate(3, Date.valueOf(venta.fecha))
        ps?.setBigDecimal(4, venta.precioVenta)
        ps?.setInt(5, venta.id)
        val result = ps?.executeUpdate()
        ps?.close()
        conexion.desconectar()
        return result == 1
    }

    override fun deleteVenta(id: Int): Boolean {
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

}