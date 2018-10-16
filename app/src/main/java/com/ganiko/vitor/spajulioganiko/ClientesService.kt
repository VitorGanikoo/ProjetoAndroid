package com.ganiko.vitor.spajulioganiko

import android.content.Context
import android.util.Log
import br.com.fernandosousa.lmsapp.HttpHelper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import fernandosousa.com.br.lmsapp.Response
import java.net.URL

object ClientesService {

    val host = "http://fesousa.pythonanywhere.com"
    val TAG = "WS_LMS"

    fun getClientes(context: Context):List<Clientes>{

        val url = "$host/disciplinas"
        val json = HttpHelper.get(url)

        Log.d(TAG, json)

        /* val clientess = mutableListOf<Clientes>()

         for(i in 1..10){
             var d = Clientes()
             d.nome = "Nome $i"
             d.email = "Email $i"
             d.telefone = "Telefone $i"
             d.dataNascimento = "Data de nascimento $i"
             d.foto = "http://spajulioganiko.com.br/wp-content/uploads/2014/08/julio.png"
             clientess.add(d)
         }*/
        return parseJson <List<Clientes>>(json)
    }

    fun save (clientes: Clientes): Response {
        val url = "$host/disciplinas"
        val json = GsonBuilder().create().toJson(clientes)
        val retorno = HttpHelper.post(url, json)

        return parseJson<Response>(retorno)

    }

    inline fun <reified T> parseJson(json: String): T{
        val tipo = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, tipo)
    }
}