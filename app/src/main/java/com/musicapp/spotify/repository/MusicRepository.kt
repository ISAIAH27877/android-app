package com.musicapp.spotify.repository

import com.musicapp.spotify.api.RetrofitClient
import com.musicapp.spotify.database.MusicDatabase
import com.musicapp.spotify.model.Song
import kotlinx.coroutines.flow.Flow

class MusicRepository(private val database: MusicDatabase) {
    private val songDao = database.songDao()
    private val playlistDao = database.playlistDao()
    private val favoriteDao = database.favoriteDao()
    private val musicApi = RetrofitClient.musicApiService

    // Get all songs from local database
    fun getAllSongs(): Flow<List<Song>> = songDao.getAllSongs()

    // Search songs
    fun searchSongs(query: String): Flow<List<Song>> = songDao.searchSongs(query)

    // Get favorite songs
    fun getFavoriteSongs(): Flow<List<Song>> = favoriteDao.getFavoriteSongs()

    // Add to favorites
    suspend fun addToFavorites(song: Song) {
        favoriteDao.addFavorite(
            com.musicapp.spotify.model.Favorite(
                songId = song.id,
                addedDate = System.currentTimeMillis()
            )
        )
    }

    // Remove from favorites
    suspend fun removeFromFavorites(songId: String) {
        favoriteDao.removeFavorite(songId)
    }

    // Fetch songs from API and save to database
    suspend fun fetchAndSaveSongs() {
        try {
            val songs = musicApi.getAllSongs()
            songDao.insertSongs(songs)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
