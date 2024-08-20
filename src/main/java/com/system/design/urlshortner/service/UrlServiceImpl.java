package com.system.design.urlshortner.service;

import com.system.design.urlshortner.UrlServiceUtils;
import com.system.design.urlshortner.models.UrlData;
import com.system.design.urlshortner.models.UrlRequest;
import com.system.design.urlshortner.repositories.UrlShortnerRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UrlServiceImpl implements UrlService {

    @Autowired
    UrlShortnerRepository urlShortnerRepository;

    @Override
    public UrlData getShortenUrl(UrlRequest urlRequest) {

        if (StringUtils.isNotBlank(urlRequest.getUrl())
                && StringUtils.isNotEmpty(urlRequest.getUrl())) {

            UrlData urlData = new UrlData();
            urlData.setDateCreated(LocalDateTime.now());
            urlData.setShortenUrl(new UrlServiceUtils().getEncodedUrl(urlRequest.getUrl()));
            urlData.setLongUrl(urlRequest.getUrl());
            urlData.setDateOfExpiration(new UrlServiceUtils().getExpirationDate(urlRequest.getExpirationDate(),
                    urlData.getDateCreated()));
            return urlData;

        }
        return null;
    }

    @Override
    public UrlData saveShortenUrl(UrlData urlData) {
        return urlShortnerRepository.save(urlData);
    }

    @Override
    public UrlData getEncodedUrl(String url) {
        return urlShortnerRepository.findByShortenUrl(url);
    }

    @Override
    public void deleteShortenUrl(UrlData urlData) {
        urlShortnerRepository.delete(urlData);
    }
}
