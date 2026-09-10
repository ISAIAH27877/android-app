package com.musicapp.spotify.service

import android.content.Intent
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import com.musicapp.spotify.model.Song

class MusicService : MediaSessionService() {

    private var mediaSession: MediaSession? = null

    override fun onCreate() {
        super.onCreate()

        // Initialize ExoPlayer
        val player = ExoPlayer.Builder(this).build()

        // Initialize MediaSession
        mediaSession = MediaSession.Builder(this, player).build()
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? =
        mediaSession

    override fun onDestroy() {
        mediaSession?.run {
            player.release()
            release()
            mediaSession = null
        }
        super.onDestroy()
    }

    // Play a song
    fun playSong(song: Song) {
        val mediaItem = MediaItem.Builder()
            .setMediaId(song.id)
            .setUri(song.audioUrl)
            .setMediaMetadata(
                androidx.media3.common.MediaMetadata.Builder()
                    .setTitle(song.title)
                    .setArtist(song.artist)
                    .setAlbumTitle(song.album)
                    .build()
            )
            .build()

        mediaSession?.player?.apply {
            setMediaItem(mediaItem)
            prepare()
            play()
        }
    }

    // Pause playback
    fun pausePlayback() {
        mediaSession?.player?.pause()
    }

    // Resume playback
    fun resumePlayback() {
        mediaSession?.player?.play()
    }

    // Stop playback
    fun stopPlayback() {
        mediaSession?.player?.stop()
    }

    // Get current position
    fun getCurrentPosition(): Long {
        return mediaSession?.player?.currentPosition ?: 0L
    }

    // Set media items (queue)
    fun setQueue(songs: List<Song>) {
        val mediaItems = songs.map { song ->
            MediaItem.Builder()
                .setMediaId(song.id)
                .setUri(song.audioUrl)
                .setMediaMetadata(
                    androidx.media3.common.MediaMetadata.Builder()
                        .setTitle(song.title)
                        .setArtist(song.artist)
                        .build()
                )
                .build()
        }

        mediaSession?.player?.apply {
            setMediaItems(mediaItems)
            prepare()
        }
    }

    // Skip to next
    fun skipToNext() {
        mediaSession?.player?.seekToNext()
    }

    // Skip to previous
    fun skipToPrevious() {
        mediaSession?.player?.seekToPrevious()
    }

    // Seek to position
    fun seekToPosition(positionMs: Long) {
        mediaSession?.player?.seekTo(positionMs)
    }
}
