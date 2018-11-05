package com.ganiko.vitor.spajulioganiko

import android.content.Context
import android.content.Intent
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
import kotlinx.android.synthetic.main.toolbar.*

class TelaClienteActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {


    private var clientes = listOf<Clientes>()
    var RecyclerClientes: RecyclerView? = null
    private val context: Context get() = this
    private var REQUEST_CADASTRO = 1
    private var REQUEST_REMOVE= 2

    fun enviaNotificacao(cliente: Clientes){
        val intent = Intent(this, ClienteActivity::class.java)
        intent.putExtra("cliente", cliente)
        NotificationUtil.create(1, intent, "SpaJulioGaniko","Voce tem um novo cliente em ${cliente.nome}")
    }




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
        setContentView(R.layout.activity_tela_inicial)





        setSupportActionBar(toolbar)

        supportActionBar?.title = "Clientes"
        //        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        configuraMenuLateral()


        RecyclerClientes = findViewById<RecyclerView>(R.id.RecyclerClientes)
        RecyclerClientes?.layoutManager = LinearLayoutManager(context)
        RecyclerClientes?.itemAnimator = DefaultItemAnimator()
        RecyclerClientes?.setHasFixedSize(true)

    }


    override fun onResume() {
        super.onResume()
        taskClientes()
    }

    fun taskClientes() {
        Thread {

            this.clientes = ClientesService.getClientes(context)

            runOnUiThread {
                RecyclerClientes?.adapter = ClienteAdapter(clientes) { onClickCliente(it) }
                RecyclerClientes?.adapter = ClienteAdapter(this.clientes) {onClickCliente(it)}
                enviaNotificacao(this.clientes.get(0))

            }

        }
                .start()
    }

    fun onClickCliente(cliente: Clientes) {
        Toast.makeText(context, "Clicou no cliente: ${cliente.nome}", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, ClienteActivity::class.java)
        intent.putExtra("cliente", cliente)
        startActivityForResult(intent, REQUEST_REMOVE)
    }


    private fun configuraMenuLateral() {
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        var menuLateral = findViewById<DrawerLayout>(R.id.layoutMenuLateral)

        var toogle =
                ActionBarDrawerToggle(this, menuLateral, toolbar, R.string.drawer_open, R.string.drawer_close)


        menuLateral.addDrawerListener(toogle)
        toogle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.menu_lateral)
        navigationView.setNavigationItemSelectedListener(this)
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main, menu)
        // vincular evento de buscar
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                // ação enquanto está digitando
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // ação  quando terminou de buscar e enviou
                return false
            }

        })
        return true
    }




    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if (id == R.id.action_buscar) {
            Toast.makeText(this, "Clicou em buscar", Toast.LENGTH_SHORT).show()
        } else if (id == R.id.action_atualizar) {
            Toast.makeText(this, "Clicou em Atualizar", Toast.LENGTH_SHORT).show()
        } else if (id == R.id.action_configurar) {
            Toast.makeText(this, "Clicou em Configurações", Toast.LENGTH_SHORT).show()
        } else if (id == R.id.action_adicionar) {
            // iniciar activity de cadastro
            val intent = Intent(context, TelaCadastroActivity::class.java)
            startActivityForResult(intent, REQUEST_CADASTRO)
        }
        // botão up navigation
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CADASTRO || requestCode == REQUEST_REMOVE ) {
            // atualizar lista de disciplinas
            taskClientes()
        }
    }
}