package com.example.footballcardgame.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.footballcardgame.R
import com.example.footballcardgame.data.models.PlayerDetail
import com.squareup.picasso.Picasso

class PlayerListAdapter(private var playerDetails: ArrayList<PlayerDetail>): RecyclerView.Adapter<PlayerListAdapter.ViewHolder>() {

    inner  class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {

        val playerCardImage: ImageView = itemView.findViewById(R.id.player_card_image)
        val playerCardName: TextView = itemView.findViewById(R.id.player_card_name)
        val playerCardNationality: TextView = itemView.findViewById(R.id.player_card_nationality)
        val playerCardAge: TextView = itemView.findViewById(R.id.player_card_age)
        val playerCardPosition: TextView = itemView.findViewById(R.id.player_card_position)
        val playerCardAttack: TextView = itemView.findViewById(R.id.player_card_attack)
        val playerCardMidfield: TextView = itemView.findViewById(R.id.player_card_midfield)
        val playerCardDefence: TextView = itemView.findViewById(R.id.player_card_defence)
        val deletePlayerButton: Button = itemView.findViewById(R.id.delete_player_button)
        val editPlayerButton: Button = itemView.findViewById(R.id.edit_player_button)

        init {
            deletePlayerButton.setOnClickListener {
                val position = adapterPosition
                Log.d("footballCardGame", "Deleting player in position:$position")
                val updatedPlayerDetails = playerDetails
                updatedPlayerDetails.removeAt(position)
                updateList(updatedPlayerDetails)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.player_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            Picasso.get().load(playerDetails[position].imageUrl).into(playerCardImage)
            playerCardName.text = playerDetails[position].name
            playerCardNationality.text = playerDetails[position].nationality
            playerCardAge.text = playerDetails[position].age.toString()
            playerCardPosition.text = playerDetails[position].position
            playerCardAttack.text = playerDetails[position].attack.toString()
            playerCardMidfield.text = playerDetails[position].midfield.toString()
            playerCardDefence.text = playerDetails[position].defence.toString()
        }
    }

    override fun getItemCount(): Int {
        return playerDetails.size
    }

    fun updateList(updatedPlayerDetails: ArrayList<PlayerDetail>) {
        playerDetails = updatedPlayerDetails
        notifyDataSetChanged()
    }
}