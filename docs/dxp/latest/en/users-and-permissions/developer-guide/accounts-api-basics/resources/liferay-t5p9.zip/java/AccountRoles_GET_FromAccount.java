import com.liferay.headless.admin.user.client.dto.v1_0.AccountRole;
import com.liferay.headless.admin.user.client.pagination.Page;
import com.liferay.headless.admin.user.client.pagination.Pagination;
import com.liferay.headless.admin.user.client.resource.v1_0.AccountRoleResource;

public class AccountRoles_GET_FromAccount {

	/**
	 * java -classpath .:* AccountRoles_GET_FromAccount
	 */
	public static void main(String[] args) throws Exception {
		AccountRoleResource.Builder builder = AccountRoleResource.builder();

		AccountRoleResource accountRoleResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		Page<AccountRole> page = accountRoleResource.getAccountAccountRolesPage(
			null, null, Pagination.of(1, 2), null);

		System.out.println(page);
	}

}