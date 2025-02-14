package com.br.TravelEase.EmailSmsSender.html.Repository;

import com.br.TravelEase.EmailSmsSender.html.Domain.HtmlTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HtmlTemplateRepository extends JpaRepository<HtmlTemplate, Long> {
}
