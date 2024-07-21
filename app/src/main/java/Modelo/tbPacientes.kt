package Modelo

data class tbPacientes(
    val uuid: String,
    var nombre: String,
    val apellido: String,
    val edad: Int,
    val enfermedad: String,
    val habitacion_numero: Int,
    val cama_numero: Int,
    val medicamento: String,
    val horaAplicacion: String
)
