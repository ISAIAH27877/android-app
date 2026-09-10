package com.musicapp.spotify.api

import com.musicapp.spotify.model.Album
import com.musicapp.spotify.model.Artist
import com.musicapp.spotify.model.Playlist
import com.musicapp.spotify.model.Song
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MusicApiService {

    // Get all songs
    @GET("songs")
    suspend fun getAllSongs(): List<Song>

    // Search songs
    @GET("songs/search")
    suspend fun searchSongs(@Query("q") query: String): List<Song>

    // Get song by ID
    @GET("songs/{id}")
    suspend fun getSongById(@Path("id") id: String): Song

    // Get songs by artist
    @GET("artists/{artistId}/songs")
    suspend fun getSongsByArtist(@Path("artistId") artistId: String): List<Song>

    // Get songs by album
    @GET("albums/{albumId}/songs")
    suspend fun getSongsByAlbum(@Path("albumId") albumId: String): List<Song>

    // Get all playlists
    @GET("playlists")
    suspend fun getAllPlaylists(): List<Playlist>

    // Get playlist by ID
    @GET("playlists/{id}")
    suspend fun getPlaylistById(@Path("id") id: String): Playlist

    // Get songs in playlist
    @GET("playlists/{playlistId}/songs")
    suspend fun getPlaylistSongs(@Path("playlistId") playlistId: String): List<Song>

    // Get all artists
    @GET("artists")
    suspend fun getAllArtists(): List<Artist>

    // Get artist by ID
    @GET("artists/{id}")
    suspend fun getArtistById(@Path("id") id: String): Artist

    // Search artists
    @GET("artists/search")
    suspend fun searchArtists(@Query("q") query: String): List<Artist>

    // Get all albums
    @GET("albums")
    suspend fun getAllAlbums(): List<Album>

    // Get album by ID
    @GET("albums/{id}")
    suspend fun getAlbumById(@Path("id") id: String): Album

    // Search albums
    @GET("albums/search")
    suspend fun searchAlbums(@Query("q") query: String): List<Album>
}
