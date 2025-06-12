package model

data class Receta(
    val id: Int,
    val nombre: String,
    val calorias: Int,
    val ingredientes: List<String>,
    val esVegana: Boolean
) {
    init {
        require(calorias > 0) {"Las calorías deben ser mayor que 0"}
    }
    companion object {
        private var contadorId = 1
        fun generarId(): Int = contadorId++
    }
}
