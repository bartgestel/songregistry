ALTER TABLE song_artist
DROP
FOREIGN KEY FKl07frc6vlce55iyo47pctrrjl;

CREATE TABLE album_artists
(
    id        INT AUTO_INCREMENT NOT NULL,
    album_id  INT NOT NULL,
    artist_id INT NOT NULL,
    CONSTRAINT pk_album_artists PRIMARY KEY (id)
);

CREATE TABLE reviews
(
    id      INT AUTO_INCREMENT NOT NULL,
    text    VARCHAR(255) NOT NULL,
    rating DOUBLE NOT NULL,
    song_id INT          NOT NULL,
    user_id INT NULL,
    CONSTRAINT pk_reviews PRIMARY KEY (id)
);

CREATE TABLE user_roles
(
    id   INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_user_roles PRIMARY KEY (id)
);

CREATE TABLE users
(
    id       INT AUTO_INCREMENT NOT NULL,
    email    VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    hash     VARCHAR(255) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE album_artists
    ADD CONSTRAINT FK_ALBUM_ARTISTS_ON_ALBUM FOREIGN KEY (album_id) REFERENCES albums (id);

ALTER TABLE album_artists
    ADD CONSTRAINT FK_ALBUM_ARTISTS_ON_ARTIST FOREIGN KEY (artist_id) REFERENCES artists (id);

ALTER TABLE reviews
    ADD CONSTRAINT FK_REVIEWS_ON_SONG FOREIGN KEY (song_id) REFERENCES songs (id);

ALTER TABLE reviews
    ADD CONSTRAINT FK_REVIEWS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE song_artist
DROP
COLUMN role_id;

ALTER TABLE songs
    MODIFY album_id INT NULL;

ALTER TABLE song_artist
    MODIFY artist_role INT NULL;

ALTER TABLE songs
    MODIFY song_genre INT NULL;