package com.nachoPena.technicaltest.controller;

import com.nachoPena.technicaltest.service.ProductService;
import org.openapitools.api.ProductApi;
import org.openapitools.model.ProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestClientException;

import java.util.Set;

@Controller
public class ProductController implements ProductApi {

    @Autowired
    public ProductService productService;

    @Override
    public ResponseEntity<Set<ProductDetail>> getProductSimilar(String productId) {
        try {
            return new ResponseEntity<Set<ProductDetail>>(productService.getProductsSimilar(productId), HttpStatus.OK);
        } catch (RestClientException e) {
            return new ResponseEntity("Product Not found", HttpStatus.NOT_FOUND);
        }
    }
}
