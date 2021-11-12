create table roulette_round (
    id bigint not null,
    appointment datetime(6),
    participants varchar(255),
    primary key (id)
) engine=InnoDB;