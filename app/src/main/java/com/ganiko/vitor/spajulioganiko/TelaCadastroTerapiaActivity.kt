package com.ganiko.vitor.spajulioganiko

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tela_cadastro.*
import kotlinx.android.synthetic.main.activity_tela_cadastro_terapia.*


class TelaCadastroTerapiaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_cadastro_terapia)

        supportActionBar?.title = "Cadastro De Terapias"

        botaoCadastroTerapia.setOnClickListener {
            val terapia = Terapias()
            terapia.nome = campoCadastroTerapiaNome.text.toString()
            terapia.descricao = campoCadastroTerapiaDescricao.text.toString()
            terapia.valor = campoCadastroTerapiaValor.text.toString()
            terapia.foto = campoCadastroTerapiaFoto.text.toString()

            taskAtualizarTerapias(terapia)
        }
    }

    private fun OnClickCadastrarTerapia() {

        val campoTerpia = findViewById<EditText>(R.id.campoCadastroTerapiaNome)
        val valorTerapia = campoTerpia.text.toString()

        val intent = Intent(this, TelaInicialActivity::class.java)

        val paramsss = Bundle()
        paramsss.putString("Cliente cadastrado:", "$valorTerapia")
        intent.putExtras(paramsss)


        startActivityForResult(intent, 15)
        //15 é a tela cadastro

        Toast.makeText(this, "Terapia cadastrada: $valorTerapia", Toast.LENGTH_SHORT).show()



    }



    private fun taskAtualizarTerapias(terapias: Terapias) {
        // Thread para salvar a discilpina
        Thread {
            TerapiasService.save(terapias)
            runOnUiThread {
                // após cadastrar, voltar para activity anterior
                finish()
            }
        }.start()
    }

}
