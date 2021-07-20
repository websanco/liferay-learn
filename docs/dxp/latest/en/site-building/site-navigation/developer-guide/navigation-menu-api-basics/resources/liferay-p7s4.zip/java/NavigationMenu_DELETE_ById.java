import com.liferay.headless.delivery.client.resource.v1_0.NavigationMenuResource;

public class NavigationMenu_DELETE_ById {

	/**
	 * java -classpath .:* -DnavigationMenuId=1234 NavigationMenu_DELETE_ById
	 */
	public static void main(String[] args) throws Exception {
		NavigationMenuResource.Builder builder =
			NavigationMenuResource.builder();

		NavigationMenuResource navigationMenuResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		navigationMenuResource.deleteNavigationMenu(
			Long.valueOf(System.getProperty("navigationMenuId")));
	}

}