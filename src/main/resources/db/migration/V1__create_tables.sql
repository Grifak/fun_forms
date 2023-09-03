create table song
(
    id             bigserial primary key,
    title          varchar(100) not null,
    original_url   varchar(70)  not null,
    chunks         int          not null,
    chunk_duration int          not null
);
create table song_part_reversed
(
    id      bigserial primary key,
    song_id bigint references song (id),
    index   int          not null,
    url     varchar(255) not null
);
