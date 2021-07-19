import com.liferay.headless.delivery.client.dto.v1_0.BlogPosting;
import com.liferay.headless.delivery.client.pagination.Page;
import com.liferay.headless.delivery.client.pagination.Pagination;
import com.liferay.headless.delivery.client.resource.v1_0.BlogPostingResource;

public class BlogPostings_GET_FromSite {

	/**
	 * java -classpath .:* -DsiteId=1234 BlogPostings_GET_FromSite
	 */
	public static void main(String[] args) throws Exception {
		BlogPostingResource.Builder builder = BlogPostingResource.builder();

		BlogPostingResource blogPostingResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		Page<BlogPosting> page = blogPostingResource.getSiteBlogPostingsPage(
			Long.valueOf(System.getProperty("siteId")), null, null, null,
			Pagination.of(1, 2), null);

		System.out.println(page);
	}

}