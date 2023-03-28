package dao
import conexionBD.ConexionBD
import conexionBD.ConstantesBD
import no_dao.Automovil
import no_dao.ConstantesBDAutomovil

/**
 * Esta clase implementa la interfaz IAutomovilDAO y proporciona la funcionalidad
 * para acceder a los datos de los automóviles en la capa de persistencia o capa de datos.
 */
class AutomovilImpDAO:IAutomovilDAO {
    private val conexionBD = ConexionBD(ConstantesBD.URL,ConstantesBD.USUARIO,ConstantesBD.CONTRASENIA)


    /**
     * Inserta un objeto Automovil en la base de datos.
     *
     * @param automovil el objeto Automovil que se va a insertar
     * @return true si se ha insertado correctamente, false en caso contrario
     */
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

    /**
     * Actualiza el precio de un objeto Automovil en la base de datos.
     *
     * @param automovil el objeto Automovil cuyo precio se va a actualizar
     * @return true si se ha actualizado correctamente, false en caso contrario
     */
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

    /**
     * Comprueba si existe un registro en la tabla AUTOMOVILES de la base de datos con el ID especificado.
     *
     * @param id el ID del registro a comprobar
     * @return el número de registros encontrados con el ID especificado (debería ser 0 o 1)
     */
    fun comprobarExistenciaDelRegistroPorID(id:Int):Int{
        val consultaExistencia = "SELECT COUNT(*) FROM AUTOMOVILES WHERE ID=?"
        val preparedStatementExistencia = conexionBD.getPreparedStatement(consultaExistencia)
        preparedStatementExistencia?.setInt(1, id)
        val resultSetExistencia = preparedStatementExistencia?.executeQuery()
        resultSetExistencia?.next()
        val countExistencia = resultSetExistencia?.getInt(1) ?: 0
        return countExistencia
    }


    /**
     * Obtiene una lista de objetos Automovil cuyo precio se encuentra dentro de un rango específico.
     *
     * @param limiteInferior el límite inferior del rango de precios.
     * @param limiteSuperior el límite superior del rango de precios.
     * @return una lista de objetos Automovil cuyo precio se encuentra dentro del rango especificado, o null si se produce un error.
     */
    override fun obtenerAutomovilPorRangoDePrecio(limiteInferior: Double, limiteSuperior: Double): ArrayList<Automovil>? {
        conexionBD.conectar()
        val automoviles=ArrayList<Automovil>()
        try{
            val consulta="SELECT * FROM AUTOMOVILES WHERE PRECIO BETWEEN ? AND ? ORDER BY PRICE"
            val preparedStatement=conexionBD.getPreparedStatement(consulta)
            preparedStatement?.setDouble(1,limiteInferior)
            preparedStatement?.setDouble(2,limiteSuperior)
            val resultSet=preparedStatement?.executeQuery()
            while(resultSet?.next()==true){
                val automovil=Automovil(resultSet.getInt(ConstantesBDAutomovil.ID),resultSet.getString(ConstantesBDAutomovil.MARCA),resultSet.getString(ConstantesBDAutomovil.MODELO),
                    resultSet.getInt(ConstantesBDAutomovil.ANO),resultSet.getString(ConstantesBDAutomovil.COLOR),resultSet.getDouble(ConstantesBDAutomovil.PRECIO))
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

    /**
     * Obtiene una lista de automóviles por marca desde la base de datos.
     *
     * @param marca la marca de los automóviles a buscar
     * @return una lista de automóviles que pertenecen a la marca especificada
     */
    override fun obtenerAutomovilesPorMarca(marca: String): ArrayList<Automovil>? {
        conexionBD.conectar()
        val automoviles=ArrayList<Automovil>()
        try{
            val consulta="SELECT * FROM AUTOMOVILES WHERE marca=?"
            val preparedStatement=conexionBD.getPreparedStatement(consulta)
            preparedStatement?.setString(1,marca)
            val resultSet=preparedStatement?.executeQuery()
            while(resultSet?.next()==true){
                val automovil=Automovil(resultSet.getInt(ConstantesBDAutomovil.ID),resultSet.getString(ConstantesBDAutomovil.MARCA),resultSet.getString(ConstantesBDAutomovil.MODELO),
                    resultSet.getInt(ConstantesBDAutomovil.ANO),resultSet.getString(ConstantesBDAutomovil.COLOR),resultSet.getDouble(ConstantesBDAutomovil.PRECIO))
                automoviles.add(automovil)
            }

            return automoviles
        }catch(e:Exception){
            println("Algo no fue bien")
        }
        return null
    }

    /**
     * Obtiene todos los automóviles almacenados en la base de datos.
     * @return Un ArrayList de objetos Automovil que contiene todos los automóviles almacenados en la base de datos.
     *         Si no se pueden obtener los automóviles, se devuelve null.
     */
    override fun obtenerTodosLosAutomoviles(): ArrayList<Automovil>? {
        conexionBD.conectar()
        val automoviles=ArrayList<Automovil>()
        try{
            val consulta="SELECT * FROM AUTOMOVILES"
            val preparedStatement=conexionBD.getPreparedStatement(consulta)
            val resultSet=preparedStatement?.executeQuery()
            while(resultSet?.next()==true){
                val automovil=Automovil(resultSet.getInt(ConstantesBDAutomovil.ID),resultSet.getString(ConstantesBDAutomovil.MARCA),resultSet.getString(ConstantesBDAutomovil.MODELO),
                    resultSet.getInt(ConstantesBDAutomovil.ANO),resultSet.getString(ConstantesBDAutomovil.COLOR),resultSet.getDouble(ConstantesBDAutomovil.PRECIO))
                automoviles.add(automovil)
            }

            return automoviles
        }catch(e:Exception){
            println("Algo no fue bien")
        }
        return null
    }

    /**
     * Elimina un automóvil de la base de datos a partir de su ID.
     * @param id El ID del automóvil a eliminar.
     * @return true si el automóvil fue eliminado exitosamente, false si ocurrió algún error.
     */
    override fun eliminarAutomovil(id: Int): Boolean {
        conexionBD.conectar()
        var ejecucionConsulta:Int
        var resultado:Int?=1
        try{
            //Eliminar el registro
            val consulta="DELETE FROM AUTOMOVILES WHERE ID=?"
            val preparedStatement=conexionBD.getPreparedStatement(consulta)
            preparedStatement?.setInt(1,id)

            resultado=preparedStatement?.executeUpdate()
            ejecucionConsulta=1
            preparedStatement?.close()

            conexionBD.desconectar()

        }catch(e:Exception){
            println("Error al eliminar el automovil:${e.message}")
            ejecucionConsulta=0
            conexionBD.desconectar()
        }
        return resultado==ejecucionConsulta
    }
}