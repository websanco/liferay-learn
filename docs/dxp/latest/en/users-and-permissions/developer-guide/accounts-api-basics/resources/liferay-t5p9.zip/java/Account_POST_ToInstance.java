import com.liferay.headless.admin.user.client.dto.v1_0.Account;
import com.liferay.headless.admin.user.client.resource.v1_0.AccountResource;

public class Account_POST_ToInstance {

	/**
	 * java -classpath .:* Account_POST_ToInstance
	 */
	public static void main(String[] args) throws Exception {
		AccountResource.Builder builder = AccountResource.builder();

		AccountResource accountResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		Account account = accountResource.postAccount(
			new Account() {
				{
					description = "Foo";
					name = "Baker";
				}
			});

		System.out.println(account);
	}

}