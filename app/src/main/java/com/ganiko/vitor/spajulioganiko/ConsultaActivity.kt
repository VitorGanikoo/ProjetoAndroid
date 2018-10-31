package com.ganiko.vitor.spajulioganiko

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_consulta.view.*

class ConsultaActivity : DebugActivity() {

    private val context: Context get() = this
    var consulta: Consultas? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta)

        // recuperar onjeto de Disciplina da Intent
        consulta = intent.getSerializableExtra("consulta") as Consultas

        // configurar título com nome da Disciplina e botão de voltar da Toobar
        // colocar toolbar
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // alterar título da ActionBar
        supportActionBar?.title = consulta?.nomeCliente

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // atualizar dados do carro
        var texto = findViewById<TextView>(R.id.nomeClienteConsulta)
        texto.text = consulta?.nomeCliente
        var texto2 = findViewById<TextView>(R.id.nomeTerapeutaConsulta)
        texto2.text = consulta?.nomeTerapeuta
        var texto3 = findViewById<TextView>(R.id.nomeTerapiaConsulta)
        texto3.text = consulta?.nomeTerapia
        var descricao = findViewById<TextView>(R.id.descricaoConsulta)
        descricao.text = consulta?.descricao
        var data = findViewById<TextView>(R.id.dataConsulta)
        data.text = consulta?.data



        var imagem = findViewById<ImageView>(R.id.imagemConsulta)
        Picasso.with(this).load(consulta?.foto).fit().into(imagem,
                object: com.squareup.picasso.Callback{
                    override fun onSuccess() {}

                    override fun onError() { }
                })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main_consulta, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // id do item clicado
        val id = item?.itemId
        // verificar qual item foi clicado
        // remover a disciplina no WS
        if  (id == R.id.action_deletar) {
            // alerta para confirmar a remeção
            // só remove se houver confirmação positiva
            AlertDialog.Builder(this)
                    .setTitle(R.string.app_name)
                    .setMessage("Deseja excluir a consulta")
                    .setPositiveButton("Sim") {
                        dialog, which ->
                        dialog.dismiss()
                        taskExcluir()
                    }.setNegativeButton("Não") {
                        dialog, which -> dialog.dismiss()
                    }.create().show()
        }
        // botão up navigation
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun taskExcluir() {
        if (this.consulta != null && this.consulta is Consultas) {

            Thread {
                ConsultasService.delete(this.consulta as Consultas)
                runOnUiThread {

                    finish()
                }
            }.start()
        }
    }

}
