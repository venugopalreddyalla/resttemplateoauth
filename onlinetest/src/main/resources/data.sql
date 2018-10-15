
insert into dtypes(did,dtype,typevalue) values (1,'GOVTUID','Aadhaar No') ON CONFLICT(did) DO NOTHING;
insert into dtypes(did,dtype,typevalue) values (2,'GOVTUID','Driving License No') ON CONFLICT(did) DO NOTHING;
insert into dtypes(did,dtype,typevalue) values (3,'GOVTUID','Passport No') ON CONFLICT(did) DO NOTHING;
insert into dtypes(did,dtype,typevalue) values (4,'GOVTUID','VoterId No') ON CONFLICT(did) DO NOTHING;
insert into dtypes(did,dtype,typevalue) values (5,'GOVTUID','PAN Card No') ON CONFLICT(did) DO NOTHING;

insert into dtypes(did,dtype,typevalue) values (6,'GENDER','Male') ON CONFLICT(did) DO NOTHING;
insert into dtypes(did,dtype,typevalue) values (7,'GENDER','Female') ON CONFLICT(did) DO NOTHING;
insert into dtypes(did,dtype,typevalue) values (8,'GENDER','Other') ON CONFLICT(did) DO NOTHING;

insert into dtypes(did,dtype,typevalue) values (9,'CANDIDATEVALIDITY','180') ON CONFLICT(did) DO NOTHING;

insert into roles(roleid,rolename) values (1,'ADMIN') ON CONFLICT(roleid) DO NOTHING;
insert into roles(roleid,rolename) values (2,'RECRUITER') ON CONFLICT(roleid) DO NOTHING;