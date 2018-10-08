package com.ganiko.vitor.spajulioganiko

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*

    class TelaInicialActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {


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

                R.id.nav_msg -> {
                    Toast.makeText(this, "Clicou em Terapias", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, TelaTerpiaActivity::class.java)
                    startActivityForResult(intent, 45)


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

            layoutMenuLateral.closeDrawer(GravityCompat.START)
            return true
        }

        fun configuraMenuLateral(){
            val toolbar = toolbar
            val layoutMenuLateral = layoutMenuLateral

            var toggle = ActionBarDrawerToggle(
                    this,
                    layoutMenuLateral,
                    toolbar,
                    R.string.drawer_open,
                    R.string.drawer_close

            )

            layoutMenuLateral.addDrawerListener(toggle)
            toggle.syncState()

            val navigationView = menu_tela_inicial
            navigationView.setNavigationItemSelectedListener(this)
        }


        private val context: Context get() = this

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_tela_inicial)


            botaoNovoCliente.setOnClickListener{onClickNovoCliente()}


            val args = intent.extras
            val nome = args.getString("nome")
            Toast.makeText(this, "Parametro enviado: $nome", Toast.LENGTH_SHORT).show()


            val argss = intent.extras
            val nomes = argss.getString("nomes")
            Toast.makeText(this, "Parametro enviado: $nomes", Toast.LENGTH_SHORT).show()


            botaoSair.setOnClickListener {onClickSair() }

            setSupportActionBar(toolbar)

            supportActionBar?.title = "Clientes"
    //        supportActionBar?.setDisplayHomeAsUpEnabled(true)

            configuraMenuLateral()
        }

        private fun onClickNovoCliente() {
            val intent = Intent(this, TelaCadastroActivity::class.java)
            startActivityForResult(intent, 15)


        }

        fun onClickSair(){
            val intent = Intent(this, MainActivity::class.java)
            startActivityForResult(intent, 0)
            //0 é a mainActivity
            intent.putExtra("Resultado", "Saiu do App")

        }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menu_main, menu)
            return super.onCreateOptionsMenu(menu)

        }

        override fun onOptionsItemSelected(item: MenuItem?): Boolean {
            val id = item?.itemId
            if (id == R.id.action_buscar){
                Toast.makeText(this, "Clicou em buscar", Toast.LENGTH_SHORT).show()
            }else if (id == R.id.action_atualizar ){
                Toast.makeText(this, "Clicou em Atualizar", Toast.LENGTH_SHORT).show()
            }else if (id == R.id.action_configurar){
                Toast.makeText(this, "Clicou em Configurações", Toast.LENGTH_SHORT). show()
            }else if (id == android.R.id.home){
                finish()
            }

            return super.onOptionsItemSelected(item)
        }
    }
