package com.ganiko.vitor.spajulioganiko

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable


@Entity(tableName = "consulta")
class Consultas : Serializable {


    @PrimaryKey
    var id:Long = 0
    var nomeCliente = ""
    var nomeTerapeuta = ""
    var nomeTerapia = ""
    var descricao = ""
    var data = ""
    var foto = ""

    override fun toString(): String {
        return "Consultas(nome='$nomeCliente')"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }

}