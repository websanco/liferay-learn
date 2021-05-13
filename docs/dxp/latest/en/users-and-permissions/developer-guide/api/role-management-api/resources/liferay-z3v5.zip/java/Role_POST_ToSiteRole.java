import com.liferay.headless.admin.user.client.resource.v1_0.RoleResource;

public class Role_POST_ToSiteRole {

	/**
	 * java -classpath .:* -DroleId=1234 -DuserAccountId=5678 -DsiteId=9012 Role_POST_ToSiteRole
	 */
	public static void main(String[] args) throws Exception {
		RoleResource.Builder builder = RoleResource.builder();

		RoleResource roleResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		roleResource.postSiteRoleUserAccountAssociation(
			Long.valueOf(System.getProperty("roleId")),
			Long.valueOf(System.getProperty("userAccountId")),
			Long.valueOf(System.getProperty("siteId")));
	}

}