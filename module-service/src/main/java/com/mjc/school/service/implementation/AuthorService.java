package com.mjc.school.service.implementation;

import com.mjc.school.repository.implementation.AuthorModel;
import com.mjc.school.repository.implementation.AuthorDao;
import com.mjc.school.repository.interfaces.ModelDaoInterface;
import com.mjc.school.service.interfaces.ServiceInterface;
import com.mjc.school.service.interfaces.ModelDtoInterface;

import java.util.ArrayList;
import java.util.List;

public class AuthorService implements ServiceInterface {
    ModelDaoInterface authorDao;

    public AuthorService() {
        this.authorDao = new AuthorDao();
    }


    @Override
    public ResponseDto create(RequestDto requestDto) {
        try {
            AuthorModel authorModel = AuthorConverter.authorDtoToAuthor((AuthorDto) requestDto.getInputData());
            AuthorDto authorDto = AuthorConverter.authorToAuthorDto((AuthorModel) authorDao.create(authorModel));
            List<ModelDtoInterface> list = new ArrayList<>();
            list.add(authorDto);
            return ResponseDto
                    .builder()
                    .status("OK")
                    .resultSet(list)
                    .build();
        } catch (Exception e) {
            return buildErrorResponse(e);
        }
    }

    @Override
    public ResponseDto getAll() {
        try {
            List<ModelDtoInterface> list =  authorDao.readAll()
                    .stream()
                    .map(val -> (ModelDtoInterface) AuthorConverter.authorToAuthorDto((AuthorModel) val)).toList();
            return ResponseDto
                    .builder()
                    .status("OK")
                    .resultSet(list)
                    .build();

        } catch (Exception e) {
            return buildErrorResponse(e);
        }
    }

    @Override
    public ResponseDto getById(RequestDto requestDto) {
        try {
            AuthorModel authorModel = (AuthorModel) authorDao
                    .readById(AuthorConverter.authorDtoToAuthor((AuthorDto) requestDto.getInputData()).getId());
            if (authorModel == null) throw new IllegalArgumentException("Author does not exist");
            List<ModelDtoInterface> list = new ArrayList<>();
            list.add(AuthorConverter.authorToAuthorDto(authorModel));

            return ResponseDto
                    .builder()
                    .status("OK")
                    .resultSet(list)
                    .build();

        } catch (Exception e) {
            return buildErrorResponse(e);
        }
    }

    @Override
    public ResponseDto updateById(RequestDto requestDto) {
        try {
            AuthorModel authorModel = (AuthorModel) authorDao
                    .update(AuthorConverter.authorDtoToAuthor((AuthorDto) requestDto.getInputData()));
            if (authorModel == null) throw new IllegalArgumentException("Author does not exist");
            List<ModelDtoInterface> list = new ArrayList<>();
            list.add(AuthorConverter.authorToAuthorDto(authorModel));
            return ResponseDto
                    .builder()
                    .status("OK")
                    .resultSet(list)
                    .build();

        } catch (Exception e) {
            return buildErrorResponse(e);
        }
    }

    @Override
    public ResponseDto removeById(RequestDto requestDto) {
        try {
            authorDao.delete(AuthorConverter.authorDtoToAuthor((AuthorDto) requestDto.getInputData()).getId());

            return ResponseDto
                    .builder()
                    .status("OK")
                    .resultSet(null)
                    .build();

        } catch (Exception e) {
            return buildErrorResponse(e);
        }
    }

    @Override
    public ResponseDto buildErrorResponse(Exception e) {
        return ResponseDto
                .builder()
                .status("Failed")
                .resultSet(null)
                .error(new ErrorDto(e.getMessage()))
                .build();
    }
}

