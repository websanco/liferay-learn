import com.liferay.headless.delivery.client.dto.v1_0.NavigationMenu;
import com.liferay.headless.delivery.client.resource.v1_0.NavigationMenuResource;

public class NavigationMenu_POST_ToSite {

	/**
	 * java -classpath .:* -DsiteId=1234 NavigationMenu_POST_ToSite
	 */
	public static void main(String[] args) throws Exception {
		NavigationMenuResource.Builder builder =
			NavigationMenuResource.builder();

		NavigationMenuResource navigationMenuResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		NavigationMenu navigationMenu =
			navigationMenuResource.postSiteNavigationMenu(
				Long.valueOf(System.getProperty("siteId")),
				new NavigationMenu() {
					{
						name = "Foo";
					}
				});

		System.out.println(navigationMenu);
	}

}