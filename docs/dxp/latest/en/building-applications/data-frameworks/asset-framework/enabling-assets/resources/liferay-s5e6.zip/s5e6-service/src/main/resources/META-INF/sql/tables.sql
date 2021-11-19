create table S5E6_S5E6Entry (
	uuid_ VARCHAR(75) null,
	S5E6EntryId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	description VARCHAR(75) null,
	name VARCHAR(75) null
);