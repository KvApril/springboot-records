package com.kv.aboutconfig.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.UUID;

@Configuration
@ConfigurationProperties(prefix = "kv.random")
@PropertySource("classpath:random.properties")
public class ConfigRandomBean {
    private Integer age;
    private UUID uuid;
    private Integer range;
    private Integer less;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public Integer getLess() {
        return less;
    }

    public void setLess(Integer less) {
        this.less = less;
    }

    @Override
    public String toString() {
        return "ConfigRandomBean{" +
                "age=" + age +
                ", uuid=" + uuid +
                ", range=" + range +
                ", less=" + less +
                '}';
    }
}
