package com.example.guestdatabase.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.guestdatabase.db.model.Guest


@Database(entities = [Guest::class], version = 1)
abstract class GuestDatabase : RoomDatabase() {
    abstract val guestDao : GuestDao
}

//Diese Variable enthält die eine konkrete Instanz unserer Datenbank
private lateinit var INSTANCE: GuestDatabase

fun getDatabase(context: Context): GuestDatabase {

    synchronized(GuestDatabase::class.java) {

        //Überprüfe ob die Datenbank bereits existiert
        if (!::INSTANCE.isInitialized) {

            //Wenn die Datenbank noch nicht existiert dann erstelle sie
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                GuestDatabase::class.java,
                "guest_database"
            ).build()
        }

        //In jedem Fall liefer die Instanz der Datenbank zurück
        return INSTANCE
    }
}