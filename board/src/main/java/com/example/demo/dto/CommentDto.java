package com.example.demo.dto;

public class CommentDto {
    private Long id;
    private Long boardId;
    private Long parentId;
    private String content;
    private String writer;
    private String createdDate;
    private String modifiedDate;
    private Integer depth; // Changed from int to Integer to handle null values

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getBoardId() {
        return boardId;
    }
    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }
    public Long getParentId() {
        return parentId;
    }
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getWriter() {
        return writer;
    }
    public void setWriter(String writer) {
        this.writer = writer;
    }
    public String getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
    public String getModifiedDate() {
        return modifiedDate;
    }
    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    public Integer getDepth() {
        return depth;
    }
    public void setDepth(Integer depth) {
        this.depth = depth;
    }
} 