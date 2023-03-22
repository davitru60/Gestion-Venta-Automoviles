package no_dao

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

    override fun toString(): String {
        return "Marca:$marca, modelo:$modelo, año:$ano, color:$color, precio:$precio€"
    }


}