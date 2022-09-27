package com.nachoPena.technicaltest.service;

import org.openapitools.model.ProductDetail;
import org.springframework.web.client.RestClientException;

import java.util.Set;

public interface ProductService {
    public Set<ProductDetail> getProductsSimilar(String productId) throws RestClientException;
}
