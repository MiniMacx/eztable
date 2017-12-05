package org.tustcs.eztable.dao;

import org.tustcs.eztable.entity.FileKeyword;
import sun.nio.ch.FileKey;

import java.util.List;

public interface FileKeywordMapper {
    int deleteByPrimaryKey(Integer keywordId);

    int insert(FileKeyword record);

    int insertSelective(FileKeyword record);

    FileKeyword selectByPrimaryKey(Integer keywordId);

    int updateByPrimaryKeySelective(FileKeyword record);

    int updateByPrimaryKey(FileKeyword record);

    int batchInsert(List<FileKeyword> fileKeywordList);

    List<FileKeyword> selectByFileId(int fileId);
}