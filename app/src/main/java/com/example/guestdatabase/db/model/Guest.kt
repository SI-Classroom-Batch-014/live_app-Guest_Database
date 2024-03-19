package com.example.guestdatabase.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Guest(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val name: String,
    val food: String,
)