create table if not exists client(
    id int primary key generated by default as identity,
    name varchar(50) not null,
    age int not null,
    description varchar
);