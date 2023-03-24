package conexion

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.SQLException

class ConexionBD {
    private val url = "jdbc:mysql://localhost/automoviles"
    private val driver = "com.mysql.cj.jdbc.Driver"
    private val usuario = "root"
    private val contrasenia = ""
    var conex: Connection? = null

    fun conectar() {
        try{
            Class.forName(driver)
            conex= DriverManager.getConnection(url,usuario,contrasenia)
        }catch(e: SQLException){
            e.printStackTrace()
        }catch (e:ClassNotFoundException){
            e.printStackTrace()
        }
    }

    fun desconectar(){
        try{
            conex?.close()
        }catch(e: SQLException){
            println("Algo fue mal cerrando la base de datos")
        }
    }

    fun getPreparedStatement(sql: String): PreparedStatement? {
        return conex?.prepareStatement(sql)
    }
}
