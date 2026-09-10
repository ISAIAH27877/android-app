package com.musicapp.spotify.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.musicapp.spotify.R
import com.musicapp.spotify.viewmodel.PlaylistViewModel

class PlaylistFragment : Fragment() {
    private lateinit var playlistViewModel: PlaylistViewModel
    private lateinit var playlistRecycler: RecyclerView
    private lateinit var fabCreatePlaylist: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_playlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playlistViewModel = ViewModelProvider(this).get(PlaylistViewModel::class.java)
        playlistRecycler = view.findViewById(R.id.playlists_recycler)
        fabCreatePlaylist = view.findViewById(R.id.fab_create_playlist)

        playlistRecycler.layoutManager = LinearLayoutManager(requireContext())

        // Observe playlists
        playlistViewModel.allPlaylists.observe(viewLifecycleOwner) { playlists ->
            // Update RecyclerView with playlists
        }

        // Create new playlist
        fabCreatePlaylist.setOnClickListener {
            showCreatePlaylistDialog()
        }
    }

    private fun showCreatePlaylistDialog() {
        // Show dialog to create new playlist
    }
}
