package com.example.guestdatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guestdatabase.db.GuestRepository
import com.example.guestdatabase.db.getDatabase
import com.example.guestdatabase.db.model.Guest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GuestViewModel(application: Application) : AndroidViewModel(application) {

    val repository = GuestRepository(getDatabase(application))

    val allGuests = repository.allGuests

    fun insertGuest(guest: Guest) {

        if (guest.name.isNotBlank() && guest.food.isNotEmpty()) {

            viewModelScope.launch(Dispatchers.IO) {
                repository.insertGuest(guest)
            }
        }
    }

}