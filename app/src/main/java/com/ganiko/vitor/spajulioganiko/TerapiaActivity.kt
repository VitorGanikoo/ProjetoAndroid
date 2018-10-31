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

class TerapiaActivity : DebugActivity() {

    private val context: Context get() = this
    var terapia: Terapias? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terapia)

        // recuperar onjeto de Disciplina da Intent
        terapia = intent.getSerializableExtra("terapia") as Terapias

        // configurar título com nome da Disciplina e botão de voltar da Toobar
        // colocar toolbar
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // alterar título da ActionBar
        supportActionBar?.title = terapia?.nome

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // atualizar dados do carro
        var texto = findViewById<TextView>(R.id.nomeTerapia)
        texto.text = terapia?.nome
        var descricao = findViewById<TextView>(R.id.descricaoTerapia)
        descricao.text = terapia?.descricao
        var valor = findViewById<TextView>(R.id.valorTerapia)
        valor.text = terapia?.valor



        var imagem = findViewById<ImageView>(R.id.imagemTerapia)
        Picasso.with(this).load(terapia?.foto).fit().into(imagem,
                object: com.squareup.picasso.Callback{
                    override fun onSuccess() {}

                    override fun onError() { }
                })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main_terapia, menu)
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
                    .setMessage("Deseja excluir a terapia")
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
        if (this.terapia != null && this.terapia is Terapias) {

            Thread {
                TerapiasService.delete(this.terapia as Terapias)
                runOnUiThread {

                    finish()
                }
            }.start()
        }
    }

}