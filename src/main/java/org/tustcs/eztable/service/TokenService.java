package org.tustcs.eztable.service;


import org.tustcs.eztable.entity.Token;

/**
 * Created by polykickshaw on 17-6-18.
 */
public interface TokenService {

    boolean tokenVerify(int recId, int userId, String token);

    Token createToken(Integer userId);

    boolean expireToken(Integer userId);
}