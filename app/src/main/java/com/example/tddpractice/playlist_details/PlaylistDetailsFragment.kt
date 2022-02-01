package com.example.tddpractice.playlist_details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.tddpractice.R
import com.example.tddpractice.databinding.FragmentPlaylistDetailBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistDetailsFragment : Fragment() {
    lateinit var viewModel: PlaylistDetailsViewModel
    private lateinit var _binding: FragmentPlaylistDetailBinding
    private val binding get() = _binding

    @Inject
    lateinit var viewModelFactory: PlaylistDetailsViewModelFactory
    private val args: PlaylistDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaylistDetailBinding.inflate(layoutInflater, container, false)
        val id = args.playlistId
        viewModel = ViewModelProvider(this, viewModelFactory)[PlaylistDetailsViewModel::class.java]

        Log.d("test", "id: $id")
        viewModel.getPlaylistDetails(id)

        observePlaylistDetails()
        observeLoader()
        return binding.root
    }

    private fun observeLoader() = binding.apply {
        viewModel.loader.observe(this@PlaylistDetailsFragment as LifecycleOwner) { loading ->
            when (loading) {
                true -> loaderPlaylistDetails.visibility = View.VISIBLE
                else -> loaderPlaylistDetails.visibility = View.GONE
            }
        }
    }

    private fun observePlaylistDetails() {
        viewModel.playlistDetails.observe(this as LifecycleOwner) { playlistDetails ->
            if (playlistDetails.getOrNull() != null) {
                Log.d("test", "success")
                setupUi(playlistDetails)
            } else {
                Snackbar.make(
                    binding.root,
                    R.string.generic_error,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun setupUi(
        playlistDetails: Result<PlaylistDetails>
    ) {
        binding.playlistName.text =
            playlistDetails.getOrNull()!!.name
        binding.playlistDetails.text =
            playlistDetails.getOrNull()!!.details
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PlaylistDetailsFragment()
    }
}
