package com.system.design.urlshortner.repositories;

import com.system.design.urlshortner.models.UrlData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlShortnerRepository extends JpaRepository<UrlData,Long> {

    public UrlData findByShortenUrl(String shortLink);
}
