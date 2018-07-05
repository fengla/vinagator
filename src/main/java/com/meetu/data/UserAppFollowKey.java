package com.meetu.data;

import lombok.Data;

import java.io.Serializable;

/**
 * UserAppFollowDTO数据表的复合主键
 */


@Data
public class UserAppFollowKey implements Serializable {

    private Long userid;
    private Long appid;

    public UserAppFollowKey(){

    }

    public UserAppFollowKey(Long userid, Long appid){
        this.userid = userid;
        this.appid = appid;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((userid == null) ? 0 : userid.hashCode());
        result = PRIME * result + ((appid == null) ? 0 : appid.hashCode());
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

        final UserAppFollowKey other = (UserAppFollowKey)obj;
        if(userid == null){
            if(other.userid != null){
                return false;
            }
        }else if(!userid.equals(other.userid)){
            return false;
        }
        if(appid == null){
            if(other.appid != null){
                return false;
            }
        }else if(!appid.equals(other.appid)){
            return false;
        }

        return true;
    }
}
