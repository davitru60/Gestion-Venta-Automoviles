package dao
import conexion.ConexionBD
import no_dao.Automovil

class AutomovilImpDAO:IAutomovilDAO {
    private val conexionBD = ConexionBD()

    override fun insertarAutomovil(automovil: Automovil): Boolean {
        conexionBD.conectar()
        var ejecucionConsulta:Int
        var resultado:Int?=1
        try{
            val consulta="INSERT INTO AUTOMOVILES (marca,modelo,ano,color,precio) " +
                    "VALUES(?,?,?,?,?)"
            val preparedStatement=conexionBD.getPreparedStatement(consulta)
            preparedStatement?.setString(1,automovil.marca)
            preparedStatement?.setString(2,automovil.modelo)
            preparedStatement?.setInt(3,automovil.ano)
            preparedStatement?.setString(4,automovil.color)
            preparedStatement?.setDouble(5,automovil.precio)

            resultado= preparedStatement?.executeUpdate()
            ejecucionConsulta=1

            preparedStatement?.close()
            conexionBD.desconectar()

        }catch(e:Exception){
            println("Error al insertar el automovil:${e.message}")
            e.printStackTrace()
            conexionBD.desconectar()
            ejecucionConsulta=0
        }
        return resultado==ejecucionConsulta
    }

    override fun actualizarAutomovil(automovil: Automovil): Boolean {

    }

    override fun eliminarAutomovil(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun obtenerAutomovilPorPrecio(precio: Double): Boolean {
        TODO("Not yet implemented")
    }

    override fun obtenerAutomovilesPorMarca(marca: String): Boolean {
        TODO("Not yet implemented")
    }
}