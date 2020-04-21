package com.xp.blog.entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 评论类
 */
public class Comment {

    //自增id
    private int id;
    //昵称
    private String nickname;
    //邮箱
    private String email;
    //评论内容
    private String content;
    //头像
    private String avatar;
    //评论时间
    private Date createTime;

    //博客类 comment->blog 多对一·
    private Blog blog;

    //comment自关联,多对多
    private List<Comment> childComments = new ArrayList<>();

    //评论里面的父类,父类只有一个,父类下面会有子评论(别人的回复)
    private Comment parentComment;

    public Comment() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public List<Comment> getChildComments() {
        return childComments;
    }

    public void setChildComments(List<Comment> childComments) {
        this.childComments = childComments;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
