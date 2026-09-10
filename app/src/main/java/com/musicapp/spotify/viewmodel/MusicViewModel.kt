package com.musicapp.spotify.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.musicapp.spotify.database.MusicDatabase
import com.musicapp.spotify.model.Song
import com.musicapp.spotify.repository.MusicRepository
import kotlinx.coroutines.launch

class MusicViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MusicRepository
    val allSongs: LiveData<List<Song>>
    val favoriteSongs: LiveData<List<Song>>

    init {
        val database = MusicDatabase.getDatabase(application)
        repository = MusicRepository(database)
        allSongs = repository.getAllSongs().asLiveData()
        favoriteSongs = repository.getFavoriteSongs().asLiveData()

        // Fetch songs from API on init
        viewModelScope.launch {
            repository.fetchAndSaveSongs()
        }
    }

    fun searchSongs(query: String): LiveData<List<Song>> {
        return repository.searchSongs(query).asLiveData()
    }

    fun addToFavorites(song: Song) {
        viewModelScope.launch {
            repository.addToFavorites(song)
        }
    }

    fun removeFromFavorites(songId: String) {
        viewModelScope.launch {
            repository.removeFromFavorites(songId)
        }
    }
}
