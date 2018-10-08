package com.ganiko.vitor.spajulioganiko

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class TelaTerpiaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_terpia)


        supportActionBar?.title = "Terapias"
    }
}
