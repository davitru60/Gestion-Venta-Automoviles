package no_dao

class Cliente {
    var id:Int=0
    var nombre:String
    var apellido:String
    var email:String
    var telefono:Int
    constructor(id:Int,nombre:String,apellido:String,email:String,telefono:Int){
        this.id=id
        this.nombre=nombre
        this.apellido=apellido
        this.email=email
        this.telefono=telefono
    }
    constructor(nombre:String,apellido:String,email:String,telefono:Int){
        this.nombre=nombre
        this.apellido=apellido
        this.email=email
        this.telefono=telefono
    }

}