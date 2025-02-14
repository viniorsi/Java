package com.br.TravelEase.EmailSmsSender.html.Domain;

import jakarta.persistence.*;
import lombok.*;


@Table(name = "html_templates")
@Entity(name = "htmlTemplate")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class HtmlTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String content;
}
