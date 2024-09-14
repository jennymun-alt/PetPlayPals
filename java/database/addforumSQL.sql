create table topics(
topicid serial,
title varchar(50) not null, 
message varchar(1000) not null, 
userid int,
username varchar(50) not null,
constraint pktopicid primary key(topicid),
constraint fkuseridtotopic foreign key (userid) references users(user_id)
);

create table post(
postid serial,
topicid int,
userid int,
message varchar(1000) not null,
username varchar(50) not null ,
constraint pkpostid primary key(postid),
constraint fkuseridtopost foreign key(userid) references users(user_id),
constraint fktopicid foreign key(topicid) references topics(topicid)
)
