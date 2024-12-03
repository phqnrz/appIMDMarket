package com.imd.imdmarket

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.imd.imdmarket.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding // variável que permite acessar os elementos do layoutXML

    override fun onCreate(savedInstanceState: Bundle?) { // metodo para criar a activity
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater) // inicializa o binding do update layoutXML
        setContentView(binding.root)

        val produtos = mutableListOf<Produto>() // cria uma lista para armazenar os produtos

        binding.btnAlterar.setOnClickListener { // usado para o listener do btnAlterar
            val codigo = binding.codProduto.text.toString()  // recebe o texto inserido no código do produto
            val produto = ProdutoRepository.produtos.find { it.codigo == codigo } // ação que busca os produtos no repositório por um código igual ao informado pelo usuário


            // condição para atualizar os dados dos produtos; caso vazio, mantém o valor atual
            if (produto != null) {
                produto.nome = binding.nomeProduto.text.toString().takeIf { it.isNotEmpty() } ?: produto.nome
                produto.descricao = binding.descricaoProduto.text.toString().takeIf { it.isNotEmpty() } ?: produto.descricao
                produto.estoque = binding.estoqueProduto.text.toString().toIntOrNull() ?: produto.estoque

                ProdutoRepository.saveProdutos() // metodo para salvar os dados no repositório dos produtos
                Toast.makeText(this, "Produto alterado com sucesso!", Toast.LENGTH_SHORT).show() // exibe alertas na tela para informar o usuário
                finish() // fecha a activity após salvar
            } else {
                Toast.makeText(this, "Produto não encontrado!", Toast.LENGTH_SHORT).show() // exibe alertas na tela para informar o usuário
            }
        }

        binding.btnLimpar.setOnClickListener { // usado para o listener do btnLimpar
            binding.codProduto.text.clear() // limpa os dados inseridos no código do produto
            binding.nomeProduto.text.clear() // limpa os dados inseridos no nome do produto
            binding.descricaoProduto.text.clear() // limpa os dados inseridos na descrição do produto
            binding.estoqueProduto.text.clear() // limpa os dados inseridos no estoque do produto
            Toast.makeText(this, "Campos limpos!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getProdutos(context: Context): MutableList<Produto> {
        val sharedPreferences = context.getSharedPreferences("produtos", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("produtos", null)
        val type = object : TypeToken<MutableList<Produto>>() {}.type
        return gson.fromJson(json, type) ?: mutableListOf()
    }
}
