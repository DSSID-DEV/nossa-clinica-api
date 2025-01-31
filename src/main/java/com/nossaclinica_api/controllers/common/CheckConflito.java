package com.nossaclinica_api.controllers.common;

import lombok.Data;

@Data
public class CheckConflito {
    private boolean existe;
    private String conflito;
    private String msgLog;
}
