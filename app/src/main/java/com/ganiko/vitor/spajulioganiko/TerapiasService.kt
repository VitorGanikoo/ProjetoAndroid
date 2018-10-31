package com.ganiko.vitor.spajulioganiko

import android.content.Context
import br.com.fernandosousa.lmsapp.HttpHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fernandosousa.com.br.lmsapp.AndroidUtils
import fernandosousa.com.br.lmsapp.Response

object TerapiasService {

    val host = "http://vitorganikooo.pythonanywhere.com"
    val TAG = "WS_LMS"

    fun getTerapias(context: Context):List<Terapias> {

        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "${TerapiasService.host}/terapias"
            val json = HttpHelper.get(url)
            return TerapiasService.parseJson(json)
        } else {
            val dao = DatabaseManager.getTerapiaDAO()
            var terapias = dao.findAll()


            return terapias
        }
    }




    fun save (terapias: Terapias): Response {
        val json = HttpHelper.post("${TerapiasService.host}/terapias", terapias.toJson())

        return parseJson<Response>(json)

    }


    fun existeTerapia(terapias: Terapias): Boolean {
        val dao = DatabaseManager.getTerapiaDAO()
        return dao.getNyId(terapias.id) != null
    }



    fun saveOffline(terapias: Terapias) : Boolean {
        val dao = DatabaseManager.getTerapiaDAO()

        if (! existeTerapia(terapias)) {
            dao.insert(terapias)
        }

        return true

    }





    fun delete(terapias: Terapias): Response {
        val url = "${TerapiasService.host}/terapias/${terapias.id}"
        val json = HttpHelper.delete(url)
        return parseJson<Response>(json)
    }








    inline fun <reified T> parseJson(json: String): T{
        val tipo = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, tipo)
    }

}