package conexionBD

import constantes.ConstantesBD
import java.sql.*

/**
 * La clase ConexionBD se utiliza para conectarse a una base de datos y obtener objetos Statement y PreparedStatement.
 *
 * @param url La URL de la base de datos.
 * @param usuario El usuario de la base de datos.
 * @param contra La contraseña del usuario de la base de datos.
 *
 * @throws SQLException Si hay un error al intentar conectarse a la base de datos.
 * @throws ClassNotFoundException Si no se encuentra el controlador JDBC especificado en la URL.
 */

class ConexionBD {
    private var url = ""
    private var usuario = ""
    private var contrasenia = ""
    private var conex: Connection? = null

    /**
     * Crea una instancia de la clase ConexionBD.
     *
     * @param url La URL de la base de datos.
     * @param usuario El usuario de la base de datos.
     * @param contra La contraseña del usuario de la base de datos.
     */

    constructor(url:String,usuario:String,contra:String){
        this.url=url
        this.usuario=usuario
        this.contrasenia=contra

    }

    /**
     * Conecta a la base de datos utilizando la URL, usuario y contraseña proporcionados.
     */
    fun conectar() {
        try{
            Class.forName(ConstantesBD.DRIVER)
            conex= DriverManager.getConnection(url,usuario,contrasenia)
        }catch(e: SQLException){
            e.printStackTrace()
        }catch (e:ClassNotFoundException){
            e.printStackTrace()
        }
    }

    /**
     * Cierra la conexión a la base de datos.
     */
    fun desconectar(){
        try{
            conex?.close()
        }catch(e: SQLException){
            println("Algo fue mal cerrando la base de datos")
        }
    }

    /**
     * Obtiene un objeto PreparedStatement para ejecutar una consulta parametrizada en la base de datos.
     *
     * @param sql La consulta SQL parametrizada.
     * @return Un objeto PreparedStatement.
     */
    fun getPreparedStatement(sql: String): PreparedStatement? {
        return conex?.prepareStatement(sql)
    }

    /**
     * Obtiene un objeto Statement para ejecutar una consulta en la base de datos.
     *
     * @return Un objeto Statement.
     */
    fun getStatement(): Statement? {
        return conex?.createStatement()
    }
}
