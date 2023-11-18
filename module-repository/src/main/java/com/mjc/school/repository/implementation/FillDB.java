package com.mjc.school.repository.implementation;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Scanner;

public class FillDB {
    public static void fillData(Path path){
        fillAuthor(path);
        fillNews(path);
    }
    private static void fillAuthor(Path path){
        DataSourceDB dataSourceDB = DataSourceDB.getInstance();
        File file = new File(path.toFile()+"/author.txt");
        try(Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()){
                AuthorModel authorModel = new AuthorModel(scanner.nextLine());
                dataSourceDB.executeCreateQuery(authorModel);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void fillNews(Path path){
        DataSourceDB dataSourceDB = DataSourceDB.getInstance();
        File newsFile = new File(path.toFile()+"/news.txt");
        File contentFile = new File(path.toFile()+"/content.txt");
        try(Scanner newsScanner = new Scanner(newsFile);
            Scanner contentScanner = new Scanner(contentFile)) {
            Long authorId = 1L;
            while (newsScanner.hasNext() && contentScanner.hasNext()){
                NewsModel newsModel =  new NewsModel.Builder()
                        .title(newsScanner.nextLine())
                        .content(contentScanner.nextLine())
                        .createDate(LocalDateTime.now())
                        .lastUpdateDate(LocalDateTime.now())
                        .authorId(authorId)
                        .build();
                dataSourceDB.executeCreateQuery(newsModel);
                authorId++;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
