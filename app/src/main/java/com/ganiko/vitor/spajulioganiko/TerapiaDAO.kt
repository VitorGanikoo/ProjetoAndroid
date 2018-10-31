package com.ganiko.vitor.spajulioganiko

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface TerapiaDAO {

    @Query("SELECT * FROM terapia where id = :id")
    fun getNyId(id: Long): Terapias?


    @Query("SELECT * FROM terapia")
    fun findAll(): List<Terapias>


    @Insert
    fun insert(terapias: Terapias)


    @Delete
    fun delete(terapias: Terapias)
}