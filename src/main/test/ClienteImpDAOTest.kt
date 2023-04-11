
import dao.ClienteImpDAO
import entidades.Cliente
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class ClienteImpDAOTest{


    class MiClaseDePrueba {

        @Test
        fun aniadirCliente() {
            // Preparación de datos de prueba
            val cliente = Cliente("Raúl", "Gutiérrez Merino", "correoDePrueba@gmail.com", "123456789")

            // Ejecución del método a probar
            val dao = ClienteImpDAO()
            val resultado = dao.aniadirCliente(cliente)
            assertTrue(resultado)
        }

        @Test
        fun eliminarCliente() {
            val ClienteDao = ClienteImpDAO()
            val eliminado = ClienteDao.eliminarCliente(0)
            assertTrue(eliminado)
        }
    }
}