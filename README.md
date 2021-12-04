# Musify: AppDev Hack Challenge 2021

## An app to help you organize your music and listen to your favorite bops wherever you go!

## [Video Demo](https://drive.google.com/file/d/17m707Cc3A6qWFbOdIdOfPQ2daykzy_tL/view?usp=sharing)

## Description

Musify is a personalized Android app designed to help you organize your music
and listen to your favorite tunes at any time. Through the app, you're able to
create your own playlists and add your favorite songs. Additionally, you can
attach Youtube links to your songs so that you can directly play their music
videos in the app!

## Requirements Fulfilled:

### Android

- Implemented 2 RecyclerViews and 2 custom adapters
  - One displays all playlists
  - Another displays songs in a playlist
- Implemented 3 screens
  - Home screen containing user's playlists
  - Playlist screen containing songs in the playlist
  - Player screen that plays the song
  - Used Youtube Data API

### Backend

- Deployed at https://musifyappdev.herokuapp.com
- Designed 3 classes in SQLAlchemy modeling Users, Playlists, and Songs
  - One-to-many relation for Users to Playlists
  - Many-to-many relation for Playlists and Songs
- Implemented 12 routes:
  - 5 GET routes
  - 4 POST routes
  - 3 DELETE routes
- Detailed API specification in backend/api.md
