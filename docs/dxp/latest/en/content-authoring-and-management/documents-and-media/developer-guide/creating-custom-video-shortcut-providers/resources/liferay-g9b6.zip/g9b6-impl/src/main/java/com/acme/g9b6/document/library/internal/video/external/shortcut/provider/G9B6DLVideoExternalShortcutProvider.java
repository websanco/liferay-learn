package com.acme.g9b6.document.library.internal.video.external.shortcut.provider;

import com.liferay.document.library.video.external.shortcut.DLVideoExternalShortcut;
import com.liferay.document.library.video.external.shortcut.provider.DLVideoExternalShortcutProvider;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.Validator;
import java.net.HttpURLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = DLVideoExternalShortcutProvider.class)
public class G9B6DLVideoExternalShortcutProvider
    implements DLVideoExternalShortcutProvider {

    @Override
    public DLVideoExternalShortcut getDLVideoExternalShortcut(String url) {
        String dailymotionVideoId;
        Pattern urlPattern = Pattern.compile(
            "https?:\\/\\/(?:www\\.)?dai\\.ly\\/(\\S*)$");
            Matcher matcher = urlPattern.matcher(url);

        if (matcher.matches()) {
            dailymotionVideoId = matcher.group(1);
        } else {
            dailymotionVideoId = null;
        }

        if (Validator.isNull(dailymotionVideoId)) {
            return null;
        }

        return new DLVideoExternalShortcut() {

            @Override
            public String getURL() {
                return url;
            }

            @Override
            public String renderHTML(HttpServletRequest httpServletRequest) {
                String iframeSrc =
                    "https://www.dailymotion.com/embed/video/" + dailymotionVideoId +
                        "?rel=0";

                return StringBundler.concat(
                    "<iframe allow=\"autoplay; encrypted-media\" ",
                    "allowfullscreen height=\"315\" frameborder=\"0\" ",
                    "src=\"", iframeSrc, "\" width=\"560\"></iframe>");
            }
        };
    }
}
