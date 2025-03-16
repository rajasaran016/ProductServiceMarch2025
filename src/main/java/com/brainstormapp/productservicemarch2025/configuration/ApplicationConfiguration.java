package com.brainstormapp.productservicemarch2025.configuration;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {

    @Value("${razorpay.id}")
    private String razorpayId;

    @Value("${razorpay.secret}")
    private String razorpaySecret;

    @Bean
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RazorpayClient createRazorpayClient() throws RazorpayException {
        try {
            return new RazorpayClient(razorpayId, razorpaySecret);
        }
        catch (RazorpayException e) {
            throw new RazorpayException(e.getMessage());
        }
    }

}
