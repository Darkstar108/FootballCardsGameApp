package com.example.footballcardgame.ui.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.footballcardgame.data.models.PlayerDetail

class PlayerDatabaseAdapter(private val playerDetails: ArrayList<PlayerDetail>): RecyclerView.Adapter<PlayerDatabaseAdapter.ViewHolder>() {

    inner  class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {

        init {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return playerDetails.size
    }
}