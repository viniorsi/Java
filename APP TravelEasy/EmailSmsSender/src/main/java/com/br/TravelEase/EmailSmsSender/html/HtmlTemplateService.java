package com.br.TravelEase.EmailSmsSender.html;

import com.br.TravelEase.EmailSmsSender.html.Domain.HtmlTemplate;
import com.br.TravelEase.EmailSmsSender.html.Repository.HtmlTemplateRepository;

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
