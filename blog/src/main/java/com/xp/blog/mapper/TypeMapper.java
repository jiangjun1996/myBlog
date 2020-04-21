package com.xp.blog.mapper;

import com.xp.blog.entity.Type;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeMapper {

    void addType(Type type);

    void updateTypeById(Type type);

    List<Type> getAllType();

    Type getTypeById(Integer id);

    void deleteTypeById(Integer id);

    Type getTypeByName(String typeName);
}
