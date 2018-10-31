package com.ganiko.vitor.spajulioganiko

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable


@Entity(tableName = "terapia")
class Terapias : Serializable{


    @PrimaryKey
    var id:Long = 0
    var nome = ""
    var descricao = ""
    var valor = ""
    var foto = ""

    override fun toString(): String {
        return "Terapias(nome='$nome')"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }

}