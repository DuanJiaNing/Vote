package com.duan.vote.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created on 2019/10/31.
 *
 * @author DuanJiaNing
 */
@Component
@ConfigurationProperties(prefix = "config")
public class Config {

    private Topic topic;
    private Comment comment;
    private Manage manage;
    private Session session;
    private GRpc grpc;

    public GRpc getGrpc() {
        return grpc;
    }

    public void setGrpc(GRpc grpc) {
        this.grpc = grpc;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Manage getManage() {
        return manage;
    }

    public void setManage(Manage manage) {
        this.manage = manage;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public static class Session {
        private String host;
        private int port;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }
    }

    public static class Topic {
        private int wordLimit;
        private int notesLimit;

        public int getWordLimit() {
            return wordLimit;
        }

        public void setWordLimit(int wordLimit) {
            this.wordLimit = wordLimit;
        }

        public int getNotesLimit() {
            return notesLimit;
        }

        public void setNotesLimit(int notesLimit) {
            this.notesLimit = notesLimit;
        }
    }

    public static class Comment {
        private int wordLimit;

        public int getWordLimit() {
            return wordLimit;
        }

        public void setWordLimit(int wordLimit) {
            this.wordLimit = wordLimit;
        }
    }

    public static class Manage {
        private int proposalWordLimit;

        public int getProposalWordLimit() {
            return proposalWordLimit;
        }

        public void setProposalWordLimit(int proposalWordLimit) {
            this.proposalWordLimit = proposalWordLimit;
        }
    }

    public static class GRpc {
        private String host;
        private Integer port;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }
    }

}
