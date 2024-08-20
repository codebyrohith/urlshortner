package com.system.design.urlshortner;

import com.google.common.hash.Hashing;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class UrlServiceUtils {

    public String getEncodedUrl(String longUrl) {
        return StringUtils.isNotEmpty(longUrl)
                ? Hashing.murmur3_32_fixed().hashString(LocalDateTime.now().toString()
                , StandardCharsets.UTF_8).toString() : "";

    }

    public LocalDateTime getExpirationDate(String expirationDate, LocalDateTime dateOfCreation) {
        return StringUtils.isNotEmpty(expirationDate)
                ? LocalDateTime.parse(expirationDate).plusSeconds(60)
                : dateOfCreation.plusSeconds(60);
    }
}
