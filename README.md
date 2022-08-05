# boardPractice

CREATE TABLE board(

no NUMBER not null ,

title VARCHAR2(300) NOT NULL,

content VARCHAR2(2000) NOT NULL,

writer VARCHAR2(30) NOT NULL,

writeDate DATE DEFAULT SYSDATE, -- DEFAULT 값이 셋팅되어 있으면 NOT NULL을 사용하지 않는다.(의미없음)

hit NUMBER DEFAULT 0,

primary key(no)

);


create sequence board_seq
start with 1 increment by 1
maxvalue 9999 nocycle;

  CREATE TABLE boardReply (
   rno       number(10)          not null ,
   bno       number(10)          not null,
   writer       varchar(30)      not null,
   content    varchar(1000)      not null,
   write_Date    date   default sysdate not null,
   PRIMARY KEY(rno, bno),
   FOREIGN KEY(bno) REFERENCES board(no)
);

create sequence Rep_seq
increment by 1
start with 1
maxvalue 100000
minvalue 1
nocycle;
