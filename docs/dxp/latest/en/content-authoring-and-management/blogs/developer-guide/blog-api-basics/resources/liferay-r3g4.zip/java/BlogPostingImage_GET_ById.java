import com.liferay.headless.delivery.client.dto.v1_0.BlogPostingImage;
import com.liferay.headless.delivery.client.resource.v1_0.BlogPostingImageResource;

public class BlogPostingImage_GET_ById {

	/**
	 * java -classpath .:* -DblogPostingImageId=1234 BlogPostingImage_GET_ById
	 */
	public static void main(String[] args) throws Exception {
		BlogPostingImageResource.Builder builder =
			BlogPostingImageResource.builder();

		BlogPostingImageResource blogPostingImageResource =
			builder.authentication(
				"test@liferay.com", "test"
			).build();

		BlogPostingImage blogPostingImage =
			blogPostingImageResource.getBlogPostingImage(
				Long.valueOf(System.getProperty("blogPostingImageId")));

		System.out.println(blogPostingImage);
	}

}