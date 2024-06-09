INSERT INTO public.artist (name, bio, image_url) VALUES
                                                     ('The Beatles', 'Легендарная британская рок-группа', 'http://example.com/beatles.jpg'),
                                                     ('Led Zeppelin', 'Иконическая рок-группа 1970-х', 'http://example.com/ledzep.jpg'),
                                                     ('Taylor Swift', 'Американская поп-звезда', 'http://example.com/taylor.jpg');

INSERT INTO public.album (artist_id, name, release_date, type_id, cover_url, genre_id, description) VALUES
(1, 'Abbey Road', '1969-09-26', 1, 'http://example.com/abbeyroad.jpg', 1, 'Один из лучших альбомов Beatles'),
(2, 'IV', '1971-11-08', 1, 'http://example.com/ledzeppeliniv.jpg', 2, 'Содержит известные хиты, включая Stairway to Heaven'),
(3, '1989', '2014-10-27', 1, 'http://example.com/1989.jpg', 3, 'Популярный поп-альбом от Taylor Swift');

INSERT INTO  public.album_type (name) VALUES
('Album')

INSERT INTO public.genre (name) VALUES 
('Rock'),
('Classic Rock'),
('Pop');

INSERT INTO public.playlist (user_login, name, cover_url) VALUES 
('user1', 'My Favorite Rock', 'http://example.com/playlist1.jpg'),
('user2', 'Morning Classics', 'http://example.com/playlist2.jpg'),
('user3', 'Pop Hits', 'http://example.com/playlist3.jpg');

INSERT INTO public.track (album_id, name, duration, position) VALUES 
(1, 'Come Together', 259, 1),
(1, 'Something', 183, 2),
(2, 'Stairway to Heaven', 482, 4),
(3, 'Shake It Off', 219, 1);

INSERT INTO public.playlist_track (playlist_playlist_id, track_track_id) VALUES 
(1, 1),
(1, 3),
(2, 2),
(3, 4);