package conexionBD

import java.sql.*



class ConexionBD {
    private var url = ""
    private var usuario = ""
    private var contrasenia = ""
    private var conex: Connection? = null

    constructor(url:String,usuario:String,contra:String){
        this.url=url
        this.usuario=usuario
        this.contrasenia=contra

    }

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

    fun getStatement(): Statement? {
        return conex?.createStatement()
    }
}
