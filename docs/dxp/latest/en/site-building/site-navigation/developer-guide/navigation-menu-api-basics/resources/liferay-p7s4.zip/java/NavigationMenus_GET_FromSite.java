import com.liferay.headless.delivery.client.dto.v1_0.NavigationMenu;
import com.liferay.headless.delivery.client.pagination.Page;
import com.liferay.headless.delivery.client.pagination.Pagination;
import com.liferay.headless.delivery.client.resource.v1_0.NavigationMenuResource;

public class NavigationMenus_GET_FromSite {

	/**
	 * java -classpath .:* -DsiteId=1234 NavigationMenus_GET_FromSite
	 */
	public static void main(String[] args) throws Exception {
		NavigationMenuResource.Builder builder =
			NavigationMenuResource.builder();

		NavigationMenuResource navigationMenuResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		Page<NavigationMenu> page =
			navigationMenuResource.getSiteNavigationMenusPage(
				Long.valueOf(System.getProperty("siteId")),
				Pagination.of(1, 2));

		System.out.println(page);
	}

}