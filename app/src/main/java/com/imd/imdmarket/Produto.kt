package com.imd.imdmarket

data class Produto(
    val codigo: String,
    var nome: String,
    var descricao: String,
    var estoque: Int
)

object ListaProdutos {
    fun add(produto: Produto) {
    }
    val lista = mutableListOf<Produto>()
}
