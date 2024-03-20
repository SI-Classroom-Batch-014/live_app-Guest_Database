package com.example.guestdatabase.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.guestdatabase.GuestViewModel
import com.example.guestdatabase.R
import com.example.guestdatabase.databinding.AddGuestDialogBinding
import com.example.guestdatabase.databinding.FragmentEditBinding
import com.example.guestdatabase.databinding.FragmentGuestListBinding
import com.example.guestdatabase.db.model.Guest

class EditFragment : Fragment() {

    private lateinit var binding: FragmentEditBinding
    private val viewModel: GuestViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectedGuest.observe(viewLifecycleOwner){guest ->
            binding.editNameET.setText(guest.name)
            binding.editFoodET.setText(guest.food)
            binding.isComingCB.isChecked = guest.isComing
            binding.editCommentET.setText(guest.comment)

            binding.saveBTN.setOnClickListener {

                val updatedGuest = guest.copy(
                    name =  binding.editNameET.text.toString(),
                    food = binding.editFoodET.text.toString(),
                    isComing = binding.isComingCB.isChecked,
                    comment = binding.editCommentET.text.toString()
                )
                Log.d("SaveButton", "old Guest: $guest")
                Log.d("SaveButton", "new Guest: $updatedGuest")

                viewModel.upsertGuest(updatedGuest)

                findNavController().navigateUp()

            }

            binding.deleteBTN.setOnClickListener {
                viewModel.deleteGuest(guest)

                findNavController().navigateUp()
            }
        }




    }


}