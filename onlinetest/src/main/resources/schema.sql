create table if not exists candidate(
	canid serial,
	name text,
	gender text,
	emailid text,
	degree text,
	collegename text,
	department text,
	graduationyear int,
	percentage numeric(5,2),
	phoneNo text,
	govtuidtype text,
	govuid text,
	currentemployer text,
	currentposition text,
	currentempstartdate date,
	currentempenddate date,
	currentlyworking boolean,
	currentempcity text,
	totalexperienceyrs int,
	totalexperiencemonths int,
	isactive boolean default true,
	creationdate timestamp default CURRENT_TIMESTAMP,
	updationdate timestamp,
	updatedby text
);

create table if not exists questions(
	questionid serial,
	sectionid int,
	questiontext text,
	option1 text,
	option2 text,
	option3 text,
	option4 text,
	answer int,
	complexity text,
	isactive boolean default true,
	creationdate timestamp default CURRENT_TIMESTAMP,
	updationdate timestamp,
	updatedby text
);

create table if not exists sections(
	sectionid serial,
	sectionname text,
	isactive boolean default true,
	creationdate timestamp default CURRENT_TIMESTAMP,
	updationdate timestamp,
	updatedby text
);

create table if not exists tests(
	testid serial,
	testname text,
	islateraltest boolean default false,
	testdurationinmins int,
	isnegativemarking boolean default true,
	isactive boolean default true,
	creationdate timestamp default CURRENT_TIMESTAMP,
	updationdate timestamp,
	updatedby text
);

create table if not exists testcodemapping(
	testid int,
	testcode text,
	activefrom timestamp,
	activeto timestamp,
	institutename text,
	contactname text,
	contactno text,
	contactemail text,
	testcodecreationtime timestamp default CURRENT_TIMESTAMP,
	istestcodeactive boolean default true,
	updationdate timestamp,
	updatedby text	
);

create table if not exists testsectionmapping(
	testid int,
	sectionid int,
	easyno int,
	mediumno int,
	hardno int,
	creationdate timestamp default CURRENT_TIMESTAMP,
	updationdate timestamp,
	updatedby text
);

create table if not exists testquestionmapping(
	testid int,
	questionid int,
	sectionid int,
	questiontext text,
	option1 text,
	option2 text,
	option3 text,
	option4 text,
	complexity text,
	answer int,
	creationdate timestamp default CURRENT_TIMESTAMP,
	updationdate timestamp,
	updatedby text
);

create table if not exists candidatetestmapping(
	candidateid int,
	testid int,
	questionid int,
	selectedanswer int,
	isanswervalid boolean,
	answerdate timestamp default CURRENT_TIMESTAMP,
	updationdate timestamp
);

create table if not exists candidatetestsummary(
	candidateid int,
	testid int,
	uuid text not null, --to validate candidate session
	isteststarted boolean default true,
	starttime timestamp default CURRENT_TIMESTAMP,
	endtime timestamp,
	elapsedtimeinmins int,--Used to resume an active test gets updated every 5 mins
	istestcompleted boolean default false,
	scored float,
	maxscore float,
	lowscore float,
	mediumscore float,
	highscore float,
	sectionbreakup text, --JSON text
	testcompletiontext text default 'Normal'
);

create table if not exists dtypes(
	did int primary key,
	dtype text,--Govt id types, question complexity types
	typevalue text,
	creationdate timestamp default CURRENT_TIMESTAMP
);

create table if not exists users(
	userid serial primary key,
	username text,
	useremail text unique,
	userpassword text,
	isactive boolean default true,
	firsttimelogin boolean default true,
	creationdate timestamp default CURRENT_TIMESTAMP,
	updationdate timestamp,
	updatedby text
);

create table if not exists roles(
	roleid serial primary key,
	rolename text
);

create table if not exists userroles(
	id serial primary key,
	userid int,
	roleid int,
	creationdate timestamp default CURRENT_TIMESTAMP
);

