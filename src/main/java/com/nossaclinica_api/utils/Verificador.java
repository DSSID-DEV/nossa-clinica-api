package com.nossaclinica_api.utils;

import org.apache.commons.lang3.StringUtils;

public class Verificador {

    public static boolean isNotBlank(String str) {
        return str != null && StringUtils.isNotBlank(str);
    }
}
