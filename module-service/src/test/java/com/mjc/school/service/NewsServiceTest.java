package com.mjc.school.service;

import com.mjc.school.service.implementation.NewsDto;
import com.mjc.school.service.implementation.RequestDto;
import com.mjc.school.service.implementation.ResponseDto;
import com.mjc.school.service.implementation.NewsService;
import com.mjc.school.service.interfaces.ModelDtoInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.List;

class NewsServiceTest {
    @BeforeAll
    static void fillData(){
        AuthorServiceTest.fillData();
        NewsService newsService = new NewsService();
        NewsDto newsDto1 = NewsDto.builder().title("Qwert1").content("Qwert1").authorId("1").build();
        NewsDto newsDto2 = NewsDto.builder().title("Qwert2").content("Qwert2").authorId("1").build();
        NewsDto newsDto3 = NewsDto.builder().title("Qwert3").content("Qwert3").authorId("1").build();
        newsService.create(RequestDto.builder().inputData(newsDto1).build());
        newsService.create(RequestDto.builder().inputData(newsDto2).build());
        newsService.create(RequestDto.builder().inputData(newsDto3).build());


    }
    @Test
    void create() {
        NewsDto newsDto = NewsDto.builder().title("NewTitle").content("NewContent").authorId("1").build();
        NewsService newsService = new NewsService();
        List<ModelDtoInterface> list = newsService.create(RequestDto
                        .builder()
                        .inputData(newsDto)
                        .build())
                .getResultSet();
        NewsDto newNews = (NewsDto) list.get(0);
        Assertions.assertEquals(newNews.getTitle(), newsDto.getTitle());
    }

    @Test
    void getAll() {
        NewsService newsService = new NewsService();
        List<ModelDtoInterface> list = newsService.getAll().getResultSet();
        Assertions.assertNotNull(list);
    }

    @Test
    void getById() {
        NewsDto newsDto = NewsDto.builder().id("1").build();
        NewsService newsService = new NewsService();

        List<ModelDtoInterface> list = newsService
                .getById(RequestDto
                        .builder()
                        .inputData(newsDto)
                        .build())
                .getResultSet();

        Assertions.assertTrue(list.get(0).toString().contains("id='1', title='Qwert1'"));
    }

    @Test
    void updateById() {
        NewsDto newsDto = NewsDto.builder().id("2").title("NewTitle").content("NewContent").authorId("1").build();
        NewsService newsService = new NewsService();

        List<ModelDtoInterface> list = newsService
                .updateById(RequestDto
                        .builder()
                        .inputData(newsDto)
                        .build())
                .getResultSet();

        Assertions.assertTrue(list.get(0).toString().contains("id='2', title='NewTitle', content='NewContent'"));
    }

    @Test
    void removeById() {
        NewsDto newsDto = NewsDto.builder().id("3").build();
        NewsService newsService = new NewsService();

        newsService.removeById(RequestDto
                .builder()
                .inputData(newsDto)
                .build());

        List<ModelDtoInterface> list = newsService
                .getById(RequestDto
                        .builder()
                        .inputData(newsDto)
                        .build())
                .getResultSet();

        Assertions.assertNull(list);
    }

    @Test
    void buildErrorResponse() {
        NewsDto newsDto = NewsDto.builder().id("2").title("New").content("NewContent").authorId("1").build();
        NewsService newsService = new NewsService();

        ResponseDto responseDto = newsService
                .updateById(RequestDto
                        .builder()
                        .inputData(newsDto)
                        .build());

        Assertions.assertEquals("Failed", responseDto.getStatus());
    }
}