CREATE TABLE onboarding.address (
	id BIGSERIAL NOT NULL,
	user_id BIGSERIAL not null,
	zipcode varchar(8) not null,
	uf varchar(2) not null,
	city varchar(20) not null,
	district varchar(20) not null,
	street_name varchar(40) not null,
	street_number varchar(4),
	complement varchar(20),
	constraint pk_address primary key (id),
	constraint fk_address_user foreign key (user_id) references onboarding.user(id)
);