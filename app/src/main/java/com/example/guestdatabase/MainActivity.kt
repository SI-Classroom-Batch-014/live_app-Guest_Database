package com.example.guestdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.guestdatabase.databinding.ActivityMainBinding
import com.example.guestdatabase.databinding.AddGuestDialogBinding
import com.example.guestdatabase.db.model.Guest
import com.example.guestdatabase.ui.GuestAdapter

class MainActivity : AppCompatActivity() {

    private val viewModel: GuestViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        Log.d("ActivityMainBinding", "test")
        setContentView(binding.root)




    }

}