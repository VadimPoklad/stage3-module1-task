package com.mjc.school.web.implementation;

import com.mjc.school.service.implementation.NewsDto;
import com.mjc.school.service.implementation.RequestDto;
import com.mjc.school.service.implementation.NewsService;

import java.util.Objects;

public class Controller {
    boolean continueLoop = true;
    private volatile static Controller instance;
    private final View view = View.getInstance();
    private final NewsService newsService = new NewsService();
    private Controller(){}

    public static Controller getInstance(){
        synchronized (Controller.class) {
            if (Objects.isNull(instance)) {
                instance = new Controller();
            }
            return instance;
        }
    }
    public void controlMenuView(){
        int index = validateCommandInput(view.inputCommand());
        if(index == -1) standard();
        else if (index == 0) exit();
        else if (index == 1) getAllNews();
        else if (index == 2) getNewsById();
        else if (index == 3) createNews();
        else if (index == 4) updateNews();
        else if (index == 5) removeNewsById();
    }
    private int validateCommandInput(String input){
        try {
            int index = Integer.parseInt(input);
            if(index < 0 || index > 5) return -1;
            return index;
        } catch(Exception e){
            return -1;
        }
    }
    public void exit(){continueLoop = false;}
    public void getAllNews(){
        view.showResponse(newsService.getAll());
    }
    public void getNewsById(){
        view.showResponse(newsService.getById(
            RequestDto.builder()
                    .inputData(
                    NewsDto.
                    builder()
                            .id(view.inputId())
                            .build())
                    .build())
        );
    }
    public void createNews(){
        view.showResponse(newsService.create(
            RequestDto.builder().inputData(view.inputNews()).build()
        ));
    }
    public void updateNews(){
        String id = view.inputId();
        NewsDto newsDto = view.inputNews();
        newsDto.setId(id);
        view.showResponse(
            newsService.updateById(
                RequestDto.builder()
                    .inputData(newsDto)
                    .build()
            )
        );
    }
    public void removeNewsById(){
        view.showDeleteResponse(newsService.removeById(
            RequestDto.builder().inputData(
                        NewsDto.
                                builder()
                                .id(view.inputId())
                                .build())
                .build())
        );
    }
    public void standard(){
        view.showInputIncorrectCommand();
    }
}

