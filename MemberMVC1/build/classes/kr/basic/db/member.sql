select * from member;   -- alr + s => 쿼리문 한줄 실행 

drop table member;

create table member(
    num int primary key auto_increment,
    id varchar(20) not null, 
    pass varchar(20) not null,
    name varchar(20) not null,
    age int not null,
    email varchar(30) not null,
    phone varchar(30) not null,    
    unique key(id)
);


desc member;

insert into member(id,pass,name,age,email,phone) values('admin','admin','관리자',30,'test@admin.com','010-1111-1234');
insert into member(id,pass,name,age,email,phone) values('test1','1111','테스트1',31,'test1@test.com','010-1111-1235');
insert into member(id,pass,name,age,email,phone) values('test2','2222','테스트2',32,'test2@test.com','010-1111-1236');
insert into member(id,pass,name,age,email,phone) values('test3','3333','테스트3',33,'test3@test.com','010-1111-1237');
insert into member(id,pass,name,age,email,phone) values('test4','4444','테스트4',34,'test4@test.com','010-1111-1238');
insert into member(id,pass,name,age,email,phone) values('test5','5555','테스트5',35,'test5@test.com','010-1111-1239');
insert into member(id,pass,name,age,email,phone) values('test6','6666','테스트6',36,'test6@test.com','010-1111-1230');
insert into member(id,pass,name,age,email,phone) values('test7','7777','테스트7',37,'test7@test.com','010-1111-1210');


select * from member;

-- test1 1111

select * from member where id='test1' and pass='1111';
-- 번호가 3인 사람의 age 와 email, phone 번호 수정 
update member set age=12,email='test',phone='010-0000-0000' where num=3; 


