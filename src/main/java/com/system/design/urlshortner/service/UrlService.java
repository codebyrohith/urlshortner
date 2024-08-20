package com.system.design.urlshortner.service;

import com.system.design.urlshortner.models.UrlData;
import com.system.design.urlshortner.models.UrlRequest;
import org.springframework.stereotype.Service;

@Service
public interface UrlService {

    public UrlData getShortenUrl(UrlRequest urlRequest);

    public UrlData saveShortenUrl(UrlData urlData);

    public UrlData getEncodedUrl(String url);

    public void deleteShortenUrl(UrlData urlData);
}
