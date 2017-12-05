package org.tustcs.eztable.dao;

import org.apache.ibatis.annotations.Param;
import org.tustcs.eztable.entity.FileInfo;

import java.io.File;
import java.util.List;

public interface FileInfoMapper {
    int deleteByPrimaryKey(Integer fileId);

    int insert(FileInfo record);

    int insertSelective(FileInfo record);

    FileInfo selectByPrimaryKey(Integer fileId);

    int updateByPrimaryKeySelective(FileInfo record);

    int updateByPrimaryKey(FileInfo record);

    List<FileInfo> selectAll();

    List<FileInfo> selectByUserName(String userName);

    FileInfo findOneByFileName(String fileName);

    List<FileInfo> selectByTypeId(Integer typeId);

    List<FileInfo> selectByFileName(@Param("fileName") String fileName);

    int selectCount();
}