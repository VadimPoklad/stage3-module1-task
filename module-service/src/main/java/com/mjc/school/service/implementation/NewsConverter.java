package com.mjc.school.service.implementation;

import com.mjc.school.repository.implementation.NewsModel;

public class NewsConverter {

    public static NewsModel newsDtoToNews(NewsDto newsDto) {
        if(newsDto == null) throw new IllegalArgumentException("NewsDto can not be null");
        if(newsDto.getId() == null){
            return new NewsModel.Builder()
                    .title(newsDto.getTitle())
                    .content(newsDto.getContent())
                    .authorId(Long.valueOf(newsDto.getAuthorId()))
                    .build();
        }
        return new NewsModel.Builder()
                .id(Long.valueOf(newsDto.getId()))
                .title(newsDto.getTitle())
                .content(newsDto.getContent())
                .lastUpdateDate(newsDto.getLastUpdateDate())
                .authorId(Long.valueOf(newsDto.getAuthorId()))
                .build();
    }

    public static NewsDto newsToNewsDto(NewsModel newsModel) {
        if(newsModel == null) throw new IllegalArgumentException("News can not be null");
        return NewsDto.builder()
                .id(String.valueOf(newsModel.getId()))
                .title(newsModel.getTitle())
                .content(newsModel.getContent())
                .createDate(newsModel.getCreateDate())
                .lastUpdateDate(newsModel.getLastUpdateDate())
                .authorId(String.valueOf(newsModel.getAuthorId()))
                .build();
    }
}
