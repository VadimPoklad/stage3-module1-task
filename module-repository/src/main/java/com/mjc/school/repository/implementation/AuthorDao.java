package com.mjc.school.repository.implementation;

import com.mjc.school.repository.interfaces.ModelDaoInterface;
import com.mjc.school.repository.interfaces.ModelInterface;

import java.util.List;

public class AuthorDao implements ModelDaoInterface {
    private final DataSourceDB dataSourceDB;
    public AuthorDao(){
        dataSourceDB = DataSourceDB.getInstance();
    }
    @Override
    public ModelInterface create(ModelInterface model) throws Exception {
        return dataSourceDB.executeCreateQuery(model);
    }
    @Override
    public List<ModelInterface> readAll(){
        return dataSourceDB.executeSelectQuery(AuthorModel.class);
    }
    @Override
    public ModelInterface readById(Long id) throws Exception {
        return ModelDaoInterface.super.readById(id);
    }
    @Override
    public ModelInterface update(ModelInterface model){
        return dataSourceDB.executeUpdateQuery(model);
    }
    @Override
    public Boolean delete(Long id){
        return dataSourceDB.executeDeleteQuery(AuthorModel.class, id);
    }
}
