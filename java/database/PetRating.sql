create table ratings(
raterId int,
ratedPetId int,
notes varchar(250),
rating int,
constraint PKrateandpetid primary key(raterId, ratedPetId),
constraint FKuserID foreign key (raterId) references users(user_id),
constraint FKpetid foreign key (ratedPetId) references pets(petid)
);
