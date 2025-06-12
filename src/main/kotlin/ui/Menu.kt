package ui

import model.Receta
import service.Recetario

class MenuConsola(private val recetario: Recetario) {
    fun mostrarMenu() {
        var opcion: Int
        do {
            println("\n=== GESTOR DE RECETAS LIGHT ===")
            println("1. Agregar receta")
            println("2. Listar recetas")
            println("3. Buscar por nombre")
            println("4. Eliminar por ID")
            println("5. Ver estadísticas")
            println("0. Salir")
            print("Seleccione una opción: ")
            opcion = readln().toIntOrNull() ?: -1

            when (opcion) {
                1 -> agregarReceta()
                2 -> listarRecetas()
                3 -> buscarPorNombre()
                4 -> eliminarPorId()
                5 -> mostrarEstadisticas()
                0 -> println("Saliendo del programa...")
                else -> println("Opción inválida")
            }
        } while (opcion != 0)
    }

    private fun agregarReceta() {
        try {
            print("Nombre: ")
            val nombre = readln()
            print("Calorías: ")
            val calorias = readln().toInt()
            print("Ingredientes (separados por coma): ")
            val ingredientes = readln().split(",").map { it.trim() }
            print("¿Es vegana? (s/n): ")
            val esVegana = readln().lowercase() == "s"

            val receta = Receta(
                id = Receta.generarId(),
                nombre = nombre,
                calorias = calorias,
                ingredientes = ingredientes,
                esVegana = esVegana
            )
            recetario.agregarReceta(receta)
            println("Receta agregada correctamente.")
        }catch (e: IllegalArgumentException){
            println("Error: ${e.message}")
        }

    }

    private fun listarRecetas() {
        val recetas = recetario.listarRecetas()
        if (recetas.isEmpty()) {
            println("No hay recetas registradas.")
        } else {
            recetas.forEach { println(it) }
        }
    }



    private fun buscarPorNombre() {
        print("Texto a buscar: ")
        val texto = readln()
        val resultados = recetario.buscarPorNombre(texto)
        if (resultados.isEmpty()) {
            println("No se encontraron recetas.")
        } else {
            resultados.forEach { println(it) }
        }
    }

    private fun eliminarPorId() {
        print("ID a eliminar: ")
        val id = readln().toIntOrNull()
        try {
            if (id != null && recetario.eliminarPorId(id)) {
                println("Receta eliminada.")
            } else {
                println("No se encontró receta con ese ID.")
            }
        }catch (e: IllegalArgumentException){
            println("Error: ${e.message}")
        }

    }

    private fun mostrarEstadisticas() {
        val stats = recetario.calcularEstadisticas()
        println("Total de recetas: ${stats.totalRecetas}")
        println("Media de calorías: ${"%.2f".format(stats.mediaCalorias)}")
        println("Total de recetas veganas: ${stats.totalVeganas}")
    }
}
