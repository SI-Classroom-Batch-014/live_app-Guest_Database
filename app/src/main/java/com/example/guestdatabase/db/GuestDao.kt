package com.example.guestdatabase.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.guestdatabase.db.model.Guest

@Dao
interface GuestDao {

    //Mit der OnConflictStrategy Replace können wir diese Funktion als "Upsert" nutzen
    //Upsert: Mischung aus Insert und Update
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertGuest(guest: Guest)

    @Query("SELECT * FROM Guest")
    suspend fun getAll() : List<Guest>

    @Query("SELECT * FROM Guest")
    fun getAllLiveData() : LiveData<List<Guest>>

    @Delete
    fun deleteGuest(guest: Guest)

    @Query("DELETE FROM Guest")
    fun deleteAll()



}