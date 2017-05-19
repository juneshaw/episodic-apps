create table episodes (
  id bigint not null auto_increment primary key,
  show_id bigint,
  season_number int,
  episode_number int
);