package com.mjc.school.web.implementation;

import com.mjc.school.service.implementation.NewsDto;
import com.mjc.school.service.implementation.ResponseDto;

import java.util.Objects;
import java.util.Scanner;

class View {
    private volatile static View instance;
    public static View getInstance(){
        synchronized (View.class) {
            if (Objects.isNull(instance)) {
                instance = new View();
            }
            return instance;
        }
    }
    public void showMenu() {
        System.out.print(
                """
                        ______________________________
                        Enter the number of operation:
                        1 - View all news
                        2 - View news by id
                        3 - Create news
                        4 - Update news
                        5 - Delete news by id
                        0 - Exit
                        ______________________________
                        """
        );
    }
    public void showInputIdMassage(){System.out.println("Enter id:");}
    public void showInputTitleMassage(){System.out.println("Enter title:");}
    public void showInputContentMassage(){System.out.println("Enter content:");}
    public void showInputAuthorIdMassage(){System.out.println("Enter author id:");}
    public void showInputIncorrectCommand(){System.out.println("Command not found.");}
    public void showResponse(ResponseDto responseDto){
        if(responseDto.getStatus().equals("OK")) {
            responseDto
                    .getResultSet()
                    .stream()
                    .map(model ->(NewsDto) model)
                    .forEach(this::showNews);
        } else {
            showError(responseDto);
        }
    }
    public void showDeleteResponse(ResponseDto responseDto){
        System.out.println("OK".equals(responseDto.getStatus())? "True": responseDto.getError());
    }
    private void showNews(NewsDto newsDto){System.out.println(newsDto);}
    public void showError(ResponseDto responseDto){
        System.out.println(responseDto.getStatus()+"\n"+responseDto.getError().getMessage());
    }
    public String inputCommand(){
        showMenu();
        return getInput();
    }
    public String inputId(){
        showInputIdMassage();
        return getInput();
    }
    public NewsDto inputNews(){
        NewsDto newsDto = NewsDto.builder().build();

        showInputTitleMassage();
        newsDto.setTitle(getInput());

        showInputContentMassage();
        newsDto.setContent(getInput());

        showInputAuthorIdMassage();
        newsDto.setAuthorId(getInput());

        return newsDto;
    }
    private String getInput(){
        return new Scanner(System.in).nextLine();
    }
}