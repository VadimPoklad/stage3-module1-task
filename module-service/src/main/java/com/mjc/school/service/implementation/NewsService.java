package com.mjc.school.service.implementation;

import com.mjc.school.repository.implementation.NewsModel;
import com.mjc.school.repository.implementation.NewsDao;
import com.mjc.school.repository.interfaces.ModelDaoInterface;
import com.mjc.school.service.interfaces.ServiceInterface;
import com.mjc.school.service.interfaces.ModelDtoInterface;

import java.util.ArrayList;
import java.util.List;

public class NewsService implements ServiceInterface {
    ModelDaoInterface newsDao;

    public NewsService() {
        this.newsDao = new NewsDao();
    }

    @Override
    public ResponseDto create(RequestDto requestDto) {
        try {
            NewsModel newsModel = NewsConverter.newsDtoToNews((NewsDto) requestDto.getInputData());
            NewsDto newsDto = NewsConverter.newsToNewsDto((NewsModel) newsDao.create(newsModel));
            List<ModelDtoInterface> list = new ArrayList<>();
            list.add(newsDto);

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
            List<ModelDtoInterface> list = newsDao.readAll()
                    .stream()
                    .map(val -> (ModelDtoInterface) NewsConverter.newsToNewsDto((NewsModel) val)).toList();
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
            NewsDto newsDto = (NewsDto) requestDto.getInputData();
            NewsModel newsModel = (NewsModel) newsDao.readById(Long.valueOf(newsDto.getId()));
            if (newsModel == null) throw new IllegalArgumentException("News does not exist");
            List<ModelDtoInterface> list = new ArrayList<>();
            list.add(NewsConverter.newsToNewsDto(newsModel));

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
            NewsDto newsDto = (NewsDto) requestDto.getInputData();
            NewsModel newsModel = (NewsModel) newsDao.update(NewsConverter.newsDtoToNews(newsDto));
            if (newsModel == null) throw new IllegalArgumentException("News does not exist");
            List<ModelDtoInterface> list = new ArrayList<>();
            list.add(NewsConverter.newsToNewsDto(newsModel));

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
            NewsDto newsDto = (NewsDto) requestDto.getInputData();
            newsDao.delete(Long.valueOf(newsDto.getId()));

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
