package com.mjc.school.repository.interfaces;


import java.util.List;
import java.util.Objects;

public interface ModelDaoInterface {
     ModelInterface create(ModelInterface model) throws Exception;
     List<ModelInterface> readAll() throws Exception;
     default ModelInterface readById(Long id) throws Exception{
          List<ModelInterface> list = readAll();
          List<ModelInterface> news = list.stream().filter(val -> Objects.equals(val.getId(), id)).toList();
          if(news.size() == 0) return null;
          return news.get(0);
     }
     ModelInterface update(ModelInterface model) throws Exception;
     Boolean delete(Long id) throws Exception;
}
