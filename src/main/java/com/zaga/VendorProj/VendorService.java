package com.zaga.VendorProj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VendorService {

    private static final Logger logger = LogManager.getLogger(VendorService.class);

    @Autowired
    Config appConfig;

    private final RestTemplate restTemplate;

    public VendorService(RestTemplate restTemplate, Config appConfig) {
        this.restTemplate = restTemplate;
        this.appConfig = appConfig;
    }

    public VendorEntity fetchBooksFromExternalApi() {
        try {
            logger.info("Fetching data from external API...");
            VendorEntity vendorEntity = restTemplate.getForObject(appConfig.getApiUrl(), VendorEntity.class);
            logger.info("Data fetched successfully: {}", vendorEntity);
            return vendorEntity;
        } catch (Exception e) {
            logger.error("Error while fetching data from external API", e);
            throw e;
        }
    }
}