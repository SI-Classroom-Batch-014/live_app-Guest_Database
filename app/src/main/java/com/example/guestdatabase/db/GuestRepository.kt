package com.example.guestdatabase.db

import androidx.lifecycle.LiveData
import com.example.guestdatabase.db.model.Guest

class GuestRepository(val database: GuestDatabase) {

    val allGuests: LiveData<List<Guest>> = database.guestDao.getAllLiveData()



    fun upsertGuest(guest: Guest){
        database.guestDao.upsertGuest(guest)
    }

    fun deleteGuest(guest: Guest){
        database.guestDao.deleteGuest(guest)
    }

    suspend fun searchGuests(searchTerm: String): List<Guest> {
        return database.guestDao.searchGuest(searchTerm)
    }

}