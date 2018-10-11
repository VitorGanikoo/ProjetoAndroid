package com.ganiko.vitor.spajulioganiko

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast

class TelaTerpiaActivity : AppCompatActivity() {


    private var terapias = listOf<Terapias>()
    var recyclerView: RecyclerView? = null
    private val context: Context get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_terpia)


        supportActionBar?.title = "Terapias"

        recyclerView = findViewById(R.id.RecyclerClientes)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.itemAnimator = DefaultItemAnimator()
        recyclerView?.setHasFixedSize(true)
    }


    override fun onResume() {
        super.onResume()
        taskTerapias()
    }

    fun taskTerapias(){
        terapias = TerapiasService.getTerapias(context)
        recyclerView?.adapter = TerapiaAdapter(terapias){onClickTerapia(it)}
    }

    fun onClickTerapia(terapias: Terapias){
        Toast.makeText(context, "Clicou na terapia: ${terapias.nome}", Toast.LENGTH_SHORT).show()
    }

}
