package org.tustcs.eztable.dao;

import org.tustcs.eztable.entity.Token;

public interface TokenMapper {
    int deleteByPrimaryKey(Integer recId);

    int insert(Token record);

    int insertSelective(Token record);

    Token selectByPrimaryKey(Integer recId);

    int updateByPrimaryKeySelective(Token record);

    int updateByPrimaryKey(Token record);
}