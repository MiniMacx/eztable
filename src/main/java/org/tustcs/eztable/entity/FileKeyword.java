package org.tustcs.eztable.entity;

public class FileKeyword {
    private Integer keywordId;

    private String keywordName;

    private Integer keywordColumn;

    private Integer fileId;

    private Integer keywordValue;

    public Integer getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(Integer keywordId) {
        this.keywordId = keywordId;
    }

    public String getKeywordName() {
        return keywordName;
    }

    public void setKeywordName(String keywordName) {
        this.keywordName = keywordName == null ? null : keywordName.trim();
    }

    public Integer getKeywordColumn() {
        return keywordColumn;
    }

    public void setKeywordColumn(Integer keywordColumn) {
        this.keywordColumn = keywordColumn;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getKeywordValue() {
        return keywordValue;
    }

    public void setKeywordValue(Integer keywordValue) {
        this.keywordValue = keywordValue;
    }
}