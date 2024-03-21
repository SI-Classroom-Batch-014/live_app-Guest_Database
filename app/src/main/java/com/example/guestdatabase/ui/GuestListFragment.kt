package com.example.guestdatabase.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import com.example.guestdatabase.GuestViewModel
import com.example.guestdatabase.databinding.AddGuestDialogBinding
import com.example.guestdatabase.databinding.FragmentGuestListBinding
import com.example.guestdatabase.db.model.Guest


class GuestListFragment : Fragment() {

    private lateinit var binding: FragmentGuestListBinding
    private val viewModel: GuestViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGuestListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Load all guests to initalize the LiveData in the ViewModel
        viewModel.searchGuests("")

        viewModel.filteredGuest.observe(viewLifecycleOwner) {
            Log.d("GuestDataTest", "$it")

            binding.guestRV.adapter = GuestAdapter(it, viewModel)
        }

        binding.searchET.addTextChangedListener {
            viewModel.searchGuests(it.toString())
        }


        binding.addBTN.setOnClickListener {

            showDialog()

        }

    }


    fun showDialog() {
        val dialogBinding = AddGuestDialogBinding.inflate(layoutInflater)

        AlertDialog.Builder(requireContext())
            .setView(dialogBinding.root)
            .setNegativeButton("Abbrechen") { _, _ ->

            }
            .setPositiveButton("Hinzufügen") { _, _ ->

                //Datenbank Aktion
                val guest =
                    Guest(
                        name = dialogBinding.guestDialogNameET.text.toString(),
                        food = dialogBinding.guestDialogFoodET.text.toString(),
                        comment = "",
                        isComing = false,
                    )
                viewModel.upsertGuest(guest)



            }
            .show()
    }


}