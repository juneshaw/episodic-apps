create table viewings (
  id bigint not null auto_increment primary key,
  user_id bigint,
  show_id bigint,
  episode_id bigint,
  updated_at DateTime,
  timecode int
);