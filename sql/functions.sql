CREATE OR REPLACE FUNCTION getfreemanager(mng_role_code integer, rqst_finish_stat uuid)
RETURNS uuid
LANGUAGE sql
AS $$

select mangr_uuid from(

select u.uuid as mangr_uuid,
(select count(*) from request r where not exists (
		select 1 from status_history sh where 
			sh.request_uuid = r.uuid 
			and sh.status_uuid = rqst_finish_stat
		)
		and r.manager_uuid = u.uuid
) as not_finished_requests 
from "user" u where u."role" = mng_role_code

) as analyzing order by not_finished_requests limit 1; 

$$;

select * from getfreemanager(2, 'ad631ed1-a650-4f9c-bfa1-70b84c6f0d10') 



CREATE OR REPLACE FUNCTION public.init_request(user_id uuid, paymentdata text)
 RETURNS uuid
 LANGUAGE plpgsql
AS $$
declare 
	m_role_code integer;
	finish_status uuid;
	start_status uuid;
	manager_id uuid;
	new_req_id  uuid; 
begin
	m_role_code  := 2;
	start_status := '1167829b-ecfe-484c-a4fe-3e086d8c4d97';
	finish_status := 'ad631ed1-a650-4f9c-bfa1-70b84c6f0d10';
	
	manager_id := getfreemanager(m_role_code,finish_status);
	new_req_id  := uuid_generate_v4();

	INSERT INTO public.request
	(uuid, client_uuid, manager_uuid, payment_data)
	VALUES(new_req_id, user_id, manager_id ,paymentdata);
	
	INSERT INTO public.status_history
	(uuid, request_uuid, status_uuid, setup_timestamp, "comment")
	VALUES(uuid_generate_v4(), new_req_id, start_status, CURRENT_TIMESTAMP, '');


	return new_req_id;
end;
$$;

create or replace procedure public.add_chat_if_notexists(client_id uuid, manager_id uuid)
language plpgsql
as $$
begin
	if not exists(
		select 1 from chat ch 
		where ch.client_uuid  = client_id 
		and ch.manager_uuid = manager_id)
	then 
		INSERT INTO public.chat
		(uuid, client_uuid, manager_uuid)
		VALUES(uuid_generate_v4(), client_id, manager_id);
	end if;	
end
$$;

CREATE OR REPLACE PROCEDURE public.add_chat_if_notexists(client_id uuid, manager_id uuid)
LANGUAGE plpgsql
AS $$
begin
	if not exists(
		select 1 from chat ch 
		where ch.client_uuid  = client_id 
		and ch.manager_uuid = manager_id)
	then 
		INSERT INTO public.chat
		(uuid, client_uuid, manager_uuid)
		VALUES(uuid_generate_v4(), client_id, manager_id);
	end if;	
end
$$;

call add_chat_if_notexists('c2d28373-6c1f-485e-bc49-239874ba0601','012318fb-a204-4729-8a67-898f45f0a125');



CREATE OR REPLACE PROCEDURE public.update_chats(user_id uuid)
 LANGUAGE plpgsql
AS $$
declare 
	finish_status uuid; 
	manager_id uuid;
	m_role_code integer;
begin
	finish_status := 'ad631ed1-a650-4f9c-bfa1-70b84c6f0d10';
	
	if exists (
		select * from request r2 
		where r2.client_uuid = user_id
		and not exists (select * from status_history sh 
						where sh.request_uuid = r2.uuid 
						and sh.status_uuid = finish_status)) 	
	then 
		manager_id := (select r2.manager_uuid from request r2 
		where r2.client_uuid = user_id
		and not exists (select * from status_history sh 
			where sh.request_uuid = r2.uuid 
			and sh.status_uuid = finish_status) limit 1);
		call add_chat_if_notexists(user_id, manager_id );
	else 
		m_role_code  := 2;
		manager_id := getfreemanager(m_role_code, finish_status);
		call add_chat_if_notexists(user_id, manager_id);
	end if;
end;
$$;

call  update_chats('c2d28373-6c1f-485e-bc49-239874ba0601');
