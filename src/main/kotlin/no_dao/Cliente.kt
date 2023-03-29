package no_dao

class Cliente {
    var id:Int=0
    var nombre:String
    var apellido:String
    var email:String
    var telefono:String
    /**
     * Crea una instancia de la clase Cliente con todos los atributos inicializados.
     * @param id el identificador único del cliente
     * @param nombre el nombre del cliente
     * @param apellido el apellido del cliente
     * @param email el correo electrónico del cliente
     * @param telefono el número de teléfono del cliente
     */
    constructor(id:Int,nombre:String,apellido:String,email:String,telefono:String){
        this.id=id
        this.nombre=nombre
        this.apellido=apellido
        this.email=email
        this.telefono=telefono
    }

    /**
     * Crea una instancia de la clase Cliente con todos los atributos excepto el identificador inicializados.
     * El identificador se inicializa a 0.
     * @param nombre el nombre del cliente
     * @param apellido el apellido del cliente
     * @param email el correo electrónico del cliente
     * @param telefono el número de teléfono del cliente
     */
    constructor(nombre:String,apellido:String,email:String,telefono:String){
        this.nombre=nombre
        this.apellido=apellido
        this.email=email
        this.telefono=telefono
    }

    /**
     * Devuelve una representación en forma de cadena de texto del objeto Cliente.
     * @return una cadena de texto con los valores de todos los atributos del cliente
     */
    override fun toString(): String {
        return "ID:$id, nombre:$nombre, apellido:$apellido, email:$email, telefono:$telefono"
    }
}