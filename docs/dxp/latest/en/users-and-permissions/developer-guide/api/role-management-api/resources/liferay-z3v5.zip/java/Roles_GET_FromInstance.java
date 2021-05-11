import com.liferay.headless.admin.user.dto.v1_0.Role;
import com.liferay.headless.admin.user.client.pagination.Page;
import com.liferay.headless.admin.user.client.pagination.Pagination;
import com.liferay.headless.admin.user.resource.v1_0.RoleResource;

public class Roles_GET_FromInstance {

	/**
	 * java -classpath .:* Roles_GET_FromInstance
	 */
	public static void main(String[] args) throws Exception {
		RoleResource.Builder builder = RoleResource.builder();

		RoleResource roleResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		Page<Role> role =
			roleResource.getRolessPage(
				null, null, Pagination.of(1, 2), null);

		System.out.println(role);
	}

}
