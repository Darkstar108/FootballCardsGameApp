package com.example.footballcardgame.ui.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.footballcardgame.R
import com.example.footballcardgame.data.models.PlayerDetail
import com.example.footballcardgame.ui.adapters.PlayerListAdapter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log


class PlayerListActivity : AppCompatActivity() {
    private var playerListAdapter: PlayerListAdapter? = null
    private var playerDetails: ArrayList<PlayerDetail>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_list)

        playerDetails = arrayListOf<PlayerDetail>(
            PlayerDetail("Lionel Messi","Argentina","Forward",33,"https://pesdb.net/pes2021/images/players/7511.png",99,85,20),
            PlayerDetail("Kevin De Bruyne","Belgium","Midfielder",29,"https://pesdb.net/pes2021/images/players/44379.png",80,95,60),
            PlayerDetail("Neymar","Brazil","Forward",28,"https://pesdb.net/pes2021/images/players/40352.png",90,75,30),
            PlayerDetail("Kylian Mbappe","France","Forward",22,"https://pesdb.net/pes2021/images/players/110718.png",95,65,25),
            PlayerDetail("Cristiano Ronaldo","Portugal","Forward",35,"https://pesdb.net/pes2021/images/players/4522.png",95,55,20),
        )

        val searchBarEditText: EditText = findViewById(R.id.search_bar_edit_text)

        var playerListRecyclerView: RecyclerView = findViewById(R.id.player_list_recycler_view)
        playerListRecyclerView.layoutManager = LinearLayoutManager(this)
        playerListRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        playerListAdapter = PlayerListAdapter(playerDetails!!)
        playerListRecyclerView.adapter = playerListAdapter
        playerListRecyclerView.setHasFixedSize(true)

        searchBarEditText.addTextChangedListener(textWatcher)
    }

    private val textWatcher = object: TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(editable: Editable?) {
            filterPlayers(editable.toString())
        }

    }

    private fun filterPlayers(query: String) {
        var filteredPlayerDetails: ArrayList<PlayerDetail> = mutableListOf<PlayerDetail>() as ArrayList<PlayerDetail>
        filteredPlayerDetails = playerDetails?.filter { it.name.lowercase().contains(query.lowercase()) } as ArrayList<PlayerDetail>
        Log.d("footballCardGame", "${filteredPlayerDetails.toString()}")
        playerListAdapter?.updateList(filteredPlayerDetails)
    }

}