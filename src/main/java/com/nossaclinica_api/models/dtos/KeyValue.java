package com.nossaclinica_api.models.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KeyValue {
    private Integer key;
    private String value;
}
