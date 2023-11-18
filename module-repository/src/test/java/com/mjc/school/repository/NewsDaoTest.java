package com.mjc.school.repository;

import com.mjc.school.repository.implementation.DataSourceDB;
import com.mjc.school.repository.implementation.NewsModel;
import com.mjc.school.repository.implementation.NewsDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;


class NewsDaoTest {
    @BeforeAll
    static void fillData(){
        DataSourceDB.getInstance((Path.of("src/main/resources")));
    }
    @Test
    void create() throws Exception {
        NewsModel newsModel = new NewsModel.Builder()
                .title("NewTitle")
                .content("NewContent")
                .authorId(3L)
                .build();
        NewsDao newsDao = new NewsDao();
        newsDao.create(newsModel);
        Assertions.assertEquals(newsDao
                .readAll()
                .stream()
                .map(Object::toString)
                .filter(val -> val.contains("NewTitle"))
                .count(), 1);

    }

    @Test
    void readAll() {
        NewsDao newsDao = new NewsDao();
        Assertions.assertNotNull(newsDao.readAll());
    }

    @Test
    void readById() throws Exception {
        NewsDao newsDao = new NewsDao();
        NewsModel newsModel = (NewsModel) newsDao.readAll().get(0);
        Assertions.assertEquals(newsModel, newsDao.readById(1L));
    }

    @Test
    void update() throws Exception {
        NewsDao newsDao = new NewsDao();
        NewsModel newsModel = new NewsModel.Builder()
                .id(3L)
                .title("NewTitle")
                .content("NewContent")
                .authorId(3L)
                .build();
        newsDao.update(newsModel);
        NewsModel newNewsModel = (NewsModel) newsDao.readById(3L);
        Assertions.assertEquals(newNewsModel.getTitle(), newsModel.getTitle());
        Assertions.assertEquals(newNewsModel.getContent(), newsModel.getContent());
        Assertions.assertEquals(newNewsModel.getAuthorId(), newsModel.getAuthorId());
    }

    @Test
    void delete() throws Exception {
        NewsDao newsDao = new NewsDao();
        newsDao.delete(5L);
        Assertions.assertNull(newsDao.readById(5L));
    }
}