package pl.yapyap.urlshortener.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.yapyap.urlshortener.entity.UrlTranslate;
import pl.yapyap.urlshortener.repository.UrlTranslateRepository;
import pl.yapyap.urlshortener.utils.UrlShortener;


@Service
public class UrlTranslateService {
    private final UrlTranslateRepository urlTranslateRepository;

    @Value("${short-url-length}")
    private int shortUrlLen;

    @Value("${max-shortening-tries}")
    private int maxHashingTries;


    @Autowired
    public UrlTranslateService(
            UrlTranslateRepository urlTranslateRepository
    ) {
        this.urlTranslateRepository = urlTranslateRepository;
    }

    public String createUrlMapping(String longUrl) {
        if (!isUrlValid(longUrl)) return "";
        String shortUrl = generateShortUrl();
        if (shortUrl.isEmpty()) return "";

        UrlTranslate urlTranslate = new UrlTranslate();
        urlTranslate.setLongUrl(longUrl);
        urlTranslate.setShortUrl(shortUrl);
        urlTranslateRepository.save(urlTranslate);
        return shortUrl;
    }

    public String getLongUrl(String shortUrl) {
        if (shortUrl == null || shortUrl.isBlank()) return "";
        UrlTranslate urlTranslate = urlTranslateRepository.findUrlTranslateByShortUrl(shortUrl);
        return urlTranslate != null && urlTranslate.getLongUrl() != null
                ? urlTranslate.getLongUrl()
                : "";
    }

    private String generateShortUrl(){
        for (int i = 0; i < maxHashingTries; i++) {
            String shortUrl = UrlShortener.generateBase62String(shortUrlLen);
            if (!urlTranslateRepository.existsUrlTranslateByShortUrl(shortUrl)) {
                return shortUrl;
            }
        }
        return "";
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
