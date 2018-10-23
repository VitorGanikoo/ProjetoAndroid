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
            val dao = DatabaseManager.getClienteDAO()
            var clientes =  dao.findAll()


            return clientes
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
        val json = HttpHelper.post("$host/clientes", clientes.toJson())

        return parseJson<Response>(json)

    }


    fun existeCliente(cliente: Clientes): Boolean {
        val dao = DatabaseManager.getClienteDAO()
        return dao.getNyId(cliente.id) != null
    }



    fun saveOffline(clientes: Clientes) : Boolean {
        val dao = DatabaseManager.getClienteDAO()

        if (! existeCliente(clientes)) {
            dao.insert(clientes)
        }

        return true

    }





    fun delete(cliente: Clientes): Response {
        val url = "$host/clientes/${cliente.id}"
        val json = HttpHelper.delete(url)
        return parseJson<Response>(json)
    }






    

    inline fun <reified T> parseJson(json: String): T{
        val tipo = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, tipo)
    }
}