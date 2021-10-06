import com.liferay.headless.admin.user.client.dto.v1_0.UserAccount;
import com.liferay.headless.admin.user.client.pagination.Page;
import com.liferay.headless.admin.user.client.pagination.Pagination;
import com.liferay.headless.admin.user.client.resource.v1_0.UserAccountResource;

public class AccountUsers_GET_FromAccount {

	/**
	 * java -classpath .:* -DaccountId=1234 AccountUsers_GET_FromAccount
	 */
	public static void main(String[] args) throws Exception {
		UserAccountResource.Builder builder = UserAccountResource.builder();

		UserAccountResource userAccountResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		Page<UserAccount> page = userAccountResource.getAccountUserAccountsPage(
			Long.valueOf(System.getProperty("accountId")), null, null,
			Pagination.of(1, 2), null);

		System.out.println(page);
	}

}