package pl.yapyap.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.yapyap.urlshortener.entity.UrlTranslate;

import java.time.LocalDateTime;

@Repository
public interface UrlTranslateRepository extends JpaRepository<UrlTranslate, Integer> {
    boolean existsUrlTranslateByShortUrl(String shortUrl);

    UrlTranslate findUrlTranslateByShortUrl(String shortUrl);
}
