package com.ganiko.vitor.spajulioganiko

import android.content.Context
import android.util.Log
import br.com.fernandosousa.lmsapp.HttpHelper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import fernandosousa.com.br.lmsapp.AndroidUtils
import fernandosousa.com.br.lmsapp.LMSApplication
import fernandosousa.com.br.lmsapp.Response
import java.net.URL

object ClientesService {

    val host = "http://vitorganikoo.pythonanywhere.com"
    val TAG = "WS_LMS"

    fun getClientes(context: Context):List<Clientes>{
        var clientes = ArrayList<Clientes>()
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/clientes"
            val json = HttpHelper.get(url)
            clientes = parseJson(json)
            // salvar offline
            for (d in clientes) {
                saveOffline(d)
            }
            return clientes
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

    fun getCliente (context: Context, id: Long): Clientes? {

        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/clientes/${id}"
            val json = HttpHelper.get(url)
            val cliente = parseJson<Clientes>(json)

            return cliente
        } else {
            val dao = DatabaseManager.getClienteDAO()
            val cliente = dao.getNyId(id)
            return cliente
        }

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
        if (AndroidUtils.isInternetDisponivel(LMSApplication.getInstance().applicationContext)) {
            val url = "$host/clientes/${cliente.id}"
            val json = HttpHelper.delete(url)

            return parseJson(json)
        } else {
            val dao = DatabaseManager.getClienteDAO()
            dao.delete(cliente)
            return Response(status = "OK", msg = "Dados salvos localmente")
        }
    }






    

    inline fun <reified T> parseJson(json: String): T{
        val tipo = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, tipo)
    }
}