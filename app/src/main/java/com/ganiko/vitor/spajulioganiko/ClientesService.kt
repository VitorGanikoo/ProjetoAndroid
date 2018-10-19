package com.ganiko.vitor.spajulioganiko

import android.content.Context
import android.util.Log
import br.com.fernandosousa.lmsapp.HttpHelper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import fernandosousa.com.br.lmsapp.AndroidUtils
import fernandosousa.com.br.lmsapp.Response
import java.net.URL

object ClientesService {

    val host = "http://vitorganikoo.pythonanywhere.com"
    val TAG = "WS_LMS"

    fun getClientes(context: Context):List<Clientes>{

        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/clientes"
            val json = HttpHelper.get(url)
            return parseJson(json)
        } else {
            return ArrayList<Clientes>()
        }


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

    }

    fun save (clientes: Clientes): Response {
        val url = "$host/clientes"
        val json = GsonBuilder().create().toJson(clientes)
        val retorno = HttpHelper.post(url, json)

        return parseJson<Response>(retorno)

    }


    fun delete(cliente: Clientes): Response {
        Log.d(TAG, cliente.id.toString())
        val url = "$host/clientes/${cliente.id}"
        val json = HttpHelper.delete(url)
        Log.d(TAG, json)
        return parseJson(json)
    }



    inline fun <reified T> parseJson(json: String): T{
        val tipo = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, tipo)
    }
}