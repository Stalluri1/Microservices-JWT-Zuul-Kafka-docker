create table if not exists roles (id  bigserial not null, name varchar(60), primary key (id));
create table if not exists user_roles (user_id int8 not null, role_id int8 not null, primary key (user_id, role_id));
alter table if exists roles add constraint UK_nb4h0p6txrmfc0xbrd1kglp9t unique (name);
alter table if exists user_roles add constraint FKh8ciramu9cc9q3qcqiv4ue8a6 foreign key (role_id) references roles;
alter table if exists user_roles add constraint FKhfh9dx7w3ubf1co1vdev94g3f foreign key (user_id) references users;