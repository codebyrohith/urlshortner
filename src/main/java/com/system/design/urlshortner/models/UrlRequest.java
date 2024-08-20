package com.system.design.urlshortner.models;

public class UrlRequest {

    private String url;
    private String expirationDate;

    public UrlRequest() {
    }
    public UrlRequest(String url, String expirationDate) {
        this.url = url;
        this.expirationDate = expirationDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "UrlRequest{" +
                "url='" + url + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                '}';
    }
}
