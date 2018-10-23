package com.ganiko.vitor.spajulioganiko

import android.arch.persistence.room.Room
import fernandosousa.com.br.lmsapp.LMSApplication

object DatabaseManager {

    private val dbInstance: APPDatabase

    init {
        val appContext = LMSApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(appContext, APPDatabase::class.java, "lms.sqlite").build()
    }


    fun getClienteDAO(): ClienteDAO{
        return dbInstance.ClienteDAO()
    }
}