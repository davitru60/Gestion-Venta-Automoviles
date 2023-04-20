

import dao.AutomovilImpDAO
import entidades.Automovil
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test



internal class AutomovilImpDAOTest {

    @Test
    fun insertarAutomovil() {
        // Preparación de datos de prueba
        val automovil = Automovil("Toyota", "Corolla", 2022, "Rojo", 25000.0)

        // Ejecución del método a probar
        val dao = AutomovilImpDAO()
        val resultado = dao.insertarAutomovil(automovil)

        // Verificación de resultados
        assertTrue(resultado)
    }

    @Test
    fun eliminarAutomovil() {
        val automovilDao = AutomovilImpDAO()
        val automovil = Automovil("Chevrolet", "Spark", 2020, "Rojo", 10000.0)
        val eliminado = automovilDao.eliminarAutomovil(1)
        assertTrue(eliminado)
    }
}
