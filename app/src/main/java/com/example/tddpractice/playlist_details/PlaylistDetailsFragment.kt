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
import com.example.tddpractice.databinding.FragmentPlaylistDetailBinding
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

        observeLiveData()
        return binding.root
    }

    private fun observeLiveData() {
        viewModel.playlistDetails.observe(this as LifecycleOwner) { playlistDetails ->
            if (playlistDetails.getOrNull() != null) {
                Log.d("test", "success")
                setupUi(playlistDetails)
            } else {
                // TODO: 1/15/22
                Log.d("test", "error: ${playlistDetails.exceptionOrNull()?.message}")
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
