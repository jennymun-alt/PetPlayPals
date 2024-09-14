

create table pets(
petid serial,
petname varchar(50) not null, 
breed varchar(50) not null, 
personality varchar(50) not null, 
user_id int,
constraint petid primary key (petid),
constraint fkownerid foreign key (user_id) references users(user_id)

);

create table playdate(
city varchar(50) not null,
playdate_id serial,
address varchar(100) not null,
dateOfDate date,
constraint pkplaydateid primary key(playdate_id)

);


create table petsplaying(
petid int,
playdateid int,
constraint pkpetsplaying primary key(petid, playdateid),
constraint fktopet foreign key (petid) references pets(petid),
constraint fktodate foreign key (playdateid) references playdate(playdate_id)
);



commit transaction;