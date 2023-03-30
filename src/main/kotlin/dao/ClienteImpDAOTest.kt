package dao

import conexionBD.ConexionBD
import no_dao.Automovil
import no_dao.Cliente
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class ClienteImpDAOTest{


    class MiClaseDePrueba {

        @Test
        fun aniadirCliente() {
            // Preparación de datos de prueba
            val cliente = Cliente("Raúl", "Gutiérrez Merino", "correoDePrueba@gmail.com", "123456789")

            // Ejecución del método a probar
            val dao = ClienteImpDAO()
            val resultado = dao.aniadirCliente(cliente)
        }

        @Test
        fun eliminarCliente() {
            val ClienteDao = ClienteImpDAO()
            val cliente = Cliente(0,"Raúl", "Gutiérrez Merino", "correoDePrueba@gmail.com", "123456789")
            val eliminado = ClienteDao.eliminarCliente(0)
        }
    }
}