package com.imd.imdmarket

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ProdutoRepository {  // define o repositório como um singleton
    private const val FILE_NAME = "produtos.json" // variável com nome do arquivo em que os produtos serão armazenados
    private lateinit var context: Context // variável para acessar os recursos I/O
    val produtos = mutableListOf<Produto>() // lista que armazena os produtos carregados ou manipulados na aplicação


    // função que inicializa o repositório com o contexto da aplicação
    fun init(context: Context) {
        this.context = context
        loadProdutos()
    }

    // função que carrega os produtos do arquivo JSON
    private fun loadProdutos() {
        try {
            val file = context.getFileStreamPath(FILE_NAME) // verifica a existência do arquivo

            if (file.exists()) {
                val json = context.openFileInput(FILE_NAME).bufferedReader().use { it.readText() } // faz a leitura do arquivo JSON e converte para uma string
                val type = object : TypeToken<MutableList<Produto>>() {}.type
                val loadedProdutos: MutableList<Produto> = Gson().fromJson(json, type) // converte a string JSON em uma lista de objetos Produto
                produtos.clear() // limpa a lista de produtos
                produtos.addAll(loadedProdutos) // adiciona os produtos carregados
            }
        } catch (e: Exception) {
            e.printStackTrace() // tratamento de erros no log
        }
    }

    // função que salva os produtos no arquivo JSON
    fun saveProdutos() {
        try {
            val json = Gson().toJson(produtos) // converte a lista produtos para uma string JSON
            context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use { // abre a o arquivo de maneira que apenas o app pode acessá-lo
                it.write(json.toByteArray()) // escreve o JSON no arquivo
            }
        } catch (e: Exception) {
            e.printStackTrace() // tratamento de erros no log
        }
    }
}
