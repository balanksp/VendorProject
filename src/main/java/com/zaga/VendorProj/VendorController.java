package com.zaga.VendorProj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/vendor")
public class VendorController {
    private static final Logger logger = LogManager.getLogger(VendorController.class);

    @Autowired
    VendorRepo repo;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    VendorService service;

    @Autowired
    Config appConfig;

    public VendorController(Config appConfig) {
        this.appConfig = appConfig;
    }

    @GetMapping("/vendorGetDataFromExternalApi")
    public VendorEntity getOrderDetailsFromExternalAPI(@RequestParam Long id) {
        try {
            // Create the URL with the query parameter using a UriComponentsBuilder
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(appConfig.getApiUrl())
                    .queryParam("id", id);
            logger.info("Received request to get data by the product Id");

            ResponseEntity<VendorEntity> responseEntity = restTemplate.getForEntity(builder.toUriString(),
                    VendorEntity.class);

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                logger.info("Received response from the product API");

                VendorEntity vendor = responseEntity.getBody();
                repo.save(vendor);
                logger.info("Updated vendor data successfully");
            }

            return responseEntity.getBody();
        } catch (Exception e) {
            logger.error("Error while fetching order details from the external API", e);
            throw e;
        }
    }

}