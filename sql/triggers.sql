CREATE OR REPLACE FUNCTION on_feedback_add_func() 
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
begin 
	IF NOT EXISTS(
		SELECT 1 FROM request r2 
		WHERE r2.client_uuid = NEW.user_uuid
		AND EXISTS(
			SELECT 1 FROM good_request gr
			WHERE gr.good_uuid = NEW.good_uuid))
	THEN 
		RAISE EXCEPTION 'A person should buy a good before giving a feedback';
	ELSE
		return new;
	END IF;
	
end
$$;
CREATE  TRIGGER on_feedback_add BEFORE INSERT ON public.feedback 
FOR EACH ROW EXECUTE PROCEDURE on_feedback_add_func(); 



CREATE OR REPLACE FUNCTION after_stat_histor_add_proc() 
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
declare 
	status_f status%ROWTYPE; 
begin 
	SELECT * into status_f FROM status s WHERE s.uuid = NEW.status_uuid;
	if status_f is not null 
	and status_f.prev_status IS NOT NULL
	AND NOT EXISTS (SELECT sh.uuid FROM status_history sh 
					WHERE sh.request_uuid = NEW.request_uuid AND sh.status_uuid = status_f.prev_status)
	THEN
		INSERT INTO public.status_history
		(uuid, request_uuid, status_uuid, setup_timestamp, "comment")
		VALUES(uuid_generate_v4(), NEW.request_uuid, status_f.prev_status, NEW.setup_timestamp, NEW."comment");
	END IF;
	return NULL;
end
$$;
CREATE TRIGGER after_stat_histor_add AFTER INSERT ON status_history 
FOR EACH row 
WHEN (pg_trigger_depth() = 0)
EXECUTE PROCEDURE after_stat_histor_add_proc(); 


INSERT INTO public.status_history
(uuid, request_uuid, status_uuid, setup_timestamp, "comment")
VALUES(uuid_generate_v4(), 'b4526f73-2297-4e3d-8c82-39fd42c5ea8e', 'd579ac13-9e40-40d6-9b25-f36fba781671', CURRENT_TIMESTAMP, 'ff');





drop view user_and_info;

create view user_and_info as
	select u2.uuid, u2.birth_date, u2.email, u2."name", u2.pass , u2.phone, u2."role", 
		   count(r.uuid) as reqests,
		   count(m.uuid)  as messeges,
		   count(f.uuid)  as feedbacks 
	from public."user" u2
	left  join request r  on r.client_uuid = u2.uuid
	left  join message m on m.user_uuid = u2.uuid
	left  join feedback f on f.user_uuid = u2.uuid
	group by u2.uuid;
	
select * from user_and_info;






	AND NOT EXISTS (SELECT * FROM status_history sh WHERE sh.request_uuid = NEW.request_uuid AND sh.status_uuid =
	(SELECT prev_status FROM status s WHERE s.uuid = NEW.status_uuid))
	
	IF ((SELECT prev_status FROM status s WHERE s.uuid = NEW.status_uuid) IS NOT NULL) 
	THEN




CREATE OR REPLACE FUNCTION instead_of_user_data() 
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
declare 
	user_id uuid; 
begin 
	if tg_op = 'INSERT' then 
		user_id := uuid_generate_v4(); 
	
		INSERT INTO public."user"
		(uuid, email, pass, "role", "name", birth_date, phone)
		VALUES(user_id, new.email, new.pass, new."name", new."role", new.birth_date, new.phone);
	
		call update_chats(user_id);
		return new;
	elseif tg_op = 'UPDATE'then
		update public."user" set
			email = new.email,
			pass = new.pass,
			"name" = new."name",
			"role" = new."role" ,
			birth_date  = new.birth_date,
			phone = new.phone
		where uuid = new.uuid;
		return new;
	elseif tg_op = 'DELETE' then
		delete from public."user" u where u.uuid = old.uuid;
		return new;
	end if;
end
$$;
create trigger instead_trigger instead of insert or update or delete on user_and_info
for each row execute procedure instead_of_user_data();


CREATE OR REPLACE FUNCTION instead_of_user_data() 
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
declare 
	user_id uuid; 
begin 
	if tg_op = 'INSERT' then 
		user_id := uuid_generate_v4(); 
	
		INSERT INTO public."user"
		(uuid, email, pass, "role", "name", birth_date, phone)
		VALUES(user_id, new.email, new.pass,new."role", new."name",  new.birth_date, new.phone);
	
		call update_chats(user_id);
		return new;
	elseif tg_op = 'UPDATE'then
		update public."user" set
			email = new.email,
			pass = new.pass,
			"name" = new."name",
			"role" = new."role" ,
			birth_date  = new.birth_date,
			phone = new.phone
		where uuid = new.uuid;
		return new;
	elseif tg_op = 'DELETE' then
		delete from public."user" u where u.uuid = old.uuid;
		return new;
	end if;
end
$$;
create trigger instead_trigger instead of insert or update or delete on user_and_info
for each row execute procedure instead_of_user_data();


CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
INSERT INTO public.user_and_info
(uuid, birth_date, email, "name", pass, phone, "role", reqests, messeges, feedbacks)
VALUES(null, '1999-02-12', 'testrecord@mail.ru', 'ТестовоеИмя', MD5('12345'), '11223344', 1 , 0, 0, 0);

UPDATE public.user_and_info
SET  name ='Дмитрий'
where uuid='8233a260-2523-4d28-a299-db4aa787a24b';

DELETE FROM public.user_and_info WHERE uuid='8233a260-2523-4d28-a299-db4aa787a24b';

