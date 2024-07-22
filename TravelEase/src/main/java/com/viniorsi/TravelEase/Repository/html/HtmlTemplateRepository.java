package com.viniorsi.TravelEase.Repository.html;

import com.viniorsi.TravelEase.Domain.Html.HtmlTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HtmlTemplateRepository extends JpaRepository<HtmlTemplate, Long> {
}
