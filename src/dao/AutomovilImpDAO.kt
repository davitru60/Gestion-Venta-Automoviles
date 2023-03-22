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

    override fun actualizarPrecioAutomovil(automovil: Automovil): Boolean {
        conexionBD.conectar()
        var ejecucionConsulta:Int
        var resultado:Int?=1

        try{
            val consulta="UPDATE AUTOMOVILES SET PRECIO=? WHERE ID=?"
            val preparedStatement=conexionBD.getPreparedStatement(consulta)
            preparedStatement?.setDouble(1,automovil.precio)
            preparedStatement?.setInt(2,automovil.id)

            resultado=preparedStatement?.executeUpdate()
            ejecucionConsulta=1

            preparedStatement?.close()
            conexionBD.desconectar()

        }catch(e:Exception){
            println("Error al editar el automovil:${e.message}")
            e.printStackTrace()
            conexionBD.desconectar()
            ejecucionConsulta=0
        }

        return resultado==ejecucionConsulta
    }


    override fun obtenerAutomovilPorRangoDePrecio(limiteInferior: Double, limiteSuperior: Double): ArrayList<Automovil>? {
        conexionBD.conectar()
        var automoviles=ArrayList<Automovil>()
        try{
            val consulta="SELECT * FROM AUTOMOVILES WHERE PRECIO BETWEEN ? AND ? "
            val preparedStatement=conexionBD.getPreparedStatement(consulta)
            preparedStatement?.setDouble(1,limiteInferior)
            preparedStatement?.setDouble(2,limiteSuperior)
            val resultSet=preparedStatement?.executeQuery()
            while(resultSet?.next()==true){
                val automovil=Automovil(resultSet.getInt("id"),resultSet.getString("marca"),resultSet.getString("modelo"),
                    resultSet.getInt("ano"),resultSet.getString("color"),resultSet.getDouble("precio"))
                automoviles.add(automovil)
            }
            preparedStatement?.close()
            conexionBD.desconectar()

            return automoviles


        }catch(e:Exception){
            println("Algo no fue bien")
            conexionBD.desconectar()
        }
        return null
    }

    override fun obtenerAutomovilesPorMarca(marca: String): ArrayList<Automovil>? {
        conexionBD.conectar()
        var automoviles=ArrayList<Automovil>()
        try{
            val consulta="SELECT * FROM AUTOMOVILES WHERE marca=?"
            val preparedStatement=conexionBD.getPreparedStatement(consulta)
            preparedStatement?.setString(1,marca)
            val resultSet=preparedStatement?.executeQuery()
            while(resultSet?.next()==true){
                val automovil=Automovil(resultSet.getInt("id"),resultSet.getString("marca"),resultSet.getString("modelo"),
                    resultSet.getInt("ano"),resultSet.getString("color"),resultSet.getDouble("precio"))
                automoviles.add(automovil)
            }

            return automoviles
        }catch(e:Exception){
            println("Algo no fue bien")
        }
        return null
    }

    override fun obtenerTodosLosAutomoviles(): ArrayList<Automovil>? {
        conexionBD.conectar()
        var automoviles=ArrayList<Automovil>()
        try{
            val consulta="SELECT * FROM AUTOMOVILES"
            val preparedStatement=conexionBD.getPreparedStatement(consulta)
            val resultSet=preparedStatement?.executeQuery()
            while(resultSet?.next()==true){
                val automovil=Automovil(resultSet.getInt("id"),resultSet.getString("marca"),resultSet.getString("modelo"),
                    resultSet.getInt("ano"),resultSet.getString("color"),resultSet.getDouble("precio"))
                automoviles.add(automovil)
            }

            return automoviles
        }catch(e:Exception){
            println("Algo no fue bien")
        }
        return null
    }

    override fun eliminarAutomovil(id: Int): Boolean {
        conexionBD.conectar()
        var ejecucionConsulta:Int
        var resultado:Int?=1
        try{
            val consulta="DELETE FROM AUTOMOVILES WHERE ID=?"
            val preparedStatement=conexionBD.getPreparedStatement(consulta)
            preparedStatement?.setInt(1,id)

            resultado=preparedStatement?.executeUpdate()
            ejecucionConsulta=1

            preparedStatement?.close()
            conexionBD.desconectar()

        }catch(e:Exception){
            ejecucionConsulta=0
            conexionBD.desconectar()
        }
        return resultado==ejecucionConsulta
    }
}