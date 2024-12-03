package com.imd.imdmarket

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.imd.imdmarket.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding // variável que permite acessar os elementos do layoutXML

    override fun onCreate(savedInstanceState: Bundle?) { // metodo para criar a activity
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater) // inicializa o binding do login layoutXML
        setContentView(binding.root)

        binding.btnCadastro.setOnClickListener { // usado para o listener do btnCadastro
            startActivity(Intent(this, CreateActivity::class.java)) // ao ser clicado, direciona o app para a tela de criação de produto
        }

        binding.btnListar.setOnClickListener { // usado para o listener do btnListar
            startActivity(Intent(this, ReadActivity::class.java)) // ao ser clicado, direciona o app para a tela de listagem de produtos
        }

        binding.btnAlterar.setOnClickListener { // usado para o listener do btnAlterar
            startActivity(Intent(this, UpdateActivity::class.java)) // ao ser clicado, direciona o app para a tela de alteração de produto
        }

        binding.btnDeletar.setOnClickListener { // usado para o listener do btnDeletar
            startActivity(Intent(this, DeleteActivity::class.java)) // ao ser clicado, direciona o app para a tela de exclusão de produto
        }

        binding.btnSair.setOnClickListener { // usado para o listener do btnSair
            finish() // finaliza o programa
        }
    }
}
