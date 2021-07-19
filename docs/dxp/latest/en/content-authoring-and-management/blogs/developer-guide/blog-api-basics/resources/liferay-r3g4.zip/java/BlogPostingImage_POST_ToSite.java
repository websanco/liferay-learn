import com.liferay.headless.delivery.client.dto.v1_0.BlogPostingImage;
import com.liferay.headless.delivery.client.resource.v1_0.BlogPostingImageResource;

import java.io.File;

import java.util.HashMap;

public class BlogPostingImage_POST_ToSite {

	/**
	 * java -classpath .:* -DsiteId=1234 BlogPostingImage_POST_ToSite
	 */
	public static void main(String[] args) throws Exception {
		BlogPostingImageResource.Builder builder =
			BlogPostingImageResource.builder();

		BlogPostingImageResource blogPostingImageResource =
			builder.authentication(
				"test@liferay.com", "test"
			).build();

		BlogPostingImage blogPostingImage =
			blogPostingImageResource.postSiteBlogPostingImage(
				Long.valueOf(System.getProperty("siteId")),
				new BlogPostingImage(),
				new HashMap<String, File>() {
					{
						put("file", new File("liferay.png"));
					}
				});

		System.out.println(blogPostingImage);
	}

}