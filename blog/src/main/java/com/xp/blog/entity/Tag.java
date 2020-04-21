package com.xp.blog.entity;
import java.util.ArrayList;
import java.util.List;

/**
 * 标签类
 */
public class Tag {


    //自增id
    private int id;
    //标签名称
    private String tagName;
    //tag->blog 多对多
    private List<Blog> blogs = new ArrayList<>();

    public Tag() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}
