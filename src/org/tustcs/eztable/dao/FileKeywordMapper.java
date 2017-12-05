package org.tustcs.eztable.dao;

import org.tustcs.eztable.entity.FileKeyword;

public interface FileKeywordMapper {
    int deleteByPrimaryKey(Integer keywordId);

    int insert(FileKeyword record);

    int insertSelective(FileKeyword record);

    FileKeyword selectByPrimaryKey(Integer keywordId);

    int updateByPrimaryKeySelective(FileKeyword record);

    int updateByPrimaryKey(FileKeyword record);
}