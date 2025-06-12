package model

class RecetaNoEcontradaException(id: Int) : Exception("No se encontró ninguna receta con el id: $id.")