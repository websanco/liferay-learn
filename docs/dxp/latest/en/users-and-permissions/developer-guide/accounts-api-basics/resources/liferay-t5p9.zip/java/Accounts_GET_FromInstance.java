import com.liferay.headless.admin.user.client.dto.v1_0.Account;
import com.liferay.headless.admin.user.client.pagination.Page;
import com.liferay.headless.admin.user.client.pagination.Pagination;
import com.liferay.headless.admin.user.client.resource.v1_0.AccountResource;

public class Accounts_GET_FromInstance {

	/**
	 * java -classpath .:* Accounts_GET_FromInstance
	 */
	public static void main(String[] args) throws Exception {
		AccountResource.Builder builder = AccountResource.builder();

		AccountResource accountResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		Page<Account> page = accountResource.getAccountsPage(
			null, null, Pagination.of(1, 2), null);

		System.out.println(page);
	}

}