package com.navi.data;

import lombok.Data;

/**
 * 管理页面需要用到的分页数据，用户在前后端之间交互数据
 */

@Data
public class PageModel {

    private int totalPage;
    private int curPage;

    public static final int pageSize = 10;//每页文章数

}
