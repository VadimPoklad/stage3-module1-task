package com.mjc.school.service.implementation;

import com.mjc.school.repository.implementation.AuthorModel;


public class AuthorConverter {

    public static AuthorModel authorDtoToAuthor(AuthorDto authorDto) {
        if(authorDto == null) throw new IllegalArgumentException("AuthorDto can not be null");
        if(authorDto.getId() == null) return new AuthorModel(authorDto.getName());
        return new AuthorModel(Long.parseLong(authorDto.getId()), authorDto.getName());
    }

    public static AuthorDto authorToAuthorDto(AuthorModel authorModel) {
        if(authorModel == null) throw new IllegalArgumentException("Author can not be null");
        return new AuthorDto(Long.toString(authorModel.getId()), authorModel.getName());
    }
}
