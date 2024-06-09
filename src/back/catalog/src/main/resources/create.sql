-- В H2 ENUM типы должны быть определены прямо в определении таблицы
CREATE SCHEMA IF NOT EXISTS public;

CREATE TABLE IF NOT EXISTS public.album
(
    album_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    artist_id BIGINT NOT NULL,
    type_id BIGINT NOT NULL,
    name VARCHAR(128) NOT NULL,
    release_date DATE NOT NULL,
    cover_url VARCHAR(256) NOT NULL,
    genre_id BIGINT NOT NULL,
    description VARCHAR(256),
    FOREIGN KEY (artist_id) REFERENCES public.artist (artist_id),
    FOREIGN KEY (genre_id) REFERENCES public.genre (genre_id),
    FOREIGN KEY  (type_id) REFERENCES public.album_type (type_id)
);

CREATE TABLE IF NOT EXISTS public.artist
(
    artist_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    bio VARCHAR(256),
    image_url VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS public.genre
(
    genre_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS public.playlist
(
    playlist_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_login VARCHAR(128) NOT NULL,
    name VARCHAR(128) NOT NULL,
    cover_url VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS public.playlist_track
(
    playlist_playlist_id BIGINT NOT NULL,
    track_track_id BIGINT NOT NULL,
    CONSTRAINT pk_playlist_track PRIMARY KEY (playlist_playlist_id, track_track_id),
    FOREIGN KEY (playlist_playlist_id) REFERENCES public.playlist (playlist_id),
    FOREIGN KEY (track_track_id) REFERENCES public.track (track_id)
);

CREATE TABLE IF NOT EXISTS public.track
(
    track_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    album_id INTEGER NOT NULL,
    name VARCHAR(128) NOT NULL,
    duration INTEGER NOT NULL,
    position INTEGER NOT NULL,
    FOREIGN KEY (album_id) REFERENCES public.album (album_id)
);

CREATE TABLE IF NOT EXISTS public.album_type
(
    type_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(64) NOT NULL
);

COMMIT;