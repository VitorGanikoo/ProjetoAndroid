package com.ganiko.vitor.spajulioganiko

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(Clientes::class, Terapias::class, Consultas::class), version = 3)
abstract class APPDatabase: RoomDatabase() {
    abstract fun ClienteDAO(): ClienteDAO
    abstract fun TerapiaDAO(): TerapiaDAO
    abstract fun ConsultaDAO(): ConsultaDAO

}