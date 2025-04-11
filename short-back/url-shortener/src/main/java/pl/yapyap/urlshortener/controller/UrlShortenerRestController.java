package pl.yapyap.urlshortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.yapyap.urlshortener.service.UrlTranslateService;

@CrossOrigin
@RestController
public class UrlShortenerRestController {
    private final UrlTranslateService urlTranslateService;

    @Autowired
    public UrlShortenerRestController(
            UrlTranslateService urlTranslateService
    ) {
        this.urlTranslateService = urlTranslateService;
    }


    @PostMapping("/create")
    public String createShortenUrl(@RequestBody String longUrl) {
        return urlTranslateService.createUrlMapping(longUrl);
    }

}
