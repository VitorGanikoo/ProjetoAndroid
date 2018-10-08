package com.ganiko.vitor.spajulioganiko

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.login.*

class MainActivity : DebugActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        botaoLogin.setOnClickListener {onClickLogin() }
    }

    fun onClickLogin(){
        val campoUsuario = findViewById<EditText>(R.id.campo_Usuario)
        val campoSenha = findViewById<EditText>(R.id.campo_Senha)
        val valorUsuario = campoUsuario.text.toString()
        val valorSenha = campoSenha.text.toString()

        val intent = Intent(this, TelaInicialActivity::class.java)

        val params = Bundle()
        params.putString("nome", "$valorUsuario")
        intent.putExtras(params)

        if ((campoUsuario.getText().toString().equals("aluno")) && (campoSenha.getText().toString().equals("impacta"))){
            Toast.makeText(getApplication(),
                    " Login efetuado ",
                    Toast.LENGTH_LONG).show();
            startActivityForResult(intent, 5)
            //5 é a tela inicial
        }else{
            Toast.makeText(getApplication(),
                    " Erro ",
                    Toast.LENGTH_LONG).show();
        }




    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Toast.makeText(this, data?.getStringExtra("Resultado"), Toast.LENGTH_SHORT).show()
    }

}
