package com.xp.blog.service.impl;

import com.xp.blog.entity.Type;
import com.xp.blog.mapper.TypeMapper;
import com.xp.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService{

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public void addType(Type type) {
        typeMapper.addType(type);
    }

    @Override
    public void updateTypeById(Type type) {
        typeMapper.updateTypeById(type);
    }

    @Override
    public List<Type> getAllType() {
        return typeMapper.getAllType();
    }

    @Override
    public Type getTypeById(Integer id) {
        return typeMapper.getTypeById(id);
    }

    @Override
    public void deleteTypeById(Integer id) {
        typeMapper.deleteTypeById(id);
    }

    @Override
    public Type getTypeByName(String typeName) {
        return typeMapper.getTypeByName(typeName);
    }
}
