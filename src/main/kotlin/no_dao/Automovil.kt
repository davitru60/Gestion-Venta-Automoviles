package no_dao
/*
* Esta clase representa la entidad Automovil de la base de datos, por lo tanto
* tiene los mismos atributos que la tabla automoviles de la base de datos
* */
class Automovil {
    var id:Int
    var marca:String
    var modelo:String
    var ano:Int
    var color:String
    var precio:Double

    constructor(id:Int,marca:String,modelo:String,ano:Int,color:String,precio:Double){
        this.id=id
        this.marca=marca
        this.modelo=modelo
        this.ano=ano
        this.color=color
        this.precio=precio
    }


    constructor(marca:String,modelo:String,ano:Int,color:String,precio:Double){
        this.id=0
        this.marca=marca
        this.modelo=modelo
        this.ano=ano
        this.color=color
        this.precio=precio
    }

    constructor(id:Int,precio:Double){
        this.id=id
        this.marca=""
        this.modelo=""
        this.ano=0
        this.color=""
        this.precio=precio
    }

    override fun toString(): String {
        return "ID:$id, marca:$marca, modelo:$modelo, año:$ano, color:$color, precio:$precio€"
    }


}