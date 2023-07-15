package com.example.gameapplication.ui.game

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.setMargins
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.application.R
import com.example.application.databinding.FragmentGameBinding
import com.example.gameapplication.ui.custom.ColorView
import com.example.gameapplication.utils.getMixedColor
import com.example.gameapplication.utils.toPx
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GameViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener {
            val destination = GameFragmentDirections.toNextLevel()
            findNavController().navigate(destination)
        }

        setupObserver()
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.colors.collect { colors ->
                        showColors(colors)
                    }
                }
                launch {
                    viewModel.colorsToGuess.collect { colorsToGuess ->
                        showColorsToGuess(colorsToGuess)
                    }
                }
            }
        }
    }

    private fun showColors(colors: List<Color>) {
        colors.forEach { color ->
            val colorView = ColorView(requireContext()).apply {
                id = View.generateViewId()
                setColor(color)
            }
            val layoutParams = ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            binding.constraintView.addView(colorView, layoutParams)
            binding.flowColors.addView(colorView)
        }
    }

    private fun showColorsToGuess(colors: List<Color>) {
        colors.forEach { color ->
            val colorView = ColorView(requireContext()).apply {
                id = View.generateViewId()
                setColor(color)
            }
            colorView.setOnClickListener {
                if (colorView.getColor() == viewModel.colors.value.getMixedColor()) {
                    Toast.makeText(
                        context,
                        requireActivity().getString(R.string.correct),
                        Toast.LENGTH_SHORT
                    ).show()
                    val destination = GameFragmentDirections.toNextLevel()
                    findNavController().navigate(destination)
                } else {
                    Toast.makeText(
                        context,
                        requireActivity().getString(R.string.incorrect),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            val layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(10.toPx.toInt())
            binding.constraintView.addView(colorView, layoutParams)
            binding.flowGuessedColors.addView(colorView)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}