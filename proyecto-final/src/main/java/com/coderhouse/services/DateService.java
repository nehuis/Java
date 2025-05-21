package com.coderhouse.services;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

@Service
public class DateService {

    public Date getDateFromApi() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(
                "http://worldtimeapi.org/api/timezone/America/Argentina/Buenos_Aires",
                String.class
            );

            System.out.println("[DateService] Respuesta cruda de la API: " + response);

            JSONObject json = new JSONObject(response);

            if (!json.has("datetime")) {
                System.out.println("[DateService] El campo 'datetime' no está presente en la respuesta.");
                return getLocalDate();
            }

            String dateStr = json.getString("datetime");
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateStr);

            System.out.println("[DateService] Fecha obtenida de la API: " + offsetDateTime);

            return Date.from(offsetDateTime.toInstant());

        } catch (Exception e) {
            System.out.println("[DateService] Error al obtener la fecha desde la API, usando fecha local.");
            e.printStackTrace(); // Imprime el error completo para debugging
            return getLocalDate();
        }
    }

    private Date getLocalDate() {
        Date localDate = Date.from(java.time.LocalDateTime.now()
            .atZone(ZoneId.of("America/Argentina/Buenos_Aires"))
            .toInstant());
        System.out.println("[DateService] Fecha local usada: " + localDate);
        return localDate;
    }
}
