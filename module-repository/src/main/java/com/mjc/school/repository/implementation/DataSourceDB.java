package com.mjc.school.repository.implementation;

import com.mjc.school.repository.interfaces.ModelInterface;
import com.mjc.school.repository.interfaces.DataSourceInterface;
import java.nio.file.Path;
import java.util.*;


public class DataSourceDB implements DataSourceInterface {
    private static DataSourceDB instance;
    private Long authorMaxID = 0L;
    private Long newsMaxID = 0L;
    private static List<ModelInterface> authorModels;
    private static List<ModelInterface> newsModels;

    private DataSourceDB(Path path){
        if(Objects.isNull(authorModels)){
            authorModels = new ArrayList<>();
            newsModels = new ArrayList<>();
            FillDB.fillData(path);
        }
    }
    public static DataSourceDB getInstance(Path path) {
        if (instance == null) {
            instance = new DataSourceDB(path);
        }
        return instance;
    }
    public static DataSourceDB getInstance() {
        if (instance == null) {
            instance = new DataSourceDB(Path.of("module-repository/src/main/resources"));
        }
        return instance;
    }

    public Long getMaxID(Class<? extends ModelInterface> clazz){
        if (clazz.equals(AuthorModel.class)) return authorMaxID;
        if (clazz.equals(NewsModel.class)) return newsMaxID;
        return null;
    }

    @Override
    public List<ModelInterface> executeSelectQuery(Class<? extends ModelInterface> clazz) {
        if (clazz.equals(AuthorModel.class)) return selectAuthor();
        if (clazz.equals(NewsModel.class)) return selectNews();
        return null;
    }

    @Override
    public Boolean executeDeleteQuery(Class<? extends ModelInterface> clazz, Long id) {
        if (clazz.equals(AuthorModel.class)) return deleteAuthor(id);
        if (clazz.equals(NewsModel.class)) return deleteNews(id);
        return false;
    }

    @Override
    public ModelInterface executeUpdateQuery(ModelInterface model) {
        if (model.getClass().equals(AuthorModel.class)) return updateAuthor(model);
        if (model.getClass().equals(NewsModel.class)) return updateNews(model);
        return null;
    }

    @Override
    public ModelInterface executeCreateQuery(ModelInterface model) {
        if (model.getClass().equals(AuthorModel.class)) return createAuthor(model);
        if (model.getClass().equals(NewsModel.class)) return createNews(model);
        return null;
    }

    private List<ModelInterface> selectAuthor(){
        return authorModels.stream().toList();
    }

    private List<ModelInterface> selectNews(){
        return newsModels.stream().toList();
    }

    private Boolean deleteAuthor(Long id){
        authorModels.removeIf(author -> Objects.equals(author.getId(), id));
        if(Objects.equals(authorMaxID, id)) authorMaxID--;
        return true;
    }

    private Boolean deleteNews(Long id){
        newsModels.removeIf(news -> Objects.equals(news.getId(), id));
        if(Objects.equals(newsMaxID, id)) newsMaxID--;
        return true;
    }

    private ModelInterface updateAuthor(ModelInterface model){
        AuthorModel authorModel = (AuthorModel) model;
        List<ModelInterface> old = DataSourceDB.authorModels.stream()
                .filter(val -> Objects.equals(val.getId(), authorModel.getId()))
                .toList();
        if(old.size() != 1) return null;
        executeDeleteQuery(AuthorModel.class, authorModel.getId());
        return executeCreateQuery(authorModel);
    }

    private ModelInterface updateNews(ModelInterface model){
        NewsModel newsModel = (NewsModel) model;
        List<ModelInterface> old = DataSourceDB.newsModels.stream()
                .filter(val -> Objects.equals(val.getId(), newsModel.getId()))
                .toList();
        if(old.size() != 1) return null;

        if(executeSelectQuery(AuthorModel.class).stream()
                .noneMatch(val -> Objects
                        .equals(val.getId(), newsModel.getAuthorId()))) throw new IllegalArgumentException("Author does not exist");

        executeDeleteQuery(NewsModel.class, old.get(0).getId());

        NewsModel oldNewsModel = (NewsModel) old.get(0);
        NewsModel newNewsModel = new NewsModel.Builder().
                id(newsModel.getId()).
                title(newsModel.getTitle())
                .content(newsModel.getContent())
                .createDate(oldNewsModel.getCreateDate())
                .lastUpdateDate(newsModel.getLastUpdateDate())
                .authorId(newsModel.getAuthorId())
                .build();

        return executeCreateQuery(newNewsModel);
    }

    private ModelInterface createAuthor(ModelInterface model){
        DataSourceDB.authorModels.add(model);
        authorMaxID++;
        return model;
    }

    private ModelInterface createNews(ModelInterface model){
        NewsModel newsModel = (NewsModel) model;
        if(executeSelectQuery(AuthorModel.class).stream()
                .noneMatch(val -> Objects
                        .equals(val.getId(), newsModel.getAuthorId()))) throw new IllegalArgumentException("Author does not exist");
        DataSourceDB.newsModels.add(model);
        newsMaxID++;
        return model;
    }
}
