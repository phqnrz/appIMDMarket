package com.imd.imdmarket


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.imd.imdmarket.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding // variável que permite acessar os elementos do layoutXML

    override fun onCreate(savedInstanceState: Bundle?) { // metodo para criar a activity
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater) // inicializa o binding do login layoutXML
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
        val savedUsername = sharedPreferences.getString("username", "admin")
        val savedPassword = sharedPreferences.getString("password", "admin")


        binding.btnEntrar.setOnClickListener { // usado para o listener do btnEntrar
            val username = binding.userLogin.text.toString() // obtém os valores de login passados pelo usuário
            val password = binding.password.text.toString() // obtém os valores de senha passados pelo usuário

            if (username == savedUsername && password == savedPassword) { // condição que verifica se as informações inseridas pelo usuário estão corretas
                Toast.makeText(this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MenuActivity::class.java)) // caso as credenciais estejam corretas, direciona o app para a tela de Menu
                finish() // finaliza a activityLogin
            } else {
                Toast.makeText(this, "Credenciais inválidas!", Toast.LENGTH_SHORT).show() // exibe alertas na tela para informar o usuário
            }
        }

    }
}

