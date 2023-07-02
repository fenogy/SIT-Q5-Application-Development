create table TB_ET_USER
(
    USER_ID    integer primary key not null,
    FIRST_NAME varchar(20)         not null,
    LAST_NAME  varchar(20)         not null,
    EMAIL      varchar(30)         not null,
    PASSWORD   text                not null
);

create table TB_ET_CATEGORY
(
    CATEGORY_ID   integer primary key not null,
    USER_ID       integer             not null,
    CATEGORY_NAME varchar(20)         not null,
    DESCRIPTION   varchar(50)         not null
);
alter table TB_ET_CATEGORY
    add constraint cat_users_fk
        foreign key (USER_ID) references TB_ET_USER (USER_ID);

create table TB_ET_EXPENSE
(
    EXPENSE_ID    integer primary key not null,
    CATEGORY_ID   integer             not null,
    USER_ID       integer             not null,
    AMOUNT        numeric(10, 2)      not null,
    DESCRIPTION   varchar(50)         not null,
    EXP_TIMESTAMP bigint              not null
);
alter table TB_ET_EXPENSE
    add constraint trans_cat_fk
        foreign key (CATEGORY_ID) references TB_ET_CATEGORY (CATEGORY_ID);
alter table TB_ET_EXPENSE
    add constraint trans_users_fk
        foreign key (USER_ID) references TB_ET_USER (USER_ID);

commit;


select * from TB_ET_USER;
