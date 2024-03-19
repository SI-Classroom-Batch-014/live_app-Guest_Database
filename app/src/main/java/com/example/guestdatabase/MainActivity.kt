package com.example.guestdatabase

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.guestdatabase.databinding.ActivityMainBinding
import com.example.guestdatabase.databinding.AddGuestDialogBinding
import com.example.guestdatabase.db.model.Guest

class MainActivity : AppCompatActivity() {

    private val viewModel: GuestViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        Log.d("ActivityMainBinding", "test")
        setContentView(binding.root)


        viewModel.allGuests.observe(this) {
            Log.d("GuestDataTest", "$it")

            binding.guestRV.adapter = GuestAdapter(it)
        }

        binding.addBTN.setOnClickListener {

            val dialogBinding = AddGuestDialogBinding.inflate(layoutInflater)

            AlertDialog.Builder(this)
                .setView(dialogBinding.root)
                .setNegativeButton("Abbrechen") { _, _ ->

                }
                .setPositiveButton("Hinzufügen") { _, _ ->
                    val guest =
                        Guest(
                            name = dialogBinding.guestDialogNameET.text.toString(),
                            food = dialogBinding.guestDialogFoodET.text.toString()
                        )
                    viewModel.insertGuest(guest)
                }
                .show()

        }

    }

}