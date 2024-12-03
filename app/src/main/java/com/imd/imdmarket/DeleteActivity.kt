package com.imd.imdmarket

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.imd.imdmarket.databinding.ActivityDeleteBinding

class DeleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteBinding // variável que permite acessar os elementos do layoutXML

    override fun onCreate(savedInstanceState: Bundle?) { // metodo para criar a activity
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater) // inicializa o binding do delete layoutXML
        setContentView(binding.root)

        ProdutoRepository.init(this) // inicia o repositório dos produtos

        binding.btnDeletar.setOnClickListener { // usado para o listener do btnDeletar
            val codigo = binding.codProduto.text.toString() // recebe o codigo do produto informado pelo usuário

            val produto = ProdutoRepository.produtos.find { it.codigo == codigo } // busca o produto pelo código informado

            // condição que verifica se o produto existe ou não
            if (produto != null) {
                // remove o produto e salva as alterações
                ProdutoRepository.produtos.remove(produto)
                ProdutoRepository.saveProdutos()

                Toast.makeText(this, "Produto excluído com sucesso!", Toast.LENGTH_SHORT).show() // exibe alertas na tela para informar o usuário
                finish() // encerra a tela após a exclusão
            } else {
                Toast.makeText(this, "Produto não encontrado!", Toast.LENGTH_SHORT).show() // exibe alertas na tela para informar o usuário
            }
        }
    }
}
