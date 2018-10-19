package com.ganiko.vitor.spajulioganiko

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cliente.*
import kotlinx.android.synthetic.main.activity_tela_cadastro.*

class TelaCadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_cadastro)

        supportActionBar?.title = "Cadastro De Clientes"

        botaoCadastro.setOnClickListener {
            val cliente = Clientes()
            cliente.nome = campo_Cadastro_Cliente.text.toString()
            cliente.email = campo_Cadastro_Email.text.toString()
            cliente.telefone = campo_Cadastro_fone.text.toString()
            cliente.foto = campo_Foto.text.toString()

            taskAtualizar(cliente)
        }


    }


    private fun taskAtualizar(cliente: Clientes) {
        // Thread para salvar a discilpina
        Thread {
            ClientesService.save(cliente)
            runOnUiThread {
                // após cadastrar, voltar para activity anterior
                finish()
            }
        }.start()
    }




 /*   private fun OnClickCadastrar() {

        val campoCliente = findViewById<EditText>(R.id.campo_Cadastro_Cliente)
        val valorCliente = campoCliente.text.toString()

        val intent = Intent(this, TelaInicialActivity::class.java)

        val paramss = Bundle()
        paramss.putString("Cliente cadastrado:", "$valorCliente")
        intent.putExtras(paramss)


        startActivityForResult(intent, 15)
        //15 é a tela cadastro

        Toast.makeText(this, "Cliente cadastrado: $valorCliente", Toast.LENGTH_SHORT).show()



    }*/


}
