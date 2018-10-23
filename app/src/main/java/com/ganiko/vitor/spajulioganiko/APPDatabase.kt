package com.ganiko.vitor.spajulioganiko

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(Clientes::class), version = 1)
abstract class APPDatabase: RoomDatabase() {
    abstract fun ClienteDAO(): ClienteDAO

}