package org.tustcs.eztable.dao;


import org.tustcs.eztable.entity.Token;

public interface TokenMapper {
    int insert(Token record);

    int insertSelective(Token record);

    Token selectByUserId(Integer userId);

    Token selectById(Integer id);

    Token selectByRecId(Integer recId);

    Token selectByToken(String token);

    int updateByRecId(Token record);
}