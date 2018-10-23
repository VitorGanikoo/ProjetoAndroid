package com.ganiko.vitor.spajulioganiko

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ClienteDAO {


    @Query("SELECT * FROM cliente where id = :id")
    fun getNyId(id: Long): Clientes?


    @Query("SELECT * FROM cliente")
    fun findAll(): List<Clientes>


    @Insert
    fun insert(clientes: Clientes)


    @Delete
    fun delete(clientes: Clientes)

}