package com.mymusic.catalog.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
@RequiredArgsConstructor
@Profile("development")
public class DataLoader implements CommandLineRunner {

    private final DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();

            // Загрузка данных в таблицу artist
            statement.executeUpdate("INSERT INTO artist (name, bio, image_url) VALUES " +
                    "('The Beatles', 'Легендарная британская рок-группа', 'http://example.com/beatles.jpg')," +
                    "('Led Zeppelin', 'Иконическая рок-группа 1970-х', 'http://example.com/ledzep.jpg')," +
                    "('Taylor Swift', 'Американская поп-звезда', 'http://example.com/taylor.jpg')," +
                    "('Queen', 'Британская рок-группа', 'http://example.com/queen.jpg')," +
                    "('Michael Jackson', 'Король поп-музыки', 'http://example.com/mj.jpg'), " +
                    "('Alcest', 'Alcest — французская пост-блэк-метал-группа из города Баньоль-сюр-Сез. Её лидером и основателем является Neige (Стефан По). Группа была образована в 1999 году как сольный блэк-метал-проект Нежа, вскоре превратившийся в трио.'," +
                    " 'https://s3.timeweb.cloud/de216006-diploma-storage/artist-img/132.jpg');");

            // Загрузка данных в таблицу album_type
            statement.executeUpdate("INSERT INTO album_type (name) VALUES ('Album'), ('EP');");

            // Загрузка данных в таблицу genre
            statement.executeUpdate("INSERT INTO genre (name) VALUES " +
                    "('Rock')," +
                    "('Classic Rock')," +
                    "('Pop')," +
                    "('Disco')," +
                    "('Alternative')," +
                    "('Post-Black Metal')," +
                    "('Shoegaze');");

            // Загрузка данных в таблицу album
            statement.executeUpdate("INSERT INTO album (artist_id, genre_id, type_id, cover, description, name, release_date) VALUES " +
                    "(1, 1, 1, 'http://example.com/abbeyroad.jpg', 'Один из лучших альбомов Beatles', 'Abbey Road', '1969')," +
                    "(2, 2, 1, 'http://example.com/ledzeppeliniv.jpg', 'Содержит известные хиты, включая Stairway to Heaven', 'IV', '1971')," +
                    "(3, 3, 1, 'http://example.com/1989.jpg', 'Популярный поп-альбом от Taylor Swift', '1989', '2014')," +
                    "(4, 2, 1, 'http://example.com/nightattheopera.jpg', 'Содержит известные хиты, включая Bohemian Rhapsody', 'A Night at the Opera', '1975')," +
                    "(5, 4, 1, 'http://example.com/thriller.jpg', 'Самый продаваемый альбом всех времен', 'Thriller', '1982'), " +
                    "(6, 6, 1, 'https://s3.timeweb.cloud/de216006-diploma-storage/covers/Alcest-Souvenirs_dun_autre_monde.jpg', 'Дебютный полноформатный альбом французской пост-блэк-метал-группы Alcest, выпущенный в августе 2007 года. Название альбома отсылает к смыслу музыки Alcest, поскольку лидер группы видит в ней путешествие по своим воспоминаниям о далёком мире', 'Souvenirs dun autre monde', '2007')," +
                    "(6, 6, 1, 'https://s3.timeweb.cloud/de216006-diploma-storage/covers/Alcest-Ecailles_de_Lune.jpg', 'Второй студийный альбом Alcest', 'Écailles de Lune', '2010')," +
                    "(6, 6, 1, 'https://s3.timeweb.cloud/de216006-diploma-storage/covers/Alcest-Les_Voyages_de_lAme.jpeg', 'Третий студийный альбом Alcest', 'Les Voyages de lÂme', '2012')," +
                    "(6, 7, 1, 'https://s3.timeweb.cloud/de216006-diploma-storage/covers/Alcest-Shelter.jpg', 'Четвертый студийный альбом Alcest', 'Shelter', '2014')," +
                    "(6, 6, 1, 'https://s3.timeweb.cloud/de216006-diploma-storage/covers/Alcest-Kodama.jpg', 'Пятый студийный альбом Alcest', 'Kodama', '2016');");

            // Загрузка данных в таблицу playlist
            statement.executeUpdate("INSERT INTO playlist (user_id, name, cover_url, description) VALUES " +
                    "(1, 'My Favorite Rock', 'http://example.com/playlist1.jpg', 'Описание плейлиста 1')," +
                    "(2, 'Morning Classics', 'http://example.com/playlist2.jpg', 'Описание плейлиста 2')," +
                    "(3, 'Pop Hits', 'http://example.com/playlist3.jpg', 'Описание плейлиста 3')," +
                    "(4, 'Disco Fever', 'https://s3.timeweb.cloud/de216006-diploma-storage/playlists/4.jpg', 'Описание плейлиста 4')," +
                    "(4, 'Alternative Vibes', null, 'Описание плейлиста 5');");

            // Загрузка данных в таблицу track
            statement.executeUpdate("INSERT INTO track (album_id, name, duration, position) VALUES " +
                    "(1, 'Come Together', 259, 1)," +
                    "(1, 'Something', 183, 2)," +
                    "(2, 'Stairway to Heaven', 482, 4)," +
                    "(3, 'Shake It Off', 219, 1)," +
                    "(4, 'Bohemian Rhapsody', 354, 1)," +
                    "(5, 'Thriller', 358, 1)," +
                    "(6, 'Printemps émeraude', 440, 1)," +
                    "(6, 'Souvenirs dun autre monde', 368, 2)," +
                    "(6, 'Les Iris', 460, 3)," +
                    "(6, 'Ciel errant', 432, 4)," +
                    "(6, 'Sur lautre rive je tattendrai', 410, 5)," +
                    "(6, 'Tir Nan Og', 375, 6);");

            // Загрузка данных в таблицу playlist_track
            statement.executeUpdate("INSERT INTO playlist_track (playlist_playlist_id, track_track_id) VALUES " +
                    "(1, 1)," +
                    "(1, 3)," +
                    "(2, 2)," +
                    "(3, 4)," +
                    "(4, 6)," +
                    "(5, 5);");
        }
    }
}
