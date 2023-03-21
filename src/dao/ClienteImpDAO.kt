package dao

class ClienteImpDAO : IClienteDAO{
    override fun añadirCliente(){
        val sql= "insert into clientes values ('','','','');"
    }
    override fun eliminarCliente(){
        val sql= "insert into clientes values ('','','','');"
    }
    override fun verListaCliente(){
        val sql= "select * from clientes;"
    }
    override fun BuscarCliente(){
        val sql= "select * from clientes where nombre='';"
    }
}