package com.ganiko.vitor.spajulioganiko

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tela_cadastro_consulta.*
import kotlinx.android.synthetic.main.activity_tela_cadastro_terapia.*

class TelaCadastroConsultaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_cadastro_consulta)

        supportActionBar?.title = "Cadastro De Consultas"

        botaoCadastroConsulta.setOnClickListener {
            val consulta = Consultas()
            consulta.nomeCliente = campoCadastroConsultaNomeCliente.text.toString()
            consulta.nomeTerapeuta = campoCadastroConsultaNomeTerapeuta.text.toString()
            consulta.nomeTerapia = campoCadastroConsultaNomeTerapia.text.toString()
            consulta.descricao = campoCadastroConsultaDescricao.text.toString()
            consulta.data = campoCadastroConsultaData.text.toString()
            consulta.foto = campoCadastroConsultaFoto.text.toString()

            taskAtualizarConsultas(consulta)
        }
    }

    private fun OnClickCadastrarConsulta() {

        val campoConsulta = findViewById<EditText>(R.id.campoCadastroConsultaNomeCliente)
        val valorConsulta = campoConsulta.text.toString()

        val intent = Intent(this, TelaInicialActivity::class.java)

        val paramsss = Bundle()
        paramsss.putString("Consulta cadastrada:", "$valorConsulta")
        intent.putExtras(paramsss)


        startActivityForResult(intent, 15)
        //15 é a tela cadastro

        Toast.makeText(this, "Terapia cadastrada: $valorConsulta", Toast.LENGTH_SHORT).show()



    }



    private fun taskAtualizarConsultas(consultas: Consultas) {
        // Thread para salvar a discilpina
        Thread {
            ConsultasService.save(consultas)
            runOnUiThread {
                // após cadastrar, voltar para activity anterior
                finish()
            }
        }.start()
    }

}