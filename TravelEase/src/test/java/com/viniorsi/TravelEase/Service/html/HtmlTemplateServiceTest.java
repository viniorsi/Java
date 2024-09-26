package com.viniorsi.TravelEase.Service.html;

import com.viniorsi.TravelEase.Domain.Html.Domain.HtmlTemplate;
import com.viniorsi.TravelEase.Domain.Html.Repository.HtmlTemplateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HtmlTemplateServiceTest {

    @InjectMocks
    HtmlTemplateService htmlTemplateService;

    @Mock
    private HtmlTemplateRepository htmlTemplateRepository;

    @Test
    public void GetHtmlTemplateTest() throws Exception {

        HtmlTemplate template = new HtmlTemplate();
        template.setId(1L);
        template.setContent("Teste");

        when(htmlTemplateRepository.findById(1L)).thenReturn(Optional.of(template));

        String content = htmlTemplateService.getTemplateContent(1L);

        assertEquals("Teste", content);


    }

}