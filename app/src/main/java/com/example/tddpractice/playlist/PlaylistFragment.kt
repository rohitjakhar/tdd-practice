package com.example.tddpractice.playlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tddpractice.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistFragment : Fragment() {

    lateinit var viewModel: PlaylistViewModel

    @Inject
    lateinit var viewModelFactory: PlaylistViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)
        setupViewModel()

        observeLoader(view)
        observePlaylist(view)
        return view
    }

    private fun observePlaylist(view: View) {
        viewModel.playlist.observe(this as LifecycleOwner, { playlists ->
            if (playlists.getOrNull() != null)
                setupList(view.findViewById(R.id.playlists_list), playlists.getOrNull()!!)
            else {
                // TODO: 1/15/22
            }
        })
    }

    private fun observeLoader(view: View) {
        viewModel.loader.observe(this as LifecycleOwner, { loading ->
            when (loading) {
                true -> view.findViewById<ProgressBar>(R.id.loader).visibility = View.VISIBLE
                else -> view.findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
            }
        })
    }

    private fun setupList(
        view: View?,
        playlists: List<Playlist>
    ) {
        with(view as RecyclerView) {
            layoutManager = LinearLayoutManager(requireContext())

            adapter = MyPlaylistRecyclerViewAdapter(playlists) { id ->
                val action =
                    PlaylistFragmentDirections.actionPlaylistFragmentToPlaylistDetailFragment(id)
                findNavController().navigate(action)
            }
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlaylistViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PlaylistFragment().apply {
            }
    }
}
