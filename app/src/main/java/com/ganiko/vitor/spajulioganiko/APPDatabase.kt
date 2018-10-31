package com.ganiko.vitor.spajulioganiko

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(Clientes::class, Terapias::class), version = 2)
abstract class APPDatabase: RoomDatabase() {
    abstract fun ClienteDAO(): ClienteDAO
    abstract fun TerapiaDAO(): TerapiaDAO

}