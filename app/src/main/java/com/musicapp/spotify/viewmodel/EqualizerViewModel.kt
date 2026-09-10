package com.musicapp.spotify.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.musicapp.spotify.model.EqualizerPreset
import com.musicapp.spotify.model.EqualizerPresets

class EqualizerViewModel : ViewModel() {
    private val _currentPreset = MutableLiveData<EqualizerPreset>(EqualizerPresets.NORMAL)
    val currentPreset: LiveData<EqualizerPreset> = _currentPreset

    private val _bands = MutableLiveData<FloatArray>(floatArrayOf(0f, 0f, 0f, 0f, 0f))
    val bands: LiveData<FloatArray> = _bands

    fun applyPreset(preset: EqualizerPreset) {
        _currentPreset.value = preset
        _bands.value = preset.bands
    }

    fun setBandLevel(bandIndex: Int, level: Float) {
        val currentBands = _bands.value?.copyOf() ?: FloatArray(5)
        if (bandIndex in currentBands.indices) {
            currentBands[bandIndex] = level
            _bands.value = currentBands
        }
    }

    fun resetEqualizer() {
        applyPreset(EqualizerPresets.NORMAL)
    }
}
