package com.musicapp.spotify.repository

import com.musicapp.spotify.database.MusicDatabase
import com.musicapp.spotify.model.Playlist
import kotlinx.coroutines.flow.Flow

class PlaylistRepository(private val database: MusicDatabase) {
    private val playlistDao = database.playlistDao()

    fun getAllPlaylists(): Flow<List<Playlist>> = playlistDao.getAllPlaylists()

    fun getPlaylistById(playlistId: Long): Flow<Playlist> = playlistDao.getPlaylistById(playlistId)

    suspend fun createPlaylist(name: String, description: String = ""): Long {
        val playlist = Playlist(
            name = name,
            description = description
        )
        return playlistDao.insertPlaylist(playlist)
    }

    suspend fun updatePlaylist(playlist: Playlist) {
        playlistDao.updatePlaylist(playlist)
    }

    suspend fun deletePlaylist(playlist: Playlist) {
        playlistDao.deletePlaylist(playlist)
    }
}
