package pl.yapyap.urlshortener.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.yapyap.urlshortener.entity.UrlTranslate;
import pl.yapyap.urlshortener.repository.UrlTranslateRepository;
import pl.yapyap.urlshortener.utils.UrlShortener;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UrlTranslateService {
    private UrlTranslateRepository urlTranslateRepository;

    @Value("${max-hashing-tries}")
    private int maxHashingTries;

    @Value("${url-append-const}")
    private String urlAppendConst;

    @Autowired
    public UrlTranslateService(
            UrlTranslateRepository urlTranslateRepository
    ) {
        this.urlTranslateRepository = urlTranslateRepository;
    }

    public String createUrlMapping(String longUrl) {
        if (!isUrlValid(longUrl)) return "";
        String shortUrl = generateShortUrl(longUrl);
        if (shortUrl.isEmpty()) return "";

        UrlTranslate urlTranslate = new UrlTranslate();
        urlTranslate.setLongUrl(longUrl);
        urlTranslate.setShortUrl(shortUrl);
        urlTranslateRepository.save(urlTranslate);
        return shortUrl;
    }

    private String generateShortUrl(String longUrl){
        for (int i = 0; i < maxHashingTries; i++) {
            String shortUrl = UrlShortener.hashUrlToShort(longUrl);
            if (!urlTranslateRepository.existsUrlTranslateByShortUrl(shortUrl)) {
                return shortUrl;
            }
            longUrl = longUrl.concat(urlAppendConst);
        }
        return "";
    }

    public long hashUrlToLong(String url) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(url.getBytes(StandardCharsets.UTF_8));
            // Take first 8 bytes = 64 bits = long
            ByteBuffer buffer = ByteBuffer.wrap(hash);
            return Math.abs(buffer.getLong());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    private boolean isUrlValid(String url) {
        return  url != null && !url.isEmpty() && isUrlHeaderValid(url);
    }

    private boolean isUrlHeaderValid(String url) {
        String header = getHeaderFromUrl(url);
        return header.equals("http://") || header.equals("https://");
    }

    private @NotNull String getHeaderFromUrl(@NotNull String url) {
        // https://example.com
        // We get first / and then go to the second slash
        int headerEndInd = url.indexOf('/') + 1;
        return url.substring(0, headerEndInd + 1);
    }

}
