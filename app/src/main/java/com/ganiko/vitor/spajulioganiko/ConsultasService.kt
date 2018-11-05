package com.ganiko.vitor.spajulioganiko

import android.content.Context
import br.com.fernandosousa.lmsapp.HttpHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fernandosousa.com.br.lmsapp.AndroidUtils
import fernandosousa.com.br.lmsapp.LMSApplication
import fernandosousa.com.br.lmsapp.Response

object ConsultasService {

    val host = "http://vitorganiko.pythonanywhere.com"
    val TAG = "WS_LMS"

    fun getConsultas(context: Context):List<Consultas> {
        var consultas = ArrayList<Consultas>()
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "${ConsultasService.host}/consultas"
            val json = HttpHelper.get(url)
            consultas = ConsultasService.parseJson(json)
            // salvar offline
            for (d in consultas) {
                ConsultasService.saveOffline(d)
            }
            return consultas
        } else {
            val dao = DatabaseManager.getConsultaDAO()
            var consultas = dao.findAll()


            return consultas
        }
    }




    fun save (consultas: Consultas): Response {
        val json = HttpHelper.post("${ConsultasService.host}/consultas", consultas.toJson())

        return parseJson<Response>(json)

    }


    fun existeConsulta(consultas: Consultas): Boolean {
        val dao = DatabaseManager.getConsultaDAO()
        return dao.getNyId(consultas.id) != null
    }



    fun saveOffline(consultas: Consultas) : Boolean {
        val dao = DatabaseManager.getConsultaDAO()

        if (! existeConsulta(consultas)) {
            dao.insert(consultas)
        }

        return true

    }





    fun delete(consulta: Consultas): Response {
        if (AndroidUtils.isInternetDisponivel(LMSApplication.getInstance().applicationContext)) {
            val url = "${ConsultasService.host}/consultas/${consulta.id}"
            val json = HttpHelper.delete(url)

            return ConsultasService.parseJson(json)
        } else {
            val dao = DatabaseManager.getConsultaDAO()
            dao.delete(consulta)
            return Response(status = "OK", msg = "Dados salvos localmente")
        }
    }







    inline fun <reified T> parseJson(json: String): T{
        val tipo = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, tipo)
    }

    }