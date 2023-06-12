CREATE TABLE IF NOT EXISTS Items (
        id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
        name varchar NOT NULL,
        description varchar NOT NULL,
        available boolean NOT NULL,
        owner_id BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS Users (
        id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
        name varchar NOT NULL,
        email varchar NOT NULL,
        CONSTRAINT UQ_USER_EMAIL UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS Booking (
        id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
        start_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
        end_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
        item_id BIGINT NOT NULL,
        booker_id BIGINT NOT NULL,
        status varchar(18) NOT NULL,
        CONSTRAINT START_IS_BEFORE_END CHECK(start_time<=end_time)
);

CREATE TABLE IF NOT EXISTS Comments(
        id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
        text varchar NOT NULL,
        author_name varchar NOT NULL,
        item_id BIGINT NOT NULL,
        created TIMESTAMP WITHOUT TIME ZONE NOT NULL
)