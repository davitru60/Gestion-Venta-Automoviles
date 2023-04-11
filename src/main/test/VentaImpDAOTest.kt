

import dao.VentaImpDAO
import entidades.Venta
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class VentaImpDAOTest{
    private val ventaDAO = VentaImpDAO()

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
