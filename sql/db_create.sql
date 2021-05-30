CREATE TABLE public."user" (
	uuid uuid NOT NULL,
	email varchar(128) NOT NULL,
	pass varchar(64) NOT NULL,
	"role" smallint NOT NULL,
	"name" varchar(64) NULL,
	birth_date date NULL,
	phone varchar(11) NULL,
	CONSTRAINT user_pk PRIMARY KEY (uuid)
);

CREATE TABLE public.chat (
	uuid uuid NOT NULL,
	client_uuid uuid NOT NULL,
	manager_uuid uuid NOT NULL,
	CONSTRAINT chat_pk PRIMARY KEY (uuid),
	CONSTRAINT chat_fk_client FOREIGN KEY (client_uuid) REFERENCES public."user"(uuid),
	CONSTRAINT chat_fk_manager FOREIGN KEY (manager_uuid) REFERENCES public."user"(uuid)
);

CREATE TABLE public.message (
	uuid uuid NOT NULL,
	chat_uuid uuid NOT NULL,
	user_uuid uuid NOT NULL,
	"text" text NOT NULL,
	"timestamp" timestamp(0) NOT NULL,
	CONSTRAINT message_pk PRIMARY KEY (uuid),
	CONSTRAINT message_fk_chat FOREIGN KEY (chat_uuid) REFERENCES public.chat(uuid),
	CONSTRAINT message_fk_user FOREIGN KEY (user_uuid) REFERENCES public."user"(uuid)
);

CREATE TABLE public.status (
	uuid uuid NOT NULL,
	"name" varchar(64) NOT NULL,
	"is_terminal" smallint NOT NULL default 0,
	CONSTRAINT status_pk PRIMARY KEY (uuid)
);

CREATE TABLE public.request (
	uuid uuid NOT NULL,
	client_uuid uuid NOT NULL,
	manager_uuid uuid NOT NULL,
	payment_data text NOT NULL,
	CONSTRAINT request_pk PRIMARY KEY (uuid),
	CONSTRAINT request_fk_client FOREIGN KEY (client_uuid) REFERENCES public."user"(uuid),
	CONSTRAINT request_fk_manager FOREIGN KEY (manager_uuid) REFERENCES public."user"(uuid)
);

CREATE TABLE public.status_history (
	uuid uuid NOT NULL,
	request_uuid uuid NOT NULL,
	status_uuid uuid NOT NULL,
	setup_timestamp timestamp(0) NOT NULL,
	"comment" text NULL,
	CONSTRAINT status_history_pk PRIMARY KEY (uuid),
	CONSTRAINT status_history_fk_request FOREIGN KEY (request_uuid) REFERENCES public.request(uuid),
	CONSTRAINT status_history_fk_status FOREIGN KEY (status_uuid) REFERENCES public.status(uuid)
);

CREATE TABLE public."catalog" (
	uuid uuid NOT NULL,
	"name" varchar(128) NOT NULL,
	CONSTRAINT catalog_pk PRIMARY KEY (uuid)
);

CREATE TABLE public.good (
	uuid uuid NOT NULL,
	"name" varchar(128) NOT NULL,
	price numeric(2) NOT NULL,
	descr text NOT NULL,
	img_path varchar(512) NULL,
	CONSTRAINT good_pk PRIMARY KEY (uuid)
);

CREATE TABLE public.good (
	uuid uuid NOT NULL,
	name varchar(256) NOT NULL,
	price numeric(16,2) NOT NULL,
	descr text NOT NULL,
	img_path varchar(512) NULL,
	CONSTRAINT good_pk PRIMARY KEY (uuid)
);
ALTER TABLE public.good ADD catalog_uuid uuid NULL;
ALTER TABLE public.good ADD CONSTRAINT good_fk_catalog FOREIGN KEY (catalog_uuid) REFERENCES public."catalog"(uuid);


CREATE TABLE public.feedback (
	uuid uuid NOT NULL,
	user_uuid uuid NOT NULL,
	good_uuid uuid NOT NULL,
	grade smallint NOT NULL,
	feedback text NULL,
	"timestamp" timestamp(0) NOT NULL,
	CONSTRAINT feedback_pk PRIMARY KEY (uuid),
	CONSTRAINT feedback_fk_user FOREIGN KEY (user_uuid) REFERENCES public."user"(uuid),
	CONSTRAINT feedback_fk_good FOREIGN KEY (good_uuid) REFERENCES public.good(uuid)
);

CREATE TABLE public.good_request (
	uuid uuid NOT NULL,
	request_uuid uuid NOT NULL,
	good_uuid uuid NOT NULL,
	CONSTRAINT good_request_pk PRIMARY KEY (uuid),
	CONSTRAINT good_request_fk_request FOREIGN KEY (request_uuid) REFERENCES public.request(uuid),
	CONSTRAINT good_request_fk_good FOREIGN KEY (good_uuid) REFERENCES public.good(uuid)
);
CREATE TABLE public.parametr (
	uuid uuid NOT NULL,
	good_uuid uuid NOT NULL,
	question varchar(512) NOT NULL,
	"name" varchar(128) NOT NULL,
	CONSTRAINT parametr_pk PRIMARY KEY (uuid),
	CONSTRAINT parametr_fk_good FOREIGN KEY (good_uuid) REFERENCES public.good(uuid)
);

CREATE TABLE public.parametr_value (
	uuid uuid NOT NULL,
	parametr_uuid uuid NOT NULL,
	good_request_uuid uuid NOT NULL,
	value varchar(256) NOT NULL,
	CONSTRAINT parametr_value_pk PRIMARY KEY (uuid),
	CONSTRAINT parametr_value_fk_parametr FOREIGN KEY (parametr_uuid) REFERENCES public.parametr(uuid),
	CONSTRAINT parametr_value_fk_good_req FOREIGN KEY (good_request_uuid) REFERENCES public.good_request(uuid)
);
