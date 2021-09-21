import com.liferay.headless.admin.user.client.dto.v1_0.AccountRole;
import com.liferay.headless.admin.user.client.resource.v1_0.AccountRoleResource;

public class AccountRole_POST_UserAssociation {

	/**
	 * java -classpath .:* -DaccountId=1234 -DaccountRoleId=5678 -DuserAccountId=9012 AccountRole_POST_UserAssociation
	 */
	public static void main(String[] args) throws Exception {
		AccountRoleResource.Builder builder = AccountRoleResource.builder();

		AccountRoleResource accountRoleResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		AccountRole accountRole = accountRoleResource.postAccountAccountRoleUserAccountAssociation(
			Long.valueOf(System.getProperty("accountId")),
			Long.valueOf(System.getProperty("accountRoleId")),
			Long.valueOf(System.getProperty("userAccountId")));

		System.out.println(accountRole);
	}

}