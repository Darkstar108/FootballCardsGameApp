package com.example.footballcardgame.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.footballcardgame.R
import com.example.footballcardgame.common.Constants
import com.example.footballcardgame.common.TextValidator
import com.example.footballcardgame.common.Utils
import com.example.footballcardgame.data.models.PlayerDetail
import com.example.footballcardgame.databinding.FragmentAddPlayerBinding
import com.example.footballcardgame.databinding.FragmentHomeBinding
import com.example.footballcardgame.ui.viewModels.AddPlayerViewModel
import com.example.footballcardgame.ui.viewModels.PlayerListViewModel


class AddPlayerFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance() = AddPlayerFragment()
    }

    private enum class AddEditFlag {
        ADD_PLAYER, EDIT_PLAYER
    }

    private var _binding: FragmentAddPlayerBinding? = null
    private val binding get() = _binding!!
    private var addEditFlag: AddEditFlag = AddEditFlag.ADD_PLAYER
    private val playerListViewModel: PlayerListViewModel by activityViewModels()

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
        addEditFlag =
            if(args.playerDetail.name.equals(""))
                AddEditFlag.ADD_PLAYER
            else
                AddEditFlag.EDIT_PLAYER

        if(addEditFlag == AddEditFlag.EDIT_PLAYER) {
            binding.playerAddNameInput.setText(args.playerDetail.name)
            binding.playerAddNameInput.isEnabled = false
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


        binding.playerAddSubmitButton.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.player_add_submit_button -> {
                val checkedPositionRadioButton = binding.playerAddPositionInput.checkedRadioButtonId
                val checkedRadioButton: RadioButton = binding.playerAddPositionInput.findViewById(checkedPositionRadioButton)
                var position = checkedRadioButton.text.toString()
                var playerDetail = PlayerDetail(
                    binding.playerAddNameInput.text.toString(),
                    binding.playerAddNationalityInput.text.toString(),
                    position,
                    binding.playerAddAgeInput.text.toString().toInt(),
                    binding.playerAddImageUrlInput.text.toString(),
                    binding.playerAddAttackInput.text.toString().toInt(),
                    binding.playerAddMidfieldInput.text.toString().toInt(),
                    binding.playerAddDefenceInput.text.toString().toInt()
                )
                if(addEditFlag == AddEditFlag.ADD_PLAYER) {
                    Log.d(Constants.LOG_TAG, "Adding: ${playerDetail}")
                    playerListViewModel.insert(playerDetail)
                }
                else {
                    Log.d(Constants.LOG_TAG, "Updating: ${playerDetail}")
                    playerListViewModel.update(playerDetail)
                }
                val action = AddPlayerFragmentDirections.actionNavAddPlayerToNavPlayerList()
                view.findNavController().navigate(action)
            }
        }
    }

}