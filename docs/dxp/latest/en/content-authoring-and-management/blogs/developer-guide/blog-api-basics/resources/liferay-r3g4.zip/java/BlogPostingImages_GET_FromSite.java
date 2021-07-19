import com.liferay.headless.delivery.client.dto.v1_0.BlogPostingImage;
import com.liferay.headless.delivery.client.pagination.Page;
import com.liferay.headless.delivery.client.pagination.Pagination;
import com.liferay.headless.delivery.client.resource.v1_0.BlogPostingImageResource;

public class BlogPostingImages_GET_FromSite {

	/**
	 * java -classpath .:* -DsiteId=1234 BlogPostingImages_GET_FromSite
	 */
	public static void main(String[] args) throws Exception {
		BlogPostingImageResource.Builder builder =
			BlogPostingImageResource.builder();

		BlogPostingImageResource blogPostingImageResource =
			builder.authentication(
				"test@liferay.com", "test"
			).build();

		Page<BlogPostingImage> page =
			blogPostingImageResource.getSiteBlogPostingImagesPage(
				Long.valueOf(System.getProperty("siteId")), null, null, null,
				Pagination.of(1, 2), null);

		System.out.println(page);
	}

}