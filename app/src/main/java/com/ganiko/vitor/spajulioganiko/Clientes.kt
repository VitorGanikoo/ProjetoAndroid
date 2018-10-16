package com.ganiko.vitor.spajulioganiko

import com.google.gson.GsonBuilder

class Clientes {

    var id:Long = 0
    var nome = ""
    var email = ""
    var telefone = ""
    var dataNascimento = ""
    var foto = ""

    override fun toString(): String {
        return "Clientes(nome='$nome')"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }


}