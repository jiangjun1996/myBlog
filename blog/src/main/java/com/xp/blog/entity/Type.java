package com.xp.blog.entity;
import java.util.ArrayList;
import java.util.List;

/**
 * 博客类型
 */
public class Type {

    //自增id
    private Integer id;

    //类型名称
    private String typeName;

    //type->blog 一对多
    private List<Blog> blogs = new ArrayList<>();

    public Type() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
