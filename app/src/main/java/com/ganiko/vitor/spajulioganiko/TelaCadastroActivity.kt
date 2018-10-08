package com.ganiko.vitor.spajulioganiko

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tela_cadastro.*

class TelaCadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_cadastro)


        botaoCadastro.setOnClickListener{OnClickCadastrar()}
    }

    private fun OnClickCadastrar() {

        val campoCliente = findViewById<EditText>(R.id.campo_Cadastro_Cliente)
        val valorCliente = campoCliente.text.toString()

        val intent = Intent(this, TelaInicialActivity::class.java)

        val paramss = Bundle()
        paramss.putString("Cliente cadastrado:", "$valorCliente")
        intent.putExtras(paramss)


        startActivityForResult(intent, 15)
        //15 é a tela cadastro

        Toast.makeText(this, "Cliente cadastrado: $valorCliente", Toast.LENGTH_SHORT).show()

        supportActionBar?.title = "Cadastro De Clientes"

    }


}
