package com.musicapp.spotify.database

import androidx.room.*
import com.musicapp.spotify.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSong(song: Song)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongs(songs: List<Song>)

    @Query("SELECT * FROM songs")
    fun getAllSongs(): Flow<List<Song>>

    @Query("SELECT * FROM songs WHERE id = :songId")
    suspend fun getSongById(songId: String): Song?

    @Query("SELECT * FROM songs WHERE title LIKE '%' || :query || '%' OR artist LIKE '%' || :query || '%'")
    fun searchSongs(query: String): Flow<List<Song>>

    @Update
    suspend fun updateSong(song: Song)

    @Delete
    suspend fun deleteSong(song: Song)
}

@Dao
interface PlaylistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylist(playlist: Playlist)

    @Query("SELECT * FROM playlists")
    fun getAllPlaylists(): Flow<List<Playlist>>

    @Query("SELECT * FROM playlists WHERE id = :playlistId")
    suspend fun getPlaylistById(playlistId: String): Playlist?

    @Update
    suspend fun updatePlaylist(playlist: Playlist)

    @Delete
    suspend fun deletePlaylist(playlist: Playlist)
}

@Dao
interface PlaylistSongDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSongToPlaylist(playlistSong: PlaylistSong)

    @Query("SELECT s.* FROM songs s INNER JOIN playlist_songs ps ON s.id = ps.songId WHERE ps.playlistId = :playlistId")
    fun getSongsInPlaylist(playlistId: String): Flow<List<Song>>

    @Query("DELETE FROM playlist_songs WHERE playlistId = :playlistId AND songId = :songId")
    suspend fun removeSongFromPlaylist(playlistId: String, songId: String)
}

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: Favorite)

    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): Flow<List<Favorite>>

    @Query("SELECT * FROM favorites WHERE songId = :songId")
    suspend fun getFavorite(songId: String): Favorite?

    @Query("DELETE FROM favorites WHERE songId = :songId")
    suspend fun removeFavorite(songId: String)

    @Query("SELECT s.* FROM songs s INNER JOIN favorites f ON s.id = f.songId ORDER BY f.addedDate DESC")
    fun getFavoriteSongs(): Flow<List<Song>>
}

@Dao
interface SearchHistoryDao {
    @Insert
    suspend fun insertSearchQuery(searchHistory: SearchHistory)

    @Query("SELECT * FROM search_history ORDER BY timestamp DESC LIMIT 10")
    fun getRecentSearches(): Flow<List<SearchHistory>>

    @Query("DELETE FROM search_history WHERE id = :id")
    suspend fun deleteSearch(id: Int)

    @Query("DELETE FROM search_history")
    suspend fun clearSearchHistory()
}

@Dao
interface ArtistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtist(artist: Artist)

    @Query("SELECT * FROM artists WHERE id = :artistId")
    suspend fun getArtistById(artistId: String): Artist?

    @Query("SELECT * FROM artists WHERE name LIKE '%' || :query || '%'")
    fun searchArtists(query: String): Flow<List<Artist>>
}
