package org.tustcs.eztable.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tustcs.eztable.entity.FileKeyword;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-*.xml"})
public class FileKeywordMapperTest {

    @Resource
    FileKeywordMapper fileKeywordMapper;

    @Test
    public void batchInsertTest(){
        List<FileKeyword> keywordList=new ArrayList<FileKeyword>();

        FileKeyword keyword=new FileKeyword();
        keyword.setFileId(1);
        keyword.setKeywordId(1);
        keyword.setKeywordName("name");
        keyword.setKeywordValue(0);
        keyword.setKeywordColumn(2);

        FileKeyword keyword1=new FileKeyword();
        keyword1.setFileId(1);
        keyword1.setKeywordId(1);
        keyword1.setKeywordName("value");
        keyword1.setKeywordValue(0);
        keyword1.setKeywordColumn(4);

        keywordList.add(keyword);
        keywordList.add(keyword1);

        fileKeywordMapper.batchInsert(keywordList);
    }
}