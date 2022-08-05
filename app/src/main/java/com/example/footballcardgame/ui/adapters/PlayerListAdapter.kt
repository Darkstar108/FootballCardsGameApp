package com.example.footballcardgame.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.footballcardgame.R
import com.example.footballcardgame.data.models.PlayerDetail
import com.example.footballcardgame.ui.viewModels.PlayerListViewModel
import com.squareup.picasso.Picasso
import com.example.footballcardgame.common.Constants
import com.example.footballcardgame.ui.fragments.PlayerListFragment
import com.example.footballcardgame.ui.fragments.PlayerListFragmentDirections

class PlayerListAdapter(): RecyclerView.Adapter<PlayerListAdapter.ViewHolder>() {

    private var playerDetails: ArrayList<PlayerDetail> = ArrayList<PlayerDetail>()
    lateinit var playerListViewModel: PlayerListViewModel

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
        }

        fun deletePlayerDetail(playerDetail: PlayerDetail) {
            deletePlayerButton.setOnClickListener {
                Log.d(Constants.LOG_TAG, "Deleting player ${playerDetail}")
                playerListViewModel.delete(playerDetail)
            }
        }

        fun editPlayerDetail(playerDetail: PlayerDetail, view: View) {
            editPlayerButton.setOnClickListener {
                val action = PlayerListFragmentDirections.actionNavPlayerListToNavAddPlayer(playerDetail)
                view.findNavController().navigate(action)
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
        holder.deletePlayerDetail(playerDetails[position])
        holder.editPlayerDetail(playerDetails[position], holder.itemView)
    }

    override fun getItemCount(): Int {
        return playerDetails.size
    }

    fun updateList(updatedPlayerDetails: ArrayList<PlayerDetail>) {
        Log.d("footballCardGame", "Updating player list with: ${updatedPlayerDetails}")
        playerDetails = updatedPlayerDetails
        notifyDataSetChanged()
    }

    fun setViewModel(playerListViewModel: PlayerListViewModel) {
        this.playerListViewModel = playerListViewModel
    }
}