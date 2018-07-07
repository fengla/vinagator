package com.meetu.data;

import lombok.Data;

import java.io.Serializable;

/**
 * UserAppFollowDTO数据表的复合主键
 */


@Data
public class UserNewsFollowKey implements Serializable {

    private Long userid;
    private Long newsid;

    public UserNewsFollowKey(){

    }

    public UserNewsFollowKey(Long userid, Long newsid){
        this.userid = userid;
        this.newsid = newsid;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((userid == null) ? 0 : userid.hashCode());
        result = PRIME * result + ((newsid == null) ? 0 : newsid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }

        final UserNewsFollowKey other = (UserNewsFollowKey)obj;
        if(userid == null){
            if(other.userid != null){
                return false;
            }
        }else if(!userid.equals(other.userid)){
            return false;
        }
        if(newsid == null){
            if(other.newsid != null){
                return false;
            }
        }else if(!newsid.equals(other.newsid)){
            return false;
        }

        return true;
    }
}
