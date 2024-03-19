package com.example.guestdatabase

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.guestdatabase.databinding.GuestItemBinding
import com.example.guestdatabase.db.model.Guest

class GuestAdapter(private val guests: List<Guest>) :
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

    }

}