package com.example.guestdatabase.ui

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.guestdatabase.GuestViewModel
import com.example.guestdatabase.R
import com.example.guestdatabase.databinding.GuestItemBinding
import com.example.guestdatabase.db.model.Guest

class GuestAdapter(
    private val guests: List<Guest>,
    val viewModel: GuestViewModel,
) :
    RecyclerView.Adapter<GuestAdapter.GuestViewHolder>() {

    inner class GuestViewHolder(val binding: GuestItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return guests.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val binding = GuestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        val guest = guests[position]
        Log.d("Adapter", "$guest")
        holder.binding.guestNameTV.text = guest.name
        holder.binding.guestFoodTV.text = guest.food

        if(guest.isComing) {
            holder.binding.guestItemCL.setBackgroundColor(Color.GREEN)
        } else {
            holder.binding.guestItemCL.setBackgroundColor(Color.TRANSPARENT)
        }

        holder.binding.guestItemCL.setOnClickListener {

            viewModel.selectGuest(guest)

            val navController = holder.binding.root.findNavController()
            navController.navigate(R.id.editFragment)
        }

    }

}