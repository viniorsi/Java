package com.viniorsi.TravelEase.Service.html;

import com.viniorsi.TravelEase.Domain.Html.HtmlTemplate;
import com.viniorsi.TravelEase.Repository.html.HtmlTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HtmlTemplateService {

    @Autowired
    private HtmlTemplateRepository htmlTemplateRepository;

    public String getTemplateContent(Long id) {
        HtmlTemplate template = htmlTemplateRepository.findById(id).orElse(null);
        if (template != null) {
            return template.getContent();
        } else {
            throw new RuntimeException("Template not found");
        }
    }

}
