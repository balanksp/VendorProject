package com.zaga.VendorProj;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    VendorRepo repo;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    VendorService service;

    // @GetMapping("/vendorGetDataFromExternalApi")
    // public VendorEntity getOrderDetailsFromExternalAPI(Long id) {
    // ResponseEntity<VendorEntity> responseEntity =
    // restTemplate.getForEntity("http://localhost:5089/orders/getOrders?id=97",
    // VendorEntity.class, null);

    // if (responseEntity.getStatusCode() == HttpStatus.OK) {
    // VendorEntity vendor = responseEntity.getBody();
    // repo.save(vendor);

    // }
    // return responseEntity.getBody();
    // }

    // @GetMapping("/external")
    // public VendorEntity getBooksFromExternalApi(@RequestParam Long id) {

    // VendorEntity getresult = service.fetchBooksFromExternalApi();
    // return getresult;
    // }

    @GetMapping("/vendorGetDataFromExternalApi")
    public VendorEntity getOrderDetailsFromExternalAPI(@RequestParam Long id) {
        String apiUrl = "http://localhost:5089/orders/getOrders";

        // Create the URL with the query parameter using a UriComponentsBuilder
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("id", id);

        ResponseEntity<VendorEntity> responseEntity = restTemplate.getForEntity(builder.toUriString(),
                VendorEntity.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            VendorEntity vendor = responseEntity.getBody();
            repo.save(vendor);
        }

        return responseEntity.getBody();
    }

}
