package no_dao

class Cliente {
    var id:Int=0
    var nombre:String
    var apellido:String
    var email:String
    var telefono:String
    constructor(id:Int,nombre:String,apellido:String,email:String,telefono:String){
        this.id=id
        this.nombre=nombre
        this.apellido=apellido
        this.email=email
        this.telefono=telefono
    }
    constructor(nombre:String,apellido:String,email:String,telefono:String){
        this.nombre=nombre
        this.apellido=apellido
        this.email=email
        this.telefono=telefono
    }

    override fun toString(): String {
        return "ID:$id, nombre:$nombre, apellido:$apellido, email:$email, telefono:$telefono"
    }


}