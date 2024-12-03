package com.imd.imdmarket

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.imd.imdmarket.databinding.ActivityReadBinding

class ReadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReadBinding // variável que permite acessar os elementos do layoutXML

    override fun onCreate(savedInstanceState: Bundle?) { // metodo para criar a activity
        super.onCreate(savedInstanceState)
        binding = ActivityReadBinding.inflate(layoutInflater) // inicializa o binding do read layoutXML
        setContentView(binding.root)

        ProdutoRepository.init(this) // inicia o repositório dos produtos

        val produtos = ProdutoRepository.produtos // carrega os produtos salvos

        // configura o ArrayAdapter para exibir os produtos, transformando os objetos numa string a ser exibida na listview
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            produtos.map { "${it.codigo} - ${it.nome} - Estoque: ${it.estoque}" } // exibe código, nome e estoque
        )
        binding.listView.adapter = adapter // exibe os produtos na interface


        binding.btnVoltar.setOnClickListener { // usado para o listener do btnVoltar
            finish() // fecha a activity
        }
    }
}
