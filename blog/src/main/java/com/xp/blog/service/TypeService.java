package com.xp.blog.service;

import com.xp.blog.entity.Type;

import java.util.List;

public interface TypeService {

    void addType(Type type);

    void updateTypeById(Type type);

    List<Type> getAllType();

    Type getTypeById(Integer id);

    void deleteTypeById(Integer id);

    Type getTypeByName(String typeName);
}
