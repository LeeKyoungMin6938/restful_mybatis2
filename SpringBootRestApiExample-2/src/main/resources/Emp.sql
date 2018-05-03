create table Emp(
	empno int not null Auto_increment primary key,
	ename varchar(50),
	job varchar(30),
	sal int(50),
	hiredate datetime default now()
);

insert into emp(ename,job,sal,hiredate) values('이경민','개발자',300000,'2012-01-01');
insert into emp(ename,job,sal) values('이경민','개발자2',200000);

commit;