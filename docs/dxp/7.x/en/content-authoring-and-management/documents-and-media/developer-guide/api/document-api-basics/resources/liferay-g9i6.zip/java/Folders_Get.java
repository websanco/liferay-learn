import com.liferay.headless.delivery.client.dto.v1_0.DocumentFolder;
import com.liferay.headless.delivery.client.pagination.Page;
import com.liferay.headless.delivery.client.pagination.Pagination;
import com.liferay.headless.delivery.client.resource.v1_0.DocumentFolderResource;

import java.util.Collection;

public class Folders_Get {

	public static void main(String[] args) throws Exception {
		String siteId = System.getProperty("site.id", "20121");

		DocumentFolderResource.Builder builder = DocumentFolderResource.builder();

		DocumentFolderResource documentFolderResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		Page<DocumentFolder> page = documentFolderResource.getSiteDocumentFoldersPage(
			Long.valueOf(siteId), null, null, null, null, Pagination.of(1, 2),
			null);

		Collection<DocumentFolder> documentFolders = page.getItems();

		System.out.println(documentFolders);
	}

}