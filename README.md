# Musify: Personal Music Playlist Organizer
*AppDev Hack Challenge 2021*

## Overview
Musify is an Android application designed to help users efficiently organize and manage their music playlists. It allows users to create custom playlists, add their favorite songs, and directly play music videos from YouTube. The app offers an intuitive interface for a seamless music management experience.

### Key Features:
- Create Playlists: Users can create and manage their own playlists with personalized song selections.
- Integrate with YouTube: Users can link YouTube videos to their songs, enabling direct playback within the app.
- Customizable User Interface: Two main views for managing playlists and songs with efficient navigation.

## Technical Details
### Frontend (Android)
- Developed using Kotlin with Android Studio.
- Utilized RecyclerViews and custom adapters for displaying:
  - A list of user playlists.
  - A list of songs within each playlist.
- Implemented three main screens:
  - Home screen displaying the user’s playlists.
  - Playlist screen displaying songs within a selected playlist.
  - Music player screen for video playback using embedded YouTube frame.
- Integrated with the YouTube Data API for fetching video content.

### Backend (API)
- Built using SQLAlchemy for database models:
  - Users: Models user information and playlists.
  - Playlists: Models playlist details and associated songs.
  - Songs: Represents individual songs in playlists.
- Implemented RESTful API with 12 routes:
  - 5 GET routes: To fetch data (e.g., list of playlists, songs in a playlist).
  - 4 POST routes: For creating new data (e.g., add a new playlist, add a song).
  - 3 DELETE routes: To remove data (e.g., delete a playlist, remove a song).
- Full API specification available in the backend API documentation.

## Future Improvement
- Expanded Music Discovery: Deeper integration with Youtube API to allow users to search videos within the app for an even wider range of content.
- Offline Playlists: Allow users to download playlists for offline listening.
