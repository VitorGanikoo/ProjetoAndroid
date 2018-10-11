package com.ganiko.vitor.spajulioganiko

import android.content.Context

object ClientesService {

    fun getClientes(context: Context):List<Clientes>{
        val clientess = mutableListOf<Clientes>()

        for(i in 1..10){
            var d = Clientes()
            d.nome = "Nome $i"
            d.email = "Email $i"
            d.telefone = "Telefone $i"
            d.dataNascimento = "Data de nascimento $i"
            d.foto = "http://spajulioganiko.com.br/wp-content/uploads/2014/08/julio.png"
            clientess.add(d)
        }
        return clientess
    }
}