package com.study.oa.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ConfigSystem {
    @Value(value = "${config.systemName}")
    private String systemName;
}
