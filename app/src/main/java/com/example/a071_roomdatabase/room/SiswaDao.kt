package com.example.a071_roomdatabase.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SiswaDao {
    @Query("SELECT* from tblSiswa ORDER BY name ASC")
    fun getAllSiswa(): Flow<List<Siswa>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun Insert(siswa:Siswa)

    @Query("SELECT* from tblSiswa Where id = :id")
    fun getSiswa(id: Int): Flow<Siswa>

    @Delete
    suspend fun delete(siswa: Siswa)

}