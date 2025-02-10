package com.br.TravelEase.EmailSmsSender.Listener

import lombok.Getter
import lombok.Setter
import java.io.Serializable

@Getter
@Setter
data class EmailMessage(
    val to: String,
    val subject: String,
    val body: String,
    val attachments: List<String>?
) : Serializable