# Musify API Specification

## Get All Users

```
GET /api/users/
```

```json
Response: <HTTP STATUS CODE 200>
{
  "users": [
    {
      "id": <USER ID>,
      "name": <USER NAME>,
      "username": <USERNAME>,
      "playlists": <SERIALIZED LIST OF PLAYLISTS>
    }
  ]
}
```

## Create User

```sql
POST /api/users/
```

```json
Request
{
  "name": <USER'S NAME>,
  "username": <USERNAME>
}
```

```json
Response: <HTTP STATUS CODE 201>
{
  "id": <USER ID>,
  "name": <USER'S NAME>,
  "username": <USERNAME>,
  "playlists": []
}
```

## Get Specific User

```sql
GET /api/users/{id}/
```

```json
Response: <HTTP STATUS CODE 200>
{
  "id": <USER ID>,
  "name": <USER'S NAME>,
  "username": <USERNAME>,
  "playlists": [
    <SERIALIZED PLAYLIST>,
    <SERIALIZED PLAYLIST>,
    ...
  ]
}
```

## Delete User

```sql
DELETE /api/users/{id}/
```

```json
Response: <HTTP STATUS CODE 200>
{
  "id": <USER ID>,
  "name": <USER'S NAME>,
  "username": <USERNAME>,
  "playlists": [
    <SERIALIZED PLAYLIST>,
    <SERIALIZED PLAYLIST>,
    ...
  ]
}
```

## Get User's Playlists

```sql
GET /api/users/{id}/playlists
```

```json
Response: <HTTP STATUS CODE 200>
{
  "playlists": [
    <SERIALIZED PLAYLIST>,
    <SERIALIZED PLAYLIST>,
    ...
  ]
}
```

## Create Playlist

```sql
POST /api/users/{id}/playlists
```

```json
Request
{
    "name": <PLAYLIST NAME>,
    "image": <PLAYLIST IMAGE (URL)>
}
```

```json
Response: <HTTP STATUS CODE 201>
{
    "id": <PLAYLIST ID>,
    "name": <PLAYLIST NAME>,
    "image": <PLAYLIST IMAGE (URL)>,
    "songs": []
}
```

## Get User's Specific Playlist

```sql
GET /api/users/{id}/playlists/{pid}/
```

```json
Response: <HTTP STATUS CODE 200>
{
    "id": <PLAYLIST ID>,
    "name": <PLAYLIST NAME>,
    "image": <PLAYLIST IMAGE (URL)>,
    "songs": [
      <SERIALIZED SONG>,
      <SERIALIZED SONG>,
      ...
    ]
}
```

## Add Song to Playlist

```sql
POST /api/users/{id}/playlists/{pid}/
```

```json
Request
{
    "name": <SONG NAME>,
    "artist": <ARTIST NAME>,
    "youtube": <YOUTUBE LINK>,
    "image": <SONG IMAGE (URL)>
}
```

```json
Response: <HTTP STATUS CODE 201>
{
    "id": <PLAYLIST ID>,
    "name": <PLAYLIST NAME>,
    "image": <PLAYLIST IMAGE (URL)>,
    "songs": [
      <SERIALIZED SONG>,
      <SERIALIZED SONG>,
      ...
      {
        "id": <SONG ID>,
        "name": <SONG NAME>,
        "artist": <SONG ARTIST>,
        "youtube": <YOUTUBE LINK>,
        "image": <SONG IMAGE (URL)>
      }
    ]
}
```

## Get Song from Playlist

```sql
GET /api/users/{id}/playlists/{pid}/{sid}
```

```json
Response: <HTTP STATUS CODE 200>
{
  "id": <SONG ID>,
  "name": <SONG NAME>,
  "artist": <SONG ARTIST>,
  "youtube": <YOUTUBE LINK>,
  "image": <SONG IMAGE (URL)>,
  "playlists": [
    <SERIALIZED PLAYLIST>,
    <SERIALIZED PLAYLIST>,
    ...
  ]
}
```

## Delete Song from Playlist

```sql
DELETE /api/users/{id}/playlists/{pid}/{sid}
```

```json
Response: <HTTP STATUS CODE 200>
{
  "success": true,
  "data": {
    "id": <SONG ID>,
    "name": <SONG NAME>,
    "artist": <SONG ARTIST>,
    "youtube": <YOUTUBE LINK>,
    "image": <SONG IMAGE (URL)>,
    "playlists": [
      <SERIALIZED PLAYLIST>,
      <SERIALIZED PLAYLIST>,
      ...
    ]
  }
}
```
