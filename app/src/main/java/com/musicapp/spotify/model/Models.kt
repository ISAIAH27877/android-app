package com.musicapp.spotify.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// Song data model
@Entity(tableName = "songs")
data class Song(
    @PrimaryKey
    val id: String,
    val title: String,
    val artist: String,
    val album: String,
    val duration: Long,
    val imageUrl: String,
    val audioUrl: String,
    val isDownloaded: Boolean = false,
    val localPath: String? = null
)

// Artist data model
@Entity(tableName = "artists")
data class Artist(
    @PrimaryKey
    val id: String,
    val name: String,
    val imageUrl: String,
    val bio: String
)

// Album data model
@Entity(tableName = "albums")
data class Album(
    @PrimaryKey
    val id: String,
    val title: String,
    val artist: String,
    val imageUrl: String,
    val releaseDate: String
)

// Playlist data model
@Entity(tableName = "playlists")
data class Playlist(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val createdDate: Long,
    val songCount: Int
)

// Playlist Song Junction
@Entity(tableName = "playlist_songs", primaryKeys = ["playlistId", "songId"])
data class PlaylistSong(
    val playlistId: String,
    val songId: String,
    val addedDate: Long
)

// Search History
@Entity(tableName = "search_history")
data class SearchHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val query: String,
    val timestamp: Long
)

// Favorite Songs
@Entity(tableName = "favorites")
data class Favorite(
    @PrimaryKey
    val songId: String,
    val addedDate: Long
)
