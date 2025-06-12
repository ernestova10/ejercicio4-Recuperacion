package service

import model.Estadisticas
import model.Receta
import model.RecetaNoEcontradaException

class Recetario {
    private val recetas = mutableListOf<Receta>()

    fun agregarReceta(receta: Receta) {
        recetas.add(receta)
    }

    fun listarRecetas(): List<Receta> = recetas.toList()

    fun buscarPorNombre(texto: String): List<Receta> {
        return recetas.filter { it.nombre.contains(texto, ignoreCase = true) }
    }

    fun eliminarPorId(id: Int): Boolean {
        val encontrada = recetas.any {it.id == id}
        if (!encontrada) throw RecetaNoEcontradaException(id)
        return recetas.removeIf { it.id == id }
    }

    fun calcularEstadisticas(): Estadisticas {
        val total = recetas.size
        val mediaCalorias = if (total > 0) recetas.map { it.calorias }.average() else 0.0
        val totalVeganas = recetas.count { it.esVegana }
        return Estadisticas(total, mediaCalorias, totalVeganas)
    }
}
