package com.ganiko.vitor.spajulioganiko


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.*
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.activity_tela_inicial_teste.*
import kotlinx.android.synthetic.main.toolbar.*

class TelaInicialActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {


    private val context: Context get() = this


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_clientes -> {
                Toast.makeText(this, "Clicou Cadastro de Clientes", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, TelaCadastroActivity::class.java)
                startActivityForResult(intent, 15)
            }
            R.id.nav_forum -> {

                Toast.makeText(this, "Clicou em Cadastro de Terpias", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, TelaCadastroTerapiaActivity::class.java)
                startActivityForResult(intent, 35)


            }

            R.id.nav_forum2 -> {

                Toast.makeText(this, "Clicou em Cadastro de Consultas", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, TelaCadastroConsultaActivity::class.java)
                startActivityForResult(intent, 65)


            }

            R.id.nav_msg -> {
                Toast.makeText(this, "Clicou em Terapias", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, TelaTerpiaActivity::class.java)
                startActivityForResult(intent, 85)


            }

            R.id.nav_msg2 -> {
                Toast.makeText(this, "Clicou em Consultas", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, TelaConsultaActivity::class.java)
                startActivityForResult(intent, 215)


            }

            R.id.nav_msg3 -> {
                Toast.makeText(this, "Clicou em Clientes", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, TelaClienteActivity::class.java)
                startActivityForResult(intent, 245)
            }


            R.id.nav_calen -> {
                Toast.makeText(this, "Clicou em Calendário", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, TelaCalendario::class.java)
                startActivityForResult(intent, 285)
            }


            R.id.nav_loc -> {
                Toast.makeText(this, "Clicou em Sobre", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, TelaSobreActivity::class.java)
                startActivityForResult(intent, 25)


            }

            R.id.nav_config -> {
                //Toast.makeText(this, "Clicou em Cadastro de Terpias", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivityForResult(intent, 0)
                //0 é a mainActivity
                intent.putExtra("Resultado", "Saiu do App")
                finish()

            }
        }

        val drawer = findViewById<DrawerLayout>(R.id.layoutMenuLateral)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial_teste)


        botaoNovoCliente.setOnClickListener { onClickNovoCliente() }
        botaoHistorico.setOnClickListener { onClickHistorico() }
        botaoCalendario.setOnClickListener { onClickCalendario() }


        //      val args = intent.extras
//        val nome = args.getString("nome")
        //     Toast.makeText(this, "Parametro enviado: $nome", Toast.LENGTH_SHORT).show()


        //   val argss = intent.extras
        // val nomes = argss.getString("nomes")
        //Toast.makeText(this, "Parametro enviado: $nomes", Toast.LENGTH_SHORT).show()


        botaoSair.setOnClickListener { onClickSair() }

        setSupportActionBar(toolbar)

        supportActionBar?.title = "Home"
        //        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        configuraMenuLateral()


    }


    private fun configuraMenuLateral() {
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        var menuLateral = findViewById<DrawerLayout>(R.id.layoutMenuLateral)

        // ícone de menu (hamburger) para mostrar o menu
        var toogle =
                ActionBarDrawerToggle(this, menuLateral, toolbar, R.string.drawer_open, R.string.drawer_close)


        menuLateral.addDrawerListener(toogle)
        toogle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.menu_lateral)
        navigationView.setNavigationItemSelectedListener(this)
    }


    private fun onClickNovoCliente() {
        val intent = Intent(this, TelaCadastroActivity::class.java)
        startActivityForResult(intent, 15)


    }

    private fun onClickCalendario() {
        val intent = Intent(this, TelaCalendario::class.java)
        startActivityForResult(intent, 115)


    }

    private fun onClickHistorico() {
        val intent = Intent(this, TelaConsultaActivity::class.java)
        startActivityForResult(intent, 315)


    }


    fun onClickSair() {
        val intent = Intent(this, MainActivity::class.java)
        startActivityForResult(intent, 0)
        //0 é a mainActivity
        intent.putExtra("Resultado", "Saiu do App")

    }






}