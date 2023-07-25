package com.zaga.VendorProj;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VendorService {

    private static final String EXTERNAL_API_URL = "http://localhost:5089/orders/getOrders";

    private final RestTemplate restTemplate;

    public VendorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public VendorEntity fetchBooksFromExternalApi() {
        return restTemplate.getForObject(EXTERNAL_API_URL, VendorEntity.class);
    }
}
