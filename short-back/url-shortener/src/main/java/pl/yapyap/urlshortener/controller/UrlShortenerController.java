package pl.yapyap.urlshortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.yapyap.urlshortener.service.UrlTranslateService;

@RestController
public class UrlShortenerController {

    private final UrlTranslateService urlTranslateService;

    @Autowired
    public UrlShortenerController(
            UrlTranslateService urlTranslateService
    ) {
        this.urlTranslateService = urlTranslateService;
    }

    @PostMapping("/create")
    public String createShortenUrl(@RequestBody String longUrl) {
        return urlTranslateService.createUrlMapping(longUrl);
    }
}
