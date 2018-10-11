package com.ganiko.vitor.spajulioganiko

import android.content.Context

object TerapiasService {

    fun getTerapias(context: Context):List<Terapias>{
        val terapiass = mutableListOf<Terapias>()

        for(i in 1..10){
            var d = Terapias()
            d.nome = "Nome $i"
            d.descricao = "Descrição $i"
            d.valor = "Valor $i"
            d.foto = "https://www.diarioonline.com.br/app/painel/modulo-noticia/img/imagensdb/original/destaque-518511-ronaldinho-gaucho.jpg"
            terapiass.add(d)
        }
        return terapiass
    }

}