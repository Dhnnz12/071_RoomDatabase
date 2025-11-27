package com.example.a071_roomdatabase.repositori

import com.example.a071_roomdatabase.room.Siswa
import com.example.a071_roomdatabase.room.SiswaDao
import kotlinx.coroutines.flow.Flow


interface RepositoriSiswa {
    fun getAllSiswaStream(): Flow<List<Siswa>>
    suspend fun insertSiswa(siswa: Siswa)
}


class OfflineRepositoriSiswa(
    private val siswaDao: SiswaDao

): RepositoriSiswa{
override fun getAllSiswaStream(): Flow<List<Siswa>> = siswaDao
    .getAllSiswa()
override suspend fun insertSiswa(siswa: Siswa) = siswaDao
    .Insert(siswa)
}
