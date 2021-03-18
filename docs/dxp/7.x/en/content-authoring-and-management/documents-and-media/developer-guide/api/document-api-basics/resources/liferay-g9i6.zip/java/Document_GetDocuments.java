import com.liferay.headless.delivery.client.dto.v1_0.Document;
import com.liferay.headless.delivery.client.pagination.Page;
import com.liferay.headless.delivery.client.pagination.Pagination;
import com.liferay.headless.delivery.client.resource.v1_0.DocumentResource;

public class Document_GetDocuments {

	public static void main(String[] args) throws Exception {
		String id = System.getProperty("site.id", "20121");

		DocumentResource.Builder builder = DocumentResource.builder();

		DocumentResource documentResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		Page<Document> page = documentResource.getSiteDocumentsPage(
			Long.valueOf(id), null, null, null, null, Pagination.of(1, 2),
			null);

		System.out.println(page.getItems());
	}

}