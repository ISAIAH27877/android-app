package com.musicapp.spotify.model

data class EqualizerPreset(
    val name: String,
    val bands: FloatArray // Array of frequency band adjustments (-15dB to +15dB)
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EqualizerPreset

        if (name != other.name) return false
        if (!bands.contentEquals(other.bands)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + bands.contentHashCode()
        return result
    }
}

object EqualizerPresets {
    val NORMAL = EqualizerPreset("Normal", floatArrayOf(0f, 0f, 0f, 0f, 0f))
    val BASS_BOOST = EqualizerPreset("Bass Boost", floatArrayOf(8f, 6f, 4f, 2f, 0f))
    val TREBLE_BOOST = EqualizerPreset("Treble Boost", floatArrayOf(0f, 2f, 4f, 6f, 8f))
    val CLASSICAL = EqualizerPreset("Classical", floatArrayOf(4f, 2f, -2f, 2f, 4f))
    val POP = EqualizerPreset("Pop", floatArrayOf(2f, 4f, 4f, 2f, 0f))
    val ROCK = EqualizerPreset("Rock", floatArrayOf(6f, 4f, -2f, 2f, 6f))
    val JAZZ = EqualizerPreset("Jazz", floatArrayOf(4f, 2f, 0f, 2f, 4f))

    val ALL = listOf(NORMAL, BASS_BOOST, TREBLE_BOOST, CLASSICAL, POP, ROCK, JAZZ)
}
