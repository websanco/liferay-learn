import com.liferay.headless.admin.user.client.dto.v1_0.Account;
import com.liferay.headless.admin.user.client.resource.v1_0.AccountResource;

public class Account_GET_ById {

	/**
	 * java -classpath .:* -DaccountId=1234 Account_GET_ById
	 */
	public static void main(String[] args) throws Exception {
		AccountResource.Builder builder = AccountResource.builder();

		AccountResource accountResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		Account account = accountResource.getAccount(
			Long.valueOf(System.getProperty("accountId")));

		System.out.println(account);
	}

}