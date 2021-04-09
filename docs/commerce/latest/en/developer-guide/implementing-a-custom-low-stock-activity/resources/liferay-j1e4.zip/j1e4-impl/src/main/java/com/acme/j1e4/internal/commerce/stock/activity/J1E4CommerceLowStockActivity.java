package com.acme.j1e4.internal.commerce.stock.activity;

import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.stock.activity.CommerceLowStockActivity;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

@Component(
	property = {
		"commerce.low.stock.activity.key=j1e4",
		"commerce.low.stock.activity.priority:Integer=9"
	},
	service = CommerceLowStockActivity.class
)
public class J1E4CommerceLowStockActivity implements CommerceLowStockActivity {

	@Override
	public void execute(CPInstance cpInstance) throws PortalException {
		if (_log.isWarnEnabled()) {
			_log.warn("SKU " + cpInstance.getSku());
		}
	}

	@Override
	public String getKey() {
		return "j1e4";
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(
			resourceBundle, "j1e4-commerce-low-stock-activity");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		J1E4CommerceLowStockActivity.class);

}