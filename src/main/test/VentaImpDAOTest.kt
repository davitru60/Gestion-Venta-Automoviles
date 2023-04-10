import dao.VentaImpDAO
import no_dao.Venta
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class VentaImpDAOTest{
    private val ventaDAO = VentaDAO()

    @Test
    fun testActualizarVentas() {
        val venta = Venta(1, 1, 1, "2023-03-30", 1000.0)
        val result = ventaDAO.actualizarVentas(venta)
        assertTrue(result)
    }

    @Test
    fun testBorrarVenta() {
        val id = 1
        val result = ventaDAO.borrarVenta(id)
        assertTrue(result)
    }
}
