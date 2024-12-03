package com.imd.imdmarket

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.imd.imdmarket.databinding.ActivityCreateBinding

class CreateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateBinding // variável que permite acessar os elementos do layoutXML

    override fun onCreate(savedInstanceState: Bundle?) { // metodo para criar a activity
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater) // inicializa o binding do create layoutXML
        setContentView(binding.root)


        ProdutoRepository.init(this) // inicia o repositório para armazenar os dados

        binding.btnSalvar.setOnClickListener { // usado para o listener do btnSalvar
            val codigo = binding.codProduto.text.toString() // recebe o texto inserido no código do produto
            val nome = binding.nomeProduto.text.toString() // recebe o texto inserido no nome do produto
            val descricao = binding.descricaoProduto.text.toString() // recebe o texto inserido na descrição do produto
            val estoque = binding.estoqueProduto.text.toString().toIntOrNull() // recebe o texto inserido no estoque do produto

            if (codigo.isNotEmpty() && nome.isNotEmpty() && descricao.isNotEmpty() && estoque != null) { // condição que verifica que se os campos dos produtos não estão vazios e realiza o armazenamento das informações
                if (ProdutoRepository.produtos.none { it.codigo == codigo }) {
                    // adiciona os dados no repositório de produtos
                    ProdutoRepository.produtos.add(
                        Produto(codigo, nome, descricao, estoque)
                    )
                    ProdutoRepository.saveProdutos() // metodo para salvar os dados no repositório dos produtos
                    Toast.makeText(this, "Produto cadastrado!", Toast.LENGTH_SHORT).show() // exibe alertas na tela para informar o usuário
                    finish() // fecha a activity após salvar
                } else {
                    Toast.makeText(this, "Código já existente!", Toast.LENGTH_SHORT).show() // exibe alertas na tela para informar o usuário
                }
            } else {
                Toast.makeText(this, "Preencha todos os campos corretamente!", Toast.LENGTH_SHORT).show() // exibe alertas na tela para informar o usuário
            }
        }

        binding.btnLimpar.setOnClickListener {  // usado para o listener do btnLimpar
            binding.codProduto.text.clear() // limpa os dados inseridos no código do produto
            binding.nomeProduto.text.clear() // limpa os dados inseridos no nome do produto
            binding.descricaoProduto.text.clear() // limpa os dados inseridos na descrição do produto
            binding.estoqueProduto.text.clear() // limpa os dados inseridos no estoque do produto
            Toast.makeText(this, "Campos limpos!", Toast.LENGTH_SHORT).show()
        }
    }
}
