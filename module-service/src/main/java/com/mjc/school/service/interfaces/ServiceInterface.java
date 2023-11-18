package com.mjc.school.service.interfaces;

import com.mjc.school.service.implementation.RequestDto;
import com.mjc.school.service.implementation.ResponseDto;

public interface ServiceInterface {
    ResponseDto create(RequestDto requestDto);

    ResponseDto getAll();

    ResponseDto getById(RequestDto requestDto);

    ResponseDto updateById(RequestDto requestDto);

    ResponseDto removeById(RequestDto requestDto);

    ResponseDto buildErrorResponse(Exception e);
}
