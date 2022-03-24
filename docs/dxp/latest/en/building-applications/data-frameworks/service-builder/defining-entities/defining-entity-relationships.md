# Defining Entity Relationships

Relationships between database entities or Java objects are necessary for most applications. Take Liferay's Message Boards application as an example. Each Message Board message belongs to a specific Message Board thread. A Message Board thread might also belong to a specific Message Board category. 

You can see how the relationship is defined in the application's [`service.xml`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/message-boards/message-boards-service/service.xml) file: 

```xml
<entity external-reference-code="group" human-name="message-boards message" local-service="true" name="MBMessage" remote-service="true" trash-enabled="true" uuid="true">

	<!-- PK fields -->

	<column name="messageId" primary="true" type="long" />

	<!-- Group instance -->

	<column name="groupId" type="long" />

	<!-- Audit fields -->

	<column name="companyId" type="long" />
	<column name="userId" type="long" />
	<column name="userName" type="String" uad-anonymize-field-name="fullName" />
	<column name="createDate" type="Date" />
	<column name="modifiedDate" type="Date" />

	<!-- Other fields -->

	<column name="classNameId" type="long" />
	<column name="classPK" type="long" />
	<column name="categoryId" type="long" />
	<column name="threadId" type="long" />
	...
```

The `threadId` field referenced in this `MBMessage` object has the same name as the primary key in the `MBThread` object (not shown above). This creates the relationship between the two objects. A similar relationship can be seen with `categoryId` and the `MBCategory` object. 

Congratulations, now you know how to relate two entities. 
