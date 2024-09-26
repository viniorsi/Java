package com.viniorsi.TravelEase.Domain.Html.Repository;

import com.viniorsi.TravelEase.Domain.Html.Domain.HtmlTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HtmlTemplateRepository extends JpaRepository<HtmlTemplate, Long> {
}
