package com.mjc.school.service;

import com.mjc.school.service.implementation.AuthorService;
import com.mjc.school.service.implementation.AuthorDto;
import com.mjc.school.service.implementation.RequestDto;
import com.mjc.school.service.implementation.ResponseDto;
import com.mjc.school.service.interfaces.ModelDtoInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;


class AuthorServiceTest {
    @BeforeAll
    static void fillData(){
        AuthorService authorService = new AuthorService();
        AuthorDto authorDto1 = AuthorDto.builder().name("Q").build();
        AuthorDto authorDto2 = AuthorDto.builder().name("W").build();
        AuthorDto authorDto3 = AuthorDto.builder().name("E").build();
        AuthorDto authorDto4 = AuthorDto.builder().name("R").build();
        authorService.create(RequestDto.builder().inputData(authorDto1).build());
        authorService.create(RequestDto.builder().inputData(authorDto2).build());
        authorService.create(RequestDto.builder().inputData(authorDto3).build());
        authorService.create(RequestDto.builder().inputData(authorDto4).build());
    }
    @Test
    void create() {
        AuthorDto authorDto = AuthorDto.builder().name("Bob").build();
        AuthorService authorService = new AuthorService();
        List<ModelDtoInterface> list = authorService.create(RequestDto
                        .builder()
                        .inputData(authorDto)
                        .build())
                .getResultSet();
        AuthorDto newAuthor = (AuthorDto) list.get(0);
        Assertions.assertEquals(authorDto.getName(), newAuthor.getName());
    }

    @Test
    void getAll() {
        AuthorService authorService = new AuthorService();
        List<ModelDtoInterface> list = authorService.getAll().getResultSet();
        Assertions.assertNotNull(list);
    }

    @Test
    void getById() {
        AuthorService authorService = new AuthorService();
        AuthorDto authorDto = AuthorDto.builder().id("1").build();

        List<ModelDtoInterface> list = authorService
                .getById(RequestDto
                        .builder()
                        .inputData(authorDto)
                        .build())
                .getResultSet();

        Assertions.assertEquals(list.get(0).toString(),"Author{id='1', name='Q'}");
    }

    @Test
    void updateById() {
        AuthorService authorService = new AuthorService();
        AuthorDto authorDto = new AuthorDto("2", "NewName");

        List<ModelDtoInterface> list = authorService
                .updateById(RequestDto
                        .builder()
                        .inputData(authorDto)
                        .build())
                .getResultSet();

        Assertions.assertEquals(authorDto.toString(), list.get(0).toString());
    }

    @Test
    void removeById() {
        AuthorService authorService = new AuthorService();
        AuthorDto authorDto = AuthorDto.builder().id("3").build();


        authorService.removeById(RequestDto
                        .builder()
                        .inputData(authorDto)
                        .build());

        List<ModelDtoInterface> list = authorService
                .getById(RequestDto
                        .builder()
                        .inputData(authorDto)
                        .build())
                .getResultSet();

        Assertions.assertNull(list);
    }

    @Test
    void buildErrorResponse() {
        AuthorService authorService = new AuthorService();
        RequestDto requestDto = new RequestDto();
        AuthorDto authorDto = AuthorDto.builder().id("55").build();
        requestDto.setInputData(authorDto);
        ResponseDto responseDto = authorService.getById(requestDto);

        Assertions.assertEquals("Failed", responseDto.getStatus());
    }
}