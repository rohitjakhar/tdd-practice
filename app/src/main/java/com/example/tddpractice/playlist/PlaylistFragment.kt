package com.example.tddpractice.playlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tddpractice.R

class PlaylistFragment : Fragment() {

    lateinit var viewModel: PlaylistViewModel
    lateinit var viewModelFactory: PlaylistViewModelFactory

    private val service = PlaylistService()
    private val repository = PlaylistRepository(service)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)

        setupViewModel()
        viewModel.playlist.observe(this as LifecycleOwner, { playlists ->
            if (playlists.getOrNull() != null)
                setupList(view, playlists.getOrNull()!!)
            else {
                // TODO: 1/15/22
            }
        })
        return view
    }

    private fun setupList(
        view: View?,
        playlists: List<Playlist>
    ) {
        with(view as RecyclerView) {
            layoutManager = LinearLayoutManager(requireContext())

            adapter = MyPlaylistRecyclerViewAdapter(playlists)
        }
    }

    private fun setupViewModel() {
        viewModelFactory = PlaylistViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlaylistViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PlaylistFragment().apply {
            }
    }
}
