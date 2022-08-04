package com.example.footballcardgame.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.footballcardgame.databinding.FragmentAddPlayerBinding
import com.example.footballcardgame.databinding.FragmentHomeBinding
import com.example.footballcardgame.ui.viewModels.AddPlayerViewModel
import com.example.footballcardgame.ui.viewModels.PlayerListViewModel


class AddPlayerFragment : Fragment() {

    companion object {
        fun newInstance() = AddPlayerFragment()
    }

    private var _binding: FragmentAddPlayerBinding? = null
    private val binding get() = _binding!!

    lateinit var addPlayerViewModel: AddPlayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddPlayerBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}