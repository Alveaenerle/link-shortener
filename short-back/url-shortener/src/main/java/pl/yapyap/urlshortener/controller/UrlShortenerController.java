package pl.yapyap.urlshortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.yapyap.urlshortener.service.UrlTranslateService;

@CrossOrigin
@Controller
public class UrlShortenerController {

    private final UrlTranslateService urlTranslateService;

    @Autowired
    public UrlShortenerController(
            UrlTranslateService urlTranslateService
    ) {
        this.urlTranslateService = urlTranslateService;
    }

    @GetMapping("/{shortUrl}")
    public String redirect(@PathVariable String shortUrl) {
        StringBuilder sb = new StringBuilder();
        String longUrl = urlTranslateService.getLongUrl(shortUrl);
        if (longUrl.isEmpty()) return "";
        sb.append("redirect:");
        sb.append(longUrl);
        return sb.toString();
    }
}
