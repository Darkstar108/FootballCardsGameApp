package com.example.footballcardgame.ui.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballcardgame.R
import com.example.footballcardgame.data.models.PlayerDetail
import com.example.footballcardgame.databinding.FragmentHomeBinding
import com.example.footballcardgame.databinding.FragmentPlayerListBinding
import com.example.footballcardgame.ui.activities.PlayerListActivity
import com.example.footballcardgame.ui.adapters.PlayerListAdapter
import com.example.footballcardgame.ui.viewModels.HomeViewModel

class PlayerListFragment : Fragment() {

    companion object {
        fun newInstance() = PlayerListFragment()
    }

    private var _binding: FragmentPlayerListBinding? = null
    private val binding get() = _binding!!
    private var playerListAdapter: PlayerListAdapter? = null
    private var playerDetails: ArrayList<PlayerDetail>? = null

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPlayerListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playerDetails = arrayListOf<PlayerDetail>(
            PlayerDetail("Lionel Messi","Argentina","Forward",33,"https://pesdb.net/pes2021/images/players/7511.png",99,85,20),
            PlayerDetail("Kevin De Bruyne","Belgium","Midfielder",29,"https://pesdb.net/pes2021/images/players/44379.png",80,95,60),
            PlayerDetail("Neymar","Brazil","Forward",28,"https://pesdb.net/pes2021/images/players/40352.png",90,75,30),
            PlayerDetail("Kylian Mbappe","France","Forward",22,"https://pesdb.net/pes2021/images/players/110718.png",95,65,25),
            PlayerDetail("Cristiano Ronaldo","Portugal","Forward",35,"https://pesdb.net/pes2021/images/players/4522.png",95,55,20),
        )

        binding.searchBarEditText.addTextChangedListener(textWatcher)
        binding.playerListRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.playerListRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        playerListAdapter = PlayerListAdapter(playerDetails!!)
        binding.playerListRecyclerView.adapter = playerListAdapter
        binding.playerListRecyclerView.setHasFixedSize(true)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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