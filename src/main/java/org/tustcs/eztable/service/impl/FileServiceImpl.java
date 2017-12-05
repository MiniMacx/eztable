package org.tustcs.eztable.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tustcs.eztable.config.Config;
import org.tustcs.eztable.dao.FileInfoMapper;
import org.tustcs.eztable.dao.FileKeywordMapper;
import org.tustcs.eztable.entity.FileInfo;
import org.tustcs.eztable.entity.FileKeyword;
import org.tustcs.eztable.enums.ResultEnums;
import org.tustcs.eztable.exception.FileException;
import org.tustcs.eztable.service.FileService;
import org.tustcs.eztable.utils.XSSFUtils;

import javax.annotation.Resource;
import javax.xml.transform.Result;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class FileServiceImpl implements FileService{

    @Resource
    FileInfoMapper fileInfoMapper;

    @Resource
    FileKeywordMapper fileKeywordMapper;

    @Override
    public List<FileInfo> getFileList(int pageNum) {
        PageHelper.startPage(pageNum, Config.PAGE_SIZE);
        return fileInfoMapper.selectAll();
    }

    @Override
    public List<FileInfo> getByFileName(int pageNum,String fileName) {
        PageHelper.startPage(pageNum,Config.PAGE_SIZE);
        return fileInfoMapper.selectByFileName(fileName);
    }


    @Override
    public List<FileInfo> getByUserName(int pageNum,String userName) {
        PageHelper.startPage(pageNum,Config.PAGE_SIZE);
        return fileInfoMapper.selectByUserName(userName);
    }


    @Override
    public boolean addKeyword(int rowNo, String fileName) throws IOException {
       List<FileKeyword> keywordList= XSSFUtils.readKeyword(rowNo,fileName);

       FileInfo fileInfo=fileInfoMapper.findOneByFileName(fileName);

       if(fileInfo==null){
           throw new FileException(ResultEnums.FILE_NOTFOUND);
       }

        for (FileKeyword fileKeyword :
             keywordList) {
            fileKeyword.setFileId(fileInfo.getFileId());
        }

       return fileKeywordMapper.batchInsert(keywordList)>0;
    }

    @Override
    public boolean addFileInfo(FileInfo fileInfo) {
        return fileInfoMapper.insertSelective(fileInfo)>0;
    }

    @Override
    public boolean writeFile(String[] cells) {
        return false;
    }

    @Override
    public boolean readFile(String fileName) {
        return false;
    }

    @Override
    public List<FileKeyword> getKeyByFileName(String fileName) {
        FileInfo fileInfo=fileInfoMapper.findOneByFileName(fileName);
        if(fileInfo==null){
            throw new FileException(ResultEnums.FILE_NOTFOUND);
        }
       return fileKeywordMapper.selectByFileId(fileInfo.getFileId());
    }

    @Override
    public boolean checkFile(String fileName) {
        FileInfo fileInfo=fileInfoMapper.findOneByFileName(fileName);
        if(fileInfo == null){
            throw new FileException(ResultEnums.FILE_NOTFOUND);
        }
        return true;
    }
}
