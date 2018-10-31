package com.ganiko.vitor.spajulioganiko

import android.content.Context
import br.com.fernandosousa.lmsapp.HttpHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fernandosousa.com.br.lmsapp.AndroidUtils
import fernandosousa.com.br.lmsapp.Response

object ConsultasService {

val host = "http://vitorganiko.pythonanywhere.com"
val TAG = "WS_LMS"

fun getConsultas(context: Context):List<Consultas> {

    if (AndroidUtils.isInternetDisponivel(context)) {
        val url = "${ConsultasService.host}/consultas"
        val json = HttpHelper.get(url)
        return ConsultasService.parseJson(json)
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





fun delete(consultas: Consultas): Response {
    val url = "${ConsultasService.host}/consultas/${consultas.id}"
    val json = HttpHelper.delete(url)
    return parseJson<Response>(json)
}








inline fun <reified T> parseJson(json: String): T{
    val tipo = object : TypeToken<T>(){}.type
    return Gson().fromJson<T>(json, tipo)
}

}