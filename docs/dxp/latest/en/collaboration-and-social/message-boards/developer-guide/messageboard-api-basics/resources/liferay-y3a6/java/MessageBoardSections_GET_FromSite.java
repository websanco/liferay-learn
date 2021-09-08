import com.liferay.headless.delivery.client.dto.v1_0.MessageBoardSection;
import com.liferay.headless.delivery.client.pagination.Page;
import com.liferay.headless.delivery.client.pagination.Pagination;
import com.liferay.headless.delivery.client.resource.v1_0.MessageBoardSectionResource;

public class MessageBoardSections_GET_FromSite {

	/**
	 * java -classpath .:* -DsiteId=1234 MessageBoardSections_GET_FromSite
	 */
	public static void main(String[] args) throws Exception {
		MessageBoardSectionResource.Builder builder = MessageBoardSectionResource.builder();
	
		MessageBoardSectionResource messageBoardSectionResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		Page<MessageBoardSection> page = messageBoardSectionResource.getSiteMessageBoardSectionsPage(
			Long.valueOf(System.getProperty("siteId")), null, null, null, null,
			Pagination.of(1, 2), null);

		System.out.println(page);
	}

}