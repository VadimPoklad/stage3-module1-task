package com.mjc.school.repository.implementation;

import com.mjc.school.repository.interfaces.ModelInterface;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Objects;


@Data
public class NewsModel implements ModelInterface {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private Long authorId;

    public static class Builder{
        private Long id;
        private String title;
        private String content;
        private LocalDateTime createDate;
        private LocalDateTime lastUpdateDate;
        private Long authorId;

        public Builder id(Long val){
            this.id = val;
            return this;
        }

        public Builder title(String val){
            if(val.length() < 5 || val.length() > 30)
                throw new IllegalArgumentException("Title field should have length of value from 5 to 30");
            title = val;
            return this;
        }
        public Builder content(String val){
            if(val.length() < 5 || val.length() > 255)
                throw new IllegalArgumentException("Content field should have length of value from 5 to 255");
            content = val;
            return this;
        }
        public Builder createDate(LocalDateTime val){
            createDate = val;
            return this;
        }
        public Builder lastUpdateDate(LocalDateTime val){
            lastUpdateDate = val;
            return this;
        }

        public Builder authorId(Long val){
            this.authorId = val;
            return this;
        }



        public NewsModel build(){
            return new NewsModel(this);
        }
    }

    public NewsModel(Builder builder) {
        if(builder.id == null) this.id = generateId();
        else this.id = builder.id;
        this.title = builder.title;
        this.content = builder.content;

        if(builder.createDate == null) this.createDate = LocalDateTime.now();
        else this.createDate = builder.createDate;

        this.lastUpdateDate = Objects.requireNonNullElseGet(builder.lastUpdateDate, LocalDateTime::now);

        this.authorId = builder.authorId;
    }


    @Override
    public Long generateId() {
        DataSourceDB database = DataSourceDB.getInstance();
        return database.getMaxID(NewsModel.class)+1;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "News:" + "\n"+
                "id:" + id + "\n"+
                "news:" + title + "\n"+
                "content:" + content + "\n"+
                "createDate:" + createDate.toString() + "\n"+
                "lastUpdateDate:" + lastUpdateDate.toString() + "\n"+
                "authorId:" + authorId + "\n";
    }
}