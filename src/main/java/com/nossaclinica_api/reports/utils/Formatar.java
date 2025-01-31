package com.nossaclinica_api.reports.utils;

import com.nossaclinica_api.models.dtos.BaseDeCalculo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class Formatar {
    private static final String FORMAT_HORA = "%S:%S";
    public static String string(String str) {
        var lower = str.toLowerCase();
        String[] items = lower.split(" ");
        var strFormatado = "";
        for(var item : items) {
            strFormatado += item.substring(0, 1).toUpperCase().concat(item.substring(1)) + " ";
        }
        return strFormatado.trim();
    }

    public static String date(LocalDate data) {
        if(data == null) {
            return null;
        }
        DateTimeFormatter formatBR = DateTimeFormatter.ofPattern("dd/MM/uuuu")
                .withResolverStyle(ResolverStyle.STRICT);

        return data.format(formatBR);
    }

    public static String hora(LocalDateTime now) {
        return FORMAT_HORA.formatted(now.getHour(), now.getMinute());
    }


}
