package com.example.footballcardgame.ui.fragments

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.footballcardgame.R
import com.example.footballcardgame.common.Utils.replaceFragment
import com.example.footballcardgame.databinding.FragmentHomeBinding
import com.example.footballcardgame.ui.viewModels.PlayerListViewModel


class HomeFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeGameButton.setOnClickListener(this)
        binding.homePlayerListButton.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.home_game_button -> {

            }
            R.id.home_player_list_button -> {
                val playerListFragment = PlayerListFragment.newInstance()
                fragmentManager?.let { replaceFragment(playerListFragment, it) }
            }
        }
    }

}