package com.example.footballcardgame.ui.fragments

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballcardgame.R
import com.example.footballcardgame.data.models.PlayerDetail
import com.example.footballcardgame.databinding.FragmentPlayerListBinding
import com.example.footballcardgame.ui.adapters.PlayerListAdapter
import com.example.footballcardgame.ui.viewModels.PlayerListViewModel

class PlayerListFragment : Fragment() {

    companion object {
        fun newInstance() = PlayerListFragment()
    }

    lateinit var playerListViewModel: PlayerListViewModel
    private var _binding: FragmentPlayerListBinding? = null
    private val binding get() = _binding!!
    private var playerListAdapter: PlayerListAdapter = PlayerListAdapter()
    private var playerDetails: ArrayList<PlayerDetail>? = null

    private val playerDetailsObserver = Observer<List<PlayerDetail>> {
        it?.let { playerListAdapter.updateList(it as ArrayList<PlayerDetail>) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        playerListViewModel = ViewModelProvider(this).get(PlayerListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPlayerListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
        val searchItem = menu.findItem(R.id.search_bar)
        val searchView = searchItem.actionView as SearchView

//        searchView.apply {
//            // Assumes current activity is the searchable activity
//            setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))
//            setIconifiedByDefault(false) // Do not iconify the widget; expand it by default
//        }

        searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.d("footballCardGame", "SearchText: ${query}")
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    Log.d("footballCardGame", "SearchText: ${newText}")
                    if (newText != null) {
                        filterPlayers(newText)
                    }
                    return false
                }
            }
        )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playerListViewModel.playerDetails.observe(viewLifecycleOwner, playerDetailsObserver)

        playerDetails = arrayListOf<PlayerDetail>(
            PlayerDetail("Lionel Messi","Argentina","Forward",33,"https://pesdb.net/pes2021/images/players/7511.png",99,85,20),
            PlayerDetail("Kevin De Bruyne","Belgium","Midfielder",29,"https://pesdb.net/pes2021/images/players/44379.png",80,95,60),
            PlayerDetail("Neymar","Brazil","Forward",28,"https://pesdb.net/pes2021/images/players/40352.png",90,75,30),
            PlayerDetail("Kylian Mbappe","France","Forward",22,"https://pesdb.net/pes2021/images/players/110718.png",95,65,25),
            PlayerDetail("Cristiano Ronaldo","Portugal","Forward",35,"https://pesdb.net/pes2021/images/players/4522.png",95,55,20),
        )

        setUpRecyclerView()

        for(playerDetail in playerDetails!!) {
            playerListViewModel.insert(playerDetail)
        }

        setHasOptionsMenu(true)

    }

    private fun setUpRecyclerView() {
        binding.playerListRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.playerListRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        playerListAdapter.setViewModel(playerListViewModel)
        binding.playerListRecyclerView.adapter = playerListAdapter
        binding.playerListRecyclerView.setHasFixedSize(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        playerListViewModel.playerDetails.removeObserver(playerDetailsObserver)
    }

    private fun filterPlayers(query: String) {
        var filteredPlayerDetails = playerListViewModel.playerDetails.value?.filter { it.name.lowercase().contains(query.lowercase()) } as ArrayList<PlayerDetail>
        Log.d("footballCardGame", "${filteredPlayerDetails.toString()}")
        playerListAdapter?.updateList(filteredPlayerDetails)
    }

}