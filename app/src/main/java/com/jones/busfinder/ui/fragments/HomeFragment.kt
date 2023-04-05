package com.jones.busfinder.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.jones.busfinder.R
import com.jones.busfinder.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val parentActivity = requireActivity()
        val navHostFragment =
            parentActivity.supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val drawable = AppCompatResources.getDrawable(requireContext(), R.drawable.custom_button)
        val transparentColor = parentActivity.getColor(R.color.transparent)

        binding.idBt.setOnClickListener {
            binding.idBt.background = drawable
            binding.sdBt.setBackgroundColor(transparentColor)
            binding.stopBt.setBackgroundColor(transparentColor)
            binding.enterTv1.text = getText(R.string.enter_id)
            binding.inputEt1.setText("")
            binding.inputEt2.setText("")
            binding.enterTv2.visibility = View.GONE
            binding.inputEt2.visibility = View.GONE
        }

        binding.sdBt.setOnClickListener {
            binding.sdBt.background = drawable
            binding.idBt.setBackgroundColor(transparentColor)
            binding.stopBt.setBackgroundColor(transparentColor)
            binding.inputEt1.setText("")
            binding.inputEt2.setText("")
            binding.enterTv1.text = getText(R.string.enter_source)
            binding.enterTv2.visibility = View.VISIBLE
            binding.enterTv2.text = getText(R.string.enter_destin)
            binding.inputEt2.visibility = View.VISIBLE
        }

        binding.stopBt.setOnClickListener {
            binding.stopBt.background = drawable
            binding.sdBt.setBackgroundColor(transparentColor)
            binding.idBt.setBackgroundColor(transparentColor)
            binding.inputEt1.setText("")
            binding.inputEt2.setText("")
            binding.enterTv1.text = getText(R.string.enter_stop)
            binding.enterTv2.visibility = View.GONE
            binding.inputEt2.visibility = View.GONE
        }

        binding.searchBt.setOnClickListener {
            if (binding.enterTv1.text.equals(getString(R.string.enter_id))) {
                val input1 = binding.inputEt1.text.toString().trim()
                if (input1.isNotEmpty() && input1.isNotBlank()) {
                    val action =
                        HomeFragmentDirections.actionHomeFragmentToBusesFragment(0, input1, "")
                    navController.navigate(action)
                } else {
                    Toast.makeText(requireContext(), "Fields are empty!", Toast.LENGTH_SHORT).show()
                }
            } else if (binding.enterTv1.text.equals(getString(R.string.enter_source))) {
                val input1 = binding.inputEt1.text.toString().trim()
                val input2 = binding.inputEt2.text.toString().trim()
                if (input1.isNotEmpty() && input1.isNotBlank() && input2.isNotEmpty() && input2.isNotBlank()) {
                    val action =
                        HomeFragmentDirections.actionHomeFragmentToBusesFragment(1, input1, input2)
                    navController.navigate(action)
                } else {
                    Toast.makeText(requireContext(), "Fields are empty!", Toast.LENGTH_SHORT).show()
                }
            } else if (binding.enterTv1.text.equals(getString(R.string.enter_stop))) {
                val input1 = binding.inputEt1.text.toString().trim()
                if (input1.isNotEmpty() && input1.isNotBlank()) {
                    val action =
                        HomeFragmentDirections.actionHomeFragmentToBusesFragment(2, input1, "")
                    navController.navigate(action)
                } else {
                    Toast.makeText(requireContext(), "Fields are empty!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return binding.root
    }
}