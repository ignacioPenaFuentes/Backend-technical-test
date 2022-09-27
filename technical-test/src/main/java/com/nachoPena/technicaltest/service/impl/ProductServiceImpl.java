package com.nachoPena.technicaltest.service.impl;

import com.nachoPena.technicaltest.service.ProductService;
import org.openapitools.client.api.DefaultApi;
import org.openapitools.model.ProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    DefaultApi defaultApi;

    @Override
    public Set<ProductDetail> getProductsSimilar(String productId) throws RestClientException {
        Set<String> productsSimilar = defaultApi.getProductSimilarids(productId);

        return productsSimilar.stream()
                .map(item -> {
                    return getProductDetail(item);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    private ProductDetail getProductDetail(String item) throws RestClientException {
        try {
            org.openapitools.client.model.ProductDetail productDetailClient = defaultApi.getProductProductId(item);

            ProductDetail productDetail = new ProductDetail();
            productDetail.setId(productDetailClient.getId());
            productDetail.setName(productDetailClient.getName());
            productDetail.setPrice(productDetailClient.getPrice());
            productDetail.setAvailability(productDetailClient.getAvailability());
            return productDetail;
        } catch (RestClientException e) {
            return null;
        }
    }
}
