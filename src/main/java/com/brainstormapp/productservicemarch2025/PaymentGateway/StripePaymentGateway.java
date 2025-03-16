package com.brainstormapp.productservicemarch2025.PaymentGateway;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class StripePaymentGateway implements IPaymentGateway {
    @Value("${stripe.apikey}")
    private String stripeApiKey;

    @Override
    public String paymentLink(String name, String email, long amount, String orderId, String phoneNumber) {
        try {
            Stripe.apiKey = this.stripeApiKey;

            Price price = getPrice(amount);
            PaymentLinkCreateParams params =
                    PaymentLinkCreateParams.builder()
                            .addLineItem(
                                    PaymentLinkCreateParams.LineItem.builder()
                                            .setPrice(price.getId())
                                            .setQuantity(1L)
                                            .build()
                            )
                            .build();
            return PaymentLink.create(params).getUrl();
        } catch (StripeException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    private Price getPrice(long amount) {
        try {

            PriceCreateParams params =
                    PriceCreateParams.builder()
                            .setCurrency("usd")
                            .setUnitAmount(amount)
                            .setRecurring(
                                    PriceCreateParams.Recurring.builder()
                                            .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                            .build()
                            )
                            .setProductData(
                                    PriceCreateParams.ProductData.builder().setName("Gold Plan").build()
                            )
                            .build();
            return Price.create(params);
        } catch (StripeException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}