package com.duan.vote.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created on 2019/10/31.
 *
 * @author DuanJiaNing
 */
@Component
@ConfigurationProperties(prefix = "config")
@Data
public class Config {

    private String appId;
    private Topic topic;
    private Comment comment;
    private Search search;

    @Data
    public static class Search {
        private int wordLimit;
    }

    @Data
    public static class Topic {
        private int wordLimit;
        private int notesLimit;
    }

    @Data
    public static class Comment {
        private int wordLimit;
    }
}
