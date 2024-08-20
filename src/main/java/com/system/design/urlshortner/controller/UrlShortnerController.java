package com.system.design.urlshortner.controller;

import com.system.design.urlshortner.models.UrlData;
import com.system.design.urlshortner.models.UrlErrorResponse;
import com.system.design.urlshortner.models.UrlRequest;
import com.system.design.urlshortner.models.UrlResponse;
import com.system.design.urlshortner.service.UrlServiceImpl;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
public class UrlShortnerController {

    @Autowired
    private UrlServiceImpl urlService;

    @PostMapping("/generateShortUrl")
    public ResponseEntity<?> generateShortLink(@RequestBody UrlRequest urlRequest){

        UrlData urlData = urlService.getShortenUrl(urlRequest);
        if(urlData!=null){
            urlData = urlService.saveShortenUrl(urlData);
            UrlResponse urlResponse = new UrlResponse();
            urlResponse.setLongUrl(urlData.getLongUrl());
            urlResponse.setShortenUrl(urlData.getShortenUrl());
            urlResponse.setExpirationDate(urlData.getDateOfExpiration());
            return new ResponseEntity<UrlResponse>(urlResponse, HttpStatus.OK);
        }
        UrlErrorResponse errorResponse = new UrlErrorResponse("404",
                "There was an error processing your request, please try again later after sometime");
        return new ResponseEntity<UrlErrorResponse>(errorResponse,HttpStatus.OK);
    }


    @GetMapping("/{shortLink}")
    public ResponseEntity<?> getOriginalUrl(@PathVariable String shortLink, HttpServletResponse response) throws IOException {

        if(StringUtils.isNotEmpty(shortLink)){
            UrlData urlData = urlService.getEncodedUrl(shortLink);
            if (urlData !=null) {
                if(urlData.getDateOfExpiration().isBefore(LocalDateTime.now())){
                    System.out.println(urlData.getDateOfExpiration());
                    urlService.deleteShortenUrl(urlData);
                    UrlErrorResponse errorResponse = new UrlErrorResponse("200",
                            "Short url has been expired, please create a new short url");
                    return new ResponseEntity<UrlErrorResponse>(errorResponse,HttpStatus.OK);
                }
                response.sendRedirect(urlData.getLongUrl());
                return null;
            }

            UrlErrorResponse errorResponse = new UrlErrorResponse("404",
                    "Unable to find the original url for the give short url, please try with valid short url again");
            return new ResponseEntity<UrlErrorResponse>(errorResponse,HttpStatus.OK);
        }

        UrlErrorResponse errorResponse = new UrlErrorResponse("404",
                "Invalid url");
        return new ResponseEntity<UrlErrorResponse>(errorResponse,HttpStatus.OK);
    }

}
