package com.example.a071_roomdatabase.viewmodel.provider

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.a071_roomdatabase.repositori.RepositoriSiswa
import com.example.a071_roomdatabase.room.Siswa

class EntryViewModel (private val repositoriSiswa: RepositoriSiswa): ViewModel(){
    //    berisikan status siswa saat ini
    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set
    //    fungsi untuk memvalidasi input
    private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa): Boolean{
        return with(uiState){
            nama.isNotBlank() && alamat.isNotBlank() && telepon.isNotBlank()
        }
    }

    fun updateUiState(detailSiswa: DetailSiswa){
        uiStateSiswa =
            UIStateSiswa(detailSiswa = detailSiswa, isEntryValid = validasiInput(detailSiswa))
    }
    //    fungsi utnuk menyimpan data yang di entry
    suspend fun saveSiswa(){
        if (validasiInput()){
            repositoriSiswa.insertSiswa(uiStateSiswa.detailSiswa.toSiswa())
        }
    }
}

data class UIStateSiswa(
    val detailSiswa: DetailSiswa = DetailSiswa(),
    val isEntryValid: Boolean = false,
)

data class DetailSiswa(
    val id: Int = 0,
    val nama: String = "",
    val alamat: String = "",
    val telepon: String = "",
    val telpon: Any
)

fun DetailSiswa.toSiswa(): Siswa = Siswa(
    id = id,
    name = nama,
    alamat = alamat,
    telpon = telepon
)

fun Siswa.toUiStateSiswa(isEntryValid: Boolean = false): UIStateSiswa = UIStateSiswa(
    detailSiswa = this.toDetailSiswa(),
    isEntryValid = isEntryValid
)

fun Siswa.toDetailSiswa(): DetailSiswa = DetailSiswa(
    id = id,
    nama =name,
    alamat = alamat,
    telepon = telpon,
)


