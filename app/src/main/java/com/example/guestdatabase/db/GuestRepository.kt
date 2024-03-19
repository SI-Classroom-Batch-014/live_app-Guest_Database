package com.example.guestdatabase.db

import androidx.lifecycle.LiveData
import com.example.guestdatabase.db.model.Guest

class GuestRepository(val database: GuestDatabase) {

    val allGuests: LiveData<List<Guest>> = database.guestDao.getAllLiveData()

    suspend fun insertGuest(guest: Guest){
        database.guestDao.insertGuest(guest)
    }

}