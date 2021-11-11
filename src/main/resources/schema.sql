create table if not exists roulette_round (
   id bigint not null auto_increment,
   appointment datetime(6),
   participants varchar(255),
   primary key (id)
) engine=InnoDB;