package com.mjc.school.repository.implementation;


import com.mjc.school.repository.interfaces.ModelInterface;
import lombok.Data;


@Data
public class AuthorModel implements ModelInterface {
    private Long id;
    private String name;

    public AuthorModel(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public AuthorModel(String name){
        this.id = generateId();
        this.name = name;
    }
    @Override
    public Long generateId() {
        DataSourceDB database = DataSourceDB.getInstance();
        return database.getMaxID(AuthorModel.class) + 1;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Author:" +"\n"+
                "id: " + id +"\n"+
                "name: " + name+ "\n";
    }
}
