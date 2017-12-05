package org.tustcs.eztable.service;

import org.tustcs.eztable.entity.FileInfo;
import org.tustcs.eztable.entity.FileKeyword;

import java.io.IOException;
import java.util.List;

public interface FileService {

    List<FileInfo> getFileList(int pageNum);

    List<FileInfo> getByFileName(int pageNum,String fileName);

    List<FileInfo> getByUserName(int pageNum,String userName);


    boolean addKeyword(int rowNo, String fileName) throws IOException;

    boolean addFileInfo(FileInfo fileInfo);

    boolean writeFile(String[] cells);

    boolean readFile(String fileName);

    boolean checkFile(String fileName);

    List<FileKeyword> getKeyByFileName(String fileName);
}
