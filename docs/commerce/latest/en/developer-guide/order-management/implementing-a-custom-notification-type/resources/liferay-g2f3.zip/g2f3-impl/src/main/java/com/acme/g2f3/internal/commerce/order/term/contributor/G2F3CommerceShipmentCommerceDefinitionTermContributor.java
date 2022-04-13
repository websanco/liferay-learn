package com.acme.g2f3.internal.commerce.order.term.contributor;

import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.constants.CommerceDefinitionTermConstants;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceShipment;
import com.liferay.commerce.order.CommerceDefinitionTermContributor;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"commerce.definition.term.contributor.key=" + CommerceDefinitionTermConstants.BODY_AND_SUBJECT_DEFINITION_TERMS_CONTRIBUTOR,
		"commerce.notification.type.key=g2f3"
	},
	service = CommerceDefinitionTermContributor.class
)
public class G2F3CommerceShipmentCommerceDefinitionTermContributor
	implements CommerceDefinitionTermContributor {

	@Override
	public String getFilledTerm(String term, Object object, Locale locale)
		throws PortalException {

		if (!(object instanceof CommerceShipment)) {
			return term;
		}

		CommerceShipment commerceShipment = (CommerceShipment)object;

		if (term.equals(_ORDER_CREATOR_NAME)) {
			CommerceAccount commerceAccount =
				commerceShipment.getCommerceAccount();

			if (commerceAccount.getType() ==
					CommerceAccountConstants.ACCOUNT_TYPE_PERSONAL) {

				User user = _userLocalService.getUser(
					commerceAccount.getUserId());

				return user.getFullName(true, true);
			}

			return commerceAccount.getName();
		}

		if (term.equals(_ORDER_SHIPPING_ADDRESS)) {
			
			CommerceAddress commerceAddress = commerceShipment.fetchCommerceAddress();
			
			return _commerceDefinitionTermContributor.getFilledTerm((commerceAddress.getStreet1()
					+ ", " + commerceAddress.getCity() + ", " + commerceAddress.getZip()), 
					commerceAddress, locale);
		}

		if (term.equals(_SHIPMENT_ID)) {
			if (commerceShipment == null) {
				return term;
			}

			return String.valueOf(commerceShipment.getCommerceShipmentId());
		}

		return term;
	}

	@Override
	public String getLabel(String term, Locale locale) {
		return LanguageUtil.get(
			locale, _commerceShipmentDefinitionTermsMap.get(term));
	}

	@Override
	public List<String> getTerms() {
		return new ArrayList<>(_commerceShipmentDefinitionTermsMap.keySet());
	}

	private static final String _ORDER_CREATOR_NAME = "[%ORDER_CREATOR_NAME%]";

	private static final String _ORDER_SHIPPING_ADDRESS =
		"[%ORDER_SHIPPING_ADDRESS%]";

	private static final String _SHIPMENT_ID = "[%SHIPMENT_ID%]";

	private static final Log _log = LogFactoryUtil.getLog(
		G2F3CommerceShipmentCommerceDefinitionTermContributor.class);

	private static final Map<String, String>
		_commerceShipmentDefinitionTermsMap = HashMapBuilder.put(
			_ORDER_CREATOR_NAME, "g2f3-order-creator-name-definition-term"
		).put(
			_ORDER_SHIPPING_ADDRESS,
			"g2f3-order-shipping-address-definition-term"
		).put(
			_SHIPMENT_ID, "g2f3-shipment-id-definition-term"
		).build();

	@Reference
	private UserLocalService _userLocalService;
	
	@Reference
	private CommerceDefinitionTermContributor _commerceDefinitionTermContributor;

}