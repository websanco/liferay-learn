create table H6D2_H6D2 (
	uuid_ VARCHAR(75) null,
	h6d2Id LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	todo VARCHAR(75) null
);