package com.system.design.urlshortner.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import java.time.LocalDateTime;

@Entity
public class UrlData {

    @Id
    private long id;
    @Lob
    private String longUrl;

    private String shortenUrl;

    private LocalDateTime dateCreated;

    private LocalDateTime dateOfExpiration;

    public UrlData() {
    }

    public UrlData(long id, String longUrl, String shortenUrl, LocalDateTime dateCreated, LocalDateTime dateOfExpiration) {
        this.id = id;
        this.longUrl = longUrl;
        this.shortenUrl = shortenUrl;
        this.dateCreated = dateCreated;
        this.dateOfExpiration = dateOfExpiration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortenUrl() {
        return shortenUrl;
    }

    public void setShortenUrl(String shortenUrl) {
        this.shortenUrl = shortenUrl;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateOfExpiration() {
        return dateOfExpiration;
    }

    public void setDateOfExpiration(LocalDateTime dateOfExpiration) {
        this.dateOfExpiration = dateOfExpiration;
    }

    @Override
    public String toString() {
        return "UrlData{" +
                "id=" + id +
                ", providedUrl='" + longUrl + '\'' +
                ", shortenUrl='" + shortenUrl + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateOfExpiration=" + dateOfExpiration +
                '}';
    }
}
