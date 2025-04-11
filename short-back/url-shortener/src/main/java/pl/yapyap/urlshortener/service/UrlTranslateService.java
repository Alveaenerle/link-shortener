package pl.yapyap.urlshortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.yapyap.urlshortener.repository.UrlTranslateRepository;

@Service
public class UrlTranslateService {
    private UrlTranslateRepository urlTranslateRepository;

    @Autowired
    public UrlTranslateService(
            UrlTranslateRepository urlTranslateRepository
    ) {
        this.urlTranslateRepository = urlTranslateRepository;
    }
}
