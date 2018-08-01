package com.navi.util;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.TimeUnit;

/**
 * 工具类：为用户生成token(本平台内的token)
 */

@Slf4j
public class TokenGenerator {

    private static final int TOKEN_LEGTH = 25;

    public static LoadingCache<Long, String> tokenCache = Caffeine.newBuilder()
            .maximumSize(5000)
            .expireAfterAccess(1, TimeUnit.DAYS) //24小时有效
            .build(new CacheLoader<Long, String>() {
                @Override
                public String load(Long userid) throws Exception {
                    //todo: 这里查询数据库并返回token
                    String token = "";
                    //token = ?

                    return "";
                }
            });

    //todo:这里定义一个cache...从数据库中查询数据

    public static String genToken(){//wdf:我的理解这里实质上其实就是自动为用户生成一个密码
        String token  = "";

        //version1: userid+ts -> base64
        //userid,openid ——>todo: 加密

        //version2
        token = RandomString.getString(TOKEN_LEGTH);

        return token;
    }

    public static boolean validToken(long userid, String token){

        boolean valid  = false;
        String tokenTrue = tokenCache.get(userid);
        //这里查询的结果有多种可能：1.正确查询出一个String 2.这个用户不存在，所以没查出来 3.查询过程中出现异常，报错，所以没查出来
        //需要分析3种情况分别怎么处理？

        if (token!=null && token.equals(tokenTrue)) {
            valid = true;
        }

        return valid;
    }

    public static void main(String args[]){

    }
}
