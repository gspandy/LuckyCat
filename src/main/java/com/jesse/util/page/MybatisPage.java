package com.jesse.util.page;

import java.util.List;

/**
 * Created by abc on 2016/1/7.
 */
public class MybatisPage<T> {

    private long totalElements;

    private List<T> content;

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getPageCount(int pageSize){
        int pageCount= (int) (totalElements/pageSize);
        if (totalElements%pageSize!=0){
            pageCount++;
        }
        return pageCount;
    }

}
