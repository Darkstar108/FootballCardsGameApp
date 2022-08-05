package com.example.footballcardgame.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.footballcardgame.databinding.FragmentAddPlayerBinding
import com.example.footballcardgame.databinding.FragmentHomeBinding
import com.example.footballcardgame.ui.viewModels.AddPlayerViewModel
import com.example.footballcardgame.ui.viewModels.PlayerListViewModel


class AddPlayerFragment : Fragment() {

    companion object {
        fun newInstance() = AddPlayerFragment()
    }

    private enum class AddEditFlag {
        ADD_PLAYER, EDIT_PLAYER
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

        val args: AddPlayerFragmentArgs by navArgs()
        val addEditFlag =
            if(args.playerDetail.name.equals(""))
                AddEditFlag.ADD_PLAYER
            else
                AddEditFlag.EDIT_PLAYER

        if(addEditFlag == AddEditFlag.EDIT_PLAYER) {
            binding.playerAddNameInput.setText(args.playerDetail.name)
            binding.playerAddNationalityInput.setText(args.playerDetail.nationality)
            binding.playerAddAgeInput.setText(args.playerDetail.age.toString())
            when(args.playerDetail.position) {
                "Forward" -> binding.playerAddPositionInput.check(binding.playerAddForwardRadioButton.id)
                "Midfielder" -> binding.playerAddPositionInput.check(binding.playerAddMidfielderRadioButton.id)
                "Defender" -> binding.playerAddPositionInput.check(binding.playerAddDefenderRadioButton.id)
            }
            binding.playerAddImageUrlInput.setText(args.playerDetail.imageUrl)
            binding.playerAddAttackInput.setText(args.playerDetail.attack.toString())
            binding.playerAddMidfieldInput.setText(args.playerDetail.midfield.toString())
            binding.playerAddDefenceInput.setText(args.playerDetail.defence.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}