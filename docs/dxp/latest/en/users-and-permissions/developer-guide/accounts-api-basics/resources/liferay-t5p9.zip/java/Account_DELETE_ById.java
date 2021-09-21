import com.liferay.headless.admin.user.client.resource.v1_0.AccountResource;

public class Account_DELETE_ById {

	/**
	 * java -classpath .:* -DaccountId=1234 Account_DELETE_ById
	 */
	public static void main(String[] args) throws Exception {
		AccountResource.Builder builder = AccountResource.builder();

		AccountResource accountResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		accountResource.deleteAccount(
			Long.valueOf(System.getProperty("accountId")));
	}

}