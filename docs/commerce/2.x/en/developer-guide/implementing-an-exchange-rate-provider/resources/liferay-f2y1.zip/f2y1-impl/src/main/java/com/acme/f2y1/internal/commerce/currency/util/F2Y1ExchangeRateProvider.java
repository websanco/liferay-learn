package com.acme.f2y1.internal.commerce.currency.util;

import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.util.ExchangeRateProvider;
import com.liferay.portal.kernel.util.StringUtil;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true, property = "commerce.exchange.provider.key=f2y1",
	service = ExchangeRateProvider.class
)
public class F2Y1ExchangeRateProvider implements ExchangeRateProvider {

	@Override
	public BigDecimal getExchangeRate(
			CommerceCurrency primaryCommerceCurrency,
			CommerceCurrency secondaryCommerceCurrency)
		throws Exception {

		return new BigDecimal(
			_getExchangeRate(secondaryCommerceCurrency) /
				_getExchangeRate(primaryCommerceCurrency));
	}

	private Double _getExchangeRate(CommerceCurrency commerceCurrency) {
		String code = StringUtil.toUpperCase(commerceCurrency.getCode());

		return _exchangeRates.get(code);
	}

	private Map<String, Double> _exchangeRates = new HashMap<String, Double>() {
		{
			put("AUD", 1.9454);
			put("BRL", 5.15262);
			put("CAD", 1.84981);
			put("CHF", 1.36562);
			put("CLP", 947.813);
			put("CNY", 9.49073);
			put("CZK", 31.0599);
			put("DKK", 9.04642);
			put("EUR", 1.21177);
			put("GBP", 1.09733);
			put("HKD", 10.9628);
			put("HUF", 390.23);
			put("IDR", 19698.8);
			put("ILS", 5.12143);
			put("INR", 98.562);
			put("JPY", 150.862);
			put("KRW", 1567.74);
			put("MXN", 26.7972);
			put("MYR", 5.72459);
			put("NOK", 11.8138);
			put("NZD", 2.05827);
			put("PHP", 73.2097);
			put("PKR", 194.073);
			put("PLN", 5.22207);
			put("RUB", 93.4562);
			put("SEK", 12.4178);
			put("SGD", 1.88797);
			put("THB", 44.6128);
			put("USD", 1.39777);
			put("ZAR", 19.3996);
		}
	};

}