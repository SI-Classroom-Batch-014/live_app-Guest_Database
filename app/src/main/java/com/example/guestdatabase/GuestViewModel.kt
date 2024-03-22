package com.example.guestdatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.guestdatabase.db.GuestRepository
import com.example.guestdatabase.db.getDatabase
import com.example.guestdatabase.db.model.Guest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GuestViewModel(application: Application) : AndroidViewModel(application) {

    val repository = GuestRepository(getDatabase(application))

    private val _selectedGuest = MutableLiveData<Guest>()
    val selectedGuest: LiveData<Guest>
        get() = _selectedGuest

    fun selectGuest(guest: Guest) {
        _selectedGuest.value = guest
    }

    fun upsertGuest(guest: Guest) {

        if (guest.name.isNotBlank() && guest.food.isNotBlank()) {

            viewModelScope.launch(Dispatchers.IO) {
                repository.upsertGuest(guest)
            }

        }
    }

    fun deleteGuest(guest: Guest) {

        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteGuest(guest)
        }

    }

    fun searchGuests(searchTerm: String): LiveData<List<Guest>> {
        return repository.searchGuests(searchTerm)
    }

}