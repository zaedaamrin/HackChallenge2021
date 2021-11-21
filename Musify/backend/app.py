from db import db
import json
from flask import Flask, request
from db import User
from db import Playlist
from db import Song

#########################
# INITIALIZING DATABASE #
#########################

app = Flask(__name__)
db_filename = "cms.db"

app.config["SQLALCHEMY_DATABASE_URI"] = "sqlite:///%s" % db_filename
app.config["SQLALCHEMY_TRACK_MODIFICATIONS"] = False
app.config["SQLALCHEMY_ECHO"] = True

db.init_app(app)
with app.app_context():
    db.create_all()

##########
# ROUTES #
##########

API = "/api/"

USER = API + "users/"
SPECIFIC_USER = USER + "<int:uid>/"
ADD_PLAYLIST_TO_USER = SPECIFIC_USER + "add/"

PLAYLIST = SPECIFIC_USER + "playlists/"
SPECIFIC_PLAYLIST = PLAYLIST + "<int:pid>/"
ADD_SONG_TO_PLAYLIST = SPECIFIC_PLAYLIST + "add/"

SONG = API + "songs/"
SPECIFIC_SONG = SONG + "<int:sid>/"


####################
# HELPER FUNCTIONS #
####################

def success_response(data, code=200):
    return json.dumps(data), code

def failure_response(message, code=404):
    return json.dumps({"error": message}), code

def invalid_query(name, id):
    msg = f"{name} {id} Not Found"
    return failure_response(msg)

def missing_field(name):
    msg = f"Invalid {name} Field(s)"
    return failure_response(msg, 400)

def _getuser(uid):
    user = User.query.filter_by(id = uid).first()
    return user

def _getlist(user, pid):
    return next((x for x in user.playlists if x.id == pid), None)

### Need databases for users, songs, playlists##
# User -> Username
# Song -> Name, Artist, Youtube link, Image?
# Playlist -> Name, Songs, Image?

##################
# ROUTE HANDLING #
##################

@app.route(USER)
def get_users():
    result = {"users": [user.serialize() for user in User.query.all()]}
    return success_response(result)

@app.route(USER, methods = ["POST"])
def create_user():
    body = json.loads(request.data)
    name = body.get("name")
    username = body.get("username")
    if not name or not username:
        return missing_field("User")
    user = User(**body)
    db.session.add(user)
    db.session.commit()
    # print(SPECIFIC_USER)
    return success_response(user.serialize(), code = 201)

@app.route(SPECIFIC_USER, methods = ["DELETE"])
def delete_user(uid):
    user = _getuser(uid)
    if user is None:
        return invalid_query("User", uid)
    db.session.delete(user)
    db.session.commit()
    return success_response(user.serialize())

@app.route(SPECIFIC_USER)
def get_specific_user(uid):
    user = _getuser(uid)
    if user is None:
        return invalid_query("User", uid)
    return success_response(user.serialize())

@app.route(PLAYLIST)
def get_playlists(uid):
    user = _getuser(uid)
    if user is None:
        return invalid_query("User", uid)
    result = {"playlists": [playlist.serialize() for playlist in user.playlists]}
    return success_response(result)

@app.route(SPECIFIC_PLAYLIST)
def get_specific_playlist(uid, pid):
    user = _getuser(uid)
    if user is None:
        return invalid_query("User", uid)
    playlist = _getlist(user, pid)
    if playlist is None:
        return invalid_query("Playlist", pid)
    return success_response(playlist.serialize())

@app.route(PLAYLIST, methods = [ "POST" ])
def create_playlist(uid):
    user = _getuser(uid)
    if user is None:
        return invalid_query("User", uid)
    body = json.loads(request.data)
    name = body.get("name")
    if name is None:
        return missing_field("Playlist")
    playlist = Playlist(**body, uid = uid)
    db.session.add(playlist)
    db.session.commit()
    return success_response(playlist.serialize(), code = 201)


# @app.route(SPECIFIC_PLAYLIST, methods = [ "POST "])
# def add_song_to_playlist(uid, pid):
#     user = _getuser(uid)
#     if user is None:
#         return invalid_query("User", uid)
#     playlist = _getlist(pid)
#     if playlist is None:
#         return invalid_query("Playlist", pid)
#     body = json.loads(request.data)


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug=True)
