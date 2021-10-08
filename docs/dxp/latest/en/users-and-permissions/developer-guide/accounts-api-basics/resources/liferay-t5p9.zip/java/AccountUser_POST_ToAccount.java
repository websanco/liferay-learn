import com.liferay.headless.admin.user.client.dto.v1_0.UserAccount;
import com.liferay.headless.admin.user.client.resource.v1_0.UserAccountResource;

public class AccountUser_POST_ToAccount {

	/**
	 * java -classpath .:* -DaccountId=1234 AccountUser_POST_ToAccount
	 */
	public static void main(String[] args) throws Exception {
		UserAccountResource.Builder builder = UserAccountResource.builder();

		UserAccountResource userAccountResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		UserAccount userAccount = userAccountResource.postAccountUserAccount(
			Long.valueOf(System.getProperty("accountId")),
			new UserAccount() {
				{
					alternateName = "Item";
					emailAddress = "item@liferay.com";
					familyName = "Jig";
					givenName = "Item";
				}
			});

		System.out.println(userAccount);
	}

}