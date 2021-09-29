create table H6D2_H6D2Entry (
	uuid_ VARCHAR(75) null,
	h6d2EntryId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null
);