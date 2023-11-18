package com.mjc.school.repository;

import com.mjc.school.repository.implementation.AuthorModel;
import com.mjc.school.repository.implementation.AuthorDao;
import com.mjc.school.repository.implementation.DataSourceDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

class AuthorDaoTest {

    @BeforeAll
    static void fillData(){
        DataSourceDB.getInstance((Path.of("src/main/resources")));
    }
    @Test
    void create() throws Exception {
        AuthorModel authorModel = new AuthorModel("Bob");
        AuthorDao authorDao = new AuthorDao();
        authorDao.create(authorModel);
        Assertions.assertEquals(authorDao
                .readAll()
                .stream()
                .map(Object::toString)
                .filter(val -> val.contains("Bob"))
                .count(), 1);

    }

    @Test
    void readAll() {
        AuthorDao authorDao = new AuthorDao();
        Assertions.assertNotNull(authorDao.readAll());
    }

    @Test
    void readById() throws Exception {
        AuthorDao authorDao = new AuthorDao();
        AuthorModel authorModel = (AuthorModel) authorDao.readAll().get(0);
        Assertions.assertEquals(authorModel, authorDao.readById(1L));
    }

    @Test
    void update() throws Exception {
        AuthorModel authorModel = new AuthorModel(2L, "NewName");
        AuthorDao authorDao = new AuthorDao();
        authorDao.update(authorModel);
        Assertions.assertEquals(authorDao.readById(2L), authorModel);
    }

    @Test
    void delete() throws Exception {
        AuthorDao authorDao = new AuthorDao();
        authorDao.delete(5L);
        Assertions.assertNull(authorDao.readById(5L));
    }
}