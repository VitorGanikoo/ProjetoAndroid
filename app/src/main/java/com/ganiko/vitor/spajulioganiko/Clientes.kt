package com.ganiko.vitor.spajulioganiko

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "cliente")
class Clientes : Serializable {

    @PrimaryKey
    var id:Long = 0
    var nome = ""
    var email = ""
    var telefone = ""
    //var dataNascimento = ""
    var foto = ""

    override fun toString(): String {
        return "Clientes(nome='$nome')"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }


}