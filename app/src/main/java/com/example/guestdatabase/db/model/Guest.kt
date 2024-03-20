package com.example.guestdatabase.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Guest(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val name: String,
    val food: String,

    //true -> Hat fest zugesagt
    //false -> ist noch unklar
    //Wenn abgesagt -> aus der Datenbank löschen
    val isComing: Boolean,

    val comment: String,
)