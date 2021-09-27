# Implementing a Custom Exchange Rate Provider

This tutorial will show you how to add a custom exchange rate provider by implementing the [ExchangeRateProvider](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-currency-api/src/main/java/com/liferay/commerce/currency/util/ExchangeRateProvider.java) interface.

An exchange rate provider uses a data source to perform the exchange calculation between currencies. Liferay Commerce provides one exchange rate provider out-of-the-box, [ECBExchangeRateProvider](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-currency-service/src/main/java/com/liferay/commerce/currency/internal/util/ECBExchangeRateProvider.java).

![Out-of-the-box exchange rate provider](./implementing-an-exchange-rate-provider/images/01.png "Out-of-the-box exchange rate provider")

## Overview

1. [**Deploy an Example**](#deploy-an-example)
1. [**Walk Through the Example**](#walk-through-the-example)
1. [**Additional Information**](#additional-information)

## Deploy an Example

In this section, we will get an example exchange rate provider up and running on your instance of Liferay Commerce. Follow these steps:

1. Start Liferay Commerce.

    ```bash
    docker run -it -p 8080:8080 [$LIFERAY_LEARN_PORTAL_DOCKER_IMAGE$]
    ```

1. Download and unzip the [Acme Commerce Exchange Rate Provider](./liferay-f2y1.zip).

    ```bash
    curl https://learn.liferay.com/commerce/latest/en/developer-guide/liferay-f2y1.zip -O
    ```

    ```bash
    unzip liferay-f2y1.zip
    ```

1. Go to `liferay-f2y1`.

    ```bash
    cd liferay-f2y1
    ```

1. Build and deploy the example.

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```note::
       This command is the same as copying the deployed jars to ``/opt/liferay/osgi/modules`` on the Docker container.
    ```

1. Confirm the deployment in the Docker container console.

    ```bash
    STARTED com.acme.f2y1.impl_1.0.0
    ```

1. Verify that the example exchange rate provider was added. Open your browser to `https://localhost:8080`. Then click the Applications Menu (![Applications Menu](../images/icon-applications-menu.png)) and navigate to _Commerce_ → _Currencies_. The new exchange rate provider ("f2y1") will be present under the _Exchange Rate Provider_ dropdown.

```note::
   In Liferay Commerce 2.1 and earlier, find the exchange rates by navigating to *Control Panel* → *Commerce* → *Settings* → *Currencies* → *Exchange Rate*. The new exchange rate provider ("f2y1") will be present under the *Exchange Rate Provider* dropdown.
```

![New exchange rate provider](./implementing-an-exchange-rate-provider/images/02.png "New exchange rate provider")

Congratulations, you've successfully built and deployed a new exchange rate provider that implements `ExchangeRateProvider`.

Next, let's dive deeper to learn more.

## Walk Through the Example

In this section, we will review the example we deployed. First, we will annotate the class for OSGi registration. Second, we will review the `ExchangeRateProvider` interface. And third, we will complete our implementation of `ExchangeRateProvider`.

### Annotate the Class for OSGi Registration

```java
@Component(
    immediate = true, property = "commerce.exchange.provider.key=f2y1",
    service = ExchangeRateProvider.class
)
public class F2Y1ExchangeRateProvider implements ExchangeRateProvider {
```

> It is important to provide a distinct key for the exchange rate provider so that Liferay Commerce can distinguish the new exchange rate provider from others in the [exchange rate provider registry](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-currency-service/src/main/java/com/liferay/commerce/currency/internal/util/ExchangeRateProviderRegistryImpl.java). Reusing a key that is already in use will override the existing associated exchange rate provider.

### Review the `ExchangeRateProvider` Interface

Implement the following method:

```java
@Override
public BigDecimal getExchangeRate(
        CommerceCurrency primaryCommerceCurrency,
        CommerceCurrency secondaryCommerceCurrency)
    throws Exception;
```

> This method is called to calculate the exchange rate between currencies. The chosen data source for the rates must be able to handle any of the currencies that may be used in your instance of Liferay Commerce.

### Complete the Exchange Rate Provider

The exchange rate provider is comprised of logic to calculate the exchange rates between two currencies. Do the following:

* [Implement the exchange rate calculation logic.](#implement-the-exchange-rate-calculation-logic)

#### Implement the Exchange Rate Calculation Logic

```java
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
```

> This example uses a map of exchange rates. See [ECBExchangeRateProvider](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-currency-service/src/main/java/com/liferay/commerce/currency/internal/util/ECBExchangeRateProvider.java) for a more practical use case. See `_getStaticExchangeRates` and `_getRateForCode` by visiting [F2Y1ExchangeRateProvider.java](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/implementing-an-exchange-rate-provider/resources/liferay-f2y1.zip/f2y1-impl/src/main/java/com/acme/f2y1/internal/commerce/currency/util/F2Y1ExchangeRateProvider.java).
>
> Use the `CommerceCurrency` object for the two currencies to get the information needed, like their currency codes. See [CommerceCurrency.java](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-currency-api/src/main/java/com/liferay/commerce/currency/model/CommerceCurrency.java) and [CommerceCurrencyModel.java](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-currency-api/src/main/java/com/liferay/commerce/currency/model/CommerceCurrencyModel.java) to find more methods you can use with a `CommerceCurrency` object.

## Conclusion

Congratulations! You now know the basics for implementing the `ExchangeRateProvider` interface, and have added a new exchange rate provider to Liferay Commerce.

## Additional Information

* [Adding a New Currency](../../store-administration/currencies/adding-a-new-currency.md)
* [Managing Exchange Rates](../../store-administration/currencies/managing-exchange-rates.md)
