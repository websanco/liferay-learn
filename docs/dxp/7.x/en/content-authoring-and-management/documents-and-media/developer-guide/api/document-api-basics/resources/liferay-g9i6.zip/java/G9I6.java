
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.Base64;

public class G9I6 {

	public static void main(String[] args) {
		String urlString =
			"http://localhost:8080/o/headless-delivery/v1.0/sites/20121" +
				"/documents";

		try {
			URL url = new URL(urlString);

			HttpURLConnection httpURLConnection =
				(HttpURLConnection)url.openConnection();

			httpURLConnection.setRequestMethod("GET");

			Base64.Encoder encoder = Base64.getEncoder();
			String encodedAuth = encoder.encodeToString(
				"test@liferay.com:test".getBytes());

			httpURLConnection.setRequestProperty(
				"Authorization", "Basic " + encodedAuth);

			int responseCode = httpURLConnection.getResponseCode();

			InputStream inputStream = null;

			if (responseCode > 299) {
				inputStream = httpURLConnection.getErrorStream();
			}
			else {
				inputStream = httpURLConnection.getInputStream();
			}

			BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));

			StringBuilder sb = new StringBuilder();

			while (true) {
				String line = bufferedReader.readLine();

				if (line == null) {
					break;
				}

				sb.append(line);
			}

			bufferedReader.close();

			System.out.println(sb.toString());
		}
		catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

}