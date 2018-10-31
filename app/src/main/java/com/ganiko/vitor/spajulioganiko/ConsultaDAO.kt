package com.ganiko.vitor.spajulioganiko

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ConsultaDAO {
    @Query("SELECT * FROM consulta where id = :id")
    fun getNyId(id: Long): Consultas?


    @Query("SELECT * FROM consulta")
    fun findAll(): List<Consultas>


    @Insert
    fun insert(consultas: Consultas)


    @Delete
    fun delete(consultas: Consultas)



}