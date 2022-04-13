package com.acme.g2f3.internal.order.term.contributor;

import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountUserRelLocalService;
import com.liferay.commerce.constants.CommerceDefinitionTermConstants;
import com.liferay.commerce.model.CommerceShipment;
import com.liferay.commerce.order.CommerceDefinitionTermContributor;
import com.liferay.commerce.service.CommerceOrderItemLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserGroupLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"commerce.definition.term.contributor.key=" + G2F3CommerceShipmentRecipientCommerceDefinitionTermContributor.KEY,
		"commerce.notification.type.key=g2f3"
	},
	service = CommerceDefinitionTermContributor.class
)
public class G2F3CommerceShipmentRecipientCommerceDefinitionTermContributor
	implements CommerceDefinitionTermContributor {

	public static final String KEY =
		CommerceDefinitionTermConstants.RECIPIENT_DEFINITION_TERMS_CONTRIBUTOR;

	@Override
	public String getFilledTerm(String term, Object object, Locale locale)
		throws PortalException {

		if (!(object instanceof CommerceShipment)) {
			return term;
		}

		CommerceShipment commerceShipment = (CommerceShipment)object;

		if (commerceShipment == null) {
			return term;
		}

		if (term.equals(_ORDER_CREATOR_EMAIL)) {
			CommerceAccount commerceAccount =
					commerceShipment.getCommerceAccount();

			if (commerceAccount.getType() ==
					CommerceAccountConstants.ACCOUNT_TYPE_PERSONAL) {

				User user = _userLocalService.getUser(
					commerceAccount.getUserId());
				return String.valueOf(user.getUserId());
			}
			return String.valueOf(commerceShipment.getUserId());
		}
		
		return term;
	}

	@Override
	public String getLabel(String term, Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(
			resourceBundle, _commerceShipmentDefinitionTermsMap.get(term));
	}

	@Override
	public List<String> getTerms() {
		return new ArrayList<>(_commerceShipmentDefinitionTermsMap.keySet());
	}

	private static final String _ORDER_CREATOR_EMAIL = "[%ORDER_CREATOR_EMAIL%]";

	private static final Map<String, String> _commerceShipmentDefinitionTermsMap =
		HashMapBuilder.put(
			_ORDER_CREATOR_EMAIL, "g2f3-order-creator-email-definition-term"
		).build();

	@Reference
	private UserLocalService _userLocalService;

}