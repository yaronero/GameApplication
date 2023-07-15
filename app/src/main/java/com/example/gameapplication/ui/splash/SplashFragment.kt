package com.example.gameapplication.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.application.databinding.FragmentSplashBinding
import com.example.gameapplication.domain.model.ApplicationMode
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.modeLiveData.collect { mode ->
                        navigateByMode(mode)
                    }
                }
                launch {
                    viewModel.isLoading.collect { isLoading ->
                        binding.progressBar.isVisible = isLoading
                    }
                }
            }
        }
    }

    private fun navigateByMode(mode: ApplicationMode) {
        when (mode) {
            ApplicationMode.Offline -> {
                val direction = SplashFragmentDirections.toGameFragment()
                findNavController().navigate(direction)
            }

            ApplicationMode.Online -> {
                val direction = SplashFragmentDirections.toWebViewFragment()
                findNavController().navigate(direction)
                requireActivity().overridePendingTransition(0, 0)
            }

            ApplicationMode.Undefined -> {}
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}