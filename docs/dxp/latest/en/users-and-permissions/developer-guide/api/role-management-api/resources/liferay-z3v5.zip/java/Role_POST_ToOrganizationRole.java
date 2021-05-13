import com.liferay.headless.admin.user.client.resource.v1_0.RoleResource;

public class Role_POST_ToOrganizationRole {

	/**
	 * java -classpath .:* -DorganizationId=1234 -DroleId=5678 -DuserAccountId=9012 Role_POST_ToOrganizationRole
	 */
	public static void main(String[] args) throws Exception {
		RoleResource.Builder builder = RoleResource.builder();

		RoleResource roleResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		roleResource.postOrganizationRoleUserAccountAssociation(
			Long.valueOf(System.getProperty("roleId")),
			Long.valueOf(System.getProperty("userAccountId")),
			Long.valueOf(System.getProperty("organizationId")));
	}

}