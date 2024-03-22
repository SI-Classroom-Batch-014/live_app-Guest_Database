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

    fun searchGuests(searchTerm: String): LiveData<List<Guest>> {
        return database.guestDao.searchGuest(searchTerm)
    }


//    fun searchGuests(searchTerm: String) = database.guestDao.searchGuest(searchTerm)

}