package com.imd.imdmarket


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) { // metodo para criar a activity
        super.onCreate(savedInstanceState)
        ProdutoRepository.init(this) // inicia o repositório para armazenar os dados
        startActivity(Intent(this, LoginActivity::class.java)) // inicia a activity de login  para carregar na tela para o usuário
        finish()
    }
}