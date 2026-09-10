package com.musicapp.spotify.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.musicapp.spotify.database.MusicDatabase
import com.musicapp.spotify.model.Playlist
import com.musicapp.spotify.repository.PlaylistRepository
import kotlinx.coroutines.launch

class PlaylistViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PlaylistRepository
    val allPlaylists: LiveData<List<Playlist>>

    init {
        val database = MusicDatabase.getDatabase(application)
        repository = PlaylistRepository(database)
        allPlaylists = repository.getAllPlaylists().asLiveData()
    }

    fun createPlaylist(name: String, description: String = "") {
        viewModelScope.launch {
            repository.createPlaylist(name, description)
        }
    }

    fun deletePlaylist(playlist: Playlist) {
        viewModelScope.launch {
            repository.deletePlaylist(playlist)
        }
    }

    fun updatePlaylist(playlist: Playlist) {
        viewModelScope.launch {
            repository.updatePlaylist(playlist)
        }
    }
}
