package com.musicapp.spotify.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.musicapp.spotify.R
import com.musicapp.spotify.viewmodel.MusicViewModel

class HomeFragment : Fragment() {
    private lateinit var musicViewModel: MusicViewModel
    private lateinit var songsRecycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        musicViewModel = ViewModelProvider(this).get(MusicViewModel::class.java)
        songsRecycler = view.findViewById(R.id.songs_recycler)
        songsRecycler.layoutManager = LinearLayoutManager(requireContext())

        // Observe songs and update UI
        musicViewModel.allSongs.observe(viewLifecycleOwner) { songs ->
            // Update RecyclerView with songs
        }
    }
}
