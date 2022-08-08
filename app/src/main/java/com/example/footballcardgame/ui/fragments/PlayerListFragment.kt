package com.example.footballcardgame.ui.fragments

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballcardgame.R
import com.example.footballcardgame.common.Utils
import com.example.footballcardgame.data.models.PlayerDetail
import com.example.footballcardgame.databinding.FragmentPlayerListBinding
import com.example.footballcardgame.ui.adapters.PlayerListAdapter
import com.example.footballcardgame.ui.viewModels.PlayerListViewModel

class PlayerListFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance() = PlayerListFragment()
    }

    private val playerListViewModel: PlayerListViewModel by activityViewModels()
    private var _binding: FragmentPlayerListBinding? = null
    private val binding get() = _binding!!
    private var playerListAdapter: PlayerListAdapter = PlayerListAdapter()
    private var playerDetails: ArrayList<PlayerDetail>? = null

    private val playerDetailsObserver = Observer<List<PlayerDetail>> {
        it?.let { playerListAdapter.updateList(it as ArrayList<PlayerDetail>) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        setUpRecyclerView()

        binding.addPlayerFab.setOnClickListener(this)

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

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.add_player_fab -> {
                val bundle = Bundle()
                val dummyPlayer = PlayerDetail("","","",0,"",0,0,0)
                val action = PlayerListFragmentDirections.actionNavPlayerListToNavAddPlayer(dummyPlayer)
                view.findNavController().navigate(action)
            }
        }
    }

}