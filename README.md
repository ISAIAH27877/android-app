# Music Player App

A Spotify-like music streaming application with offline listening capabilities for Android.

## Features

✅ **Music Streaming** - Stream music from your favorite artists
✅ **Offline Listening** - Download songs for offline playback
✅ **Ad-Free** - Enjoy music without ads
✅ **Search** - Search for songs, artists, and albums
✅ **Playlists** - Create and manage playlists
✅ **Favorites** - Save your favorite songs
✅ **Modern UI** - Beautiful Material Design interface

## Tech Stack

- **Language**: Kotlin
- **Architecture**: MVVM with Repository Pattern
- **Database**: Room (SQLite)
- **Networking**: Retrofit + OkHttp
- **Media Playback**: ExoPlayer (Media3)
- **Image Loading**: Glide
- **Async**: Coroutines
- **UI**: Material Design 3

## Installation

1. Clone the repository
2. Open in Android Studio
3. Build and run on an Android device or emulator

## Building the APK

### Debug APK
```bash
./gradlew assembleDebug
```

### Release APK
```bash
./gradlew assembleRelease
```

The APK will be generated in `app/build/outputs/apk/`

## Project Structure

```
app/src/main/
├── java/com/musicapp/spotify/
│   ├── api/           # Retrofit API services
│   ├── database/      # Room database and DAOs
│   ├── model/         # Data models
│   ├── repository/    # Repository pattern
│   ├── service/       # Background services
│   ├── ui/            # UI fragments and activities
│   └── viewmodel/     # ViewModel classes
└── res/
    ├── layout/        # XML layouts
    ├── drawable/      # Drawables and shapes
    ├── menu/          # Menu resources
    └── values/        # Colors, strings, themes
```

## Permissions Required

- `INTERNET` - For streaming music
- `READ_EXTERNAL_STORAGE` - For accessing local files
- `WRITE_EXTERNAL_STORAGE` - For downloading songs
- `ACCESS_NETWORK_STATE` - For checking network status
- `FOREGROUND_SERVICE` - For background music playback

## License

MIT License - Feel free to use this project for your own purposes.

## Support

For issues or feature requests, please open an issue on GitHub.
