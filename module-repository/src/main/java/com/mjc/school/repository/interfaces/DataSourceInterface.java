package com.mjc.school.repository.interfaces;


import java.util.List;

public interface DataSourceInterface {
     List<ModelInterface> executeSelectQuery(Class<? extends ModelInterface> clazz) throws Exception;
     Boolean executeDeleteQuery(Class<? extends ModelInterface> clazz, Long id) throws Exception;
     ModelInterface executeUpdateQuery(ModelInterface model) throws Exception;
     ModelInterface executeCreateQuery(ModelInterface model) throws Exception;

}
