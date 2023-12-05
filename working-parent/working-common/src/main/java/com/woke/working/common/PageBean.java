package com.woke.working.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Data
public class PageBean<T> implements Serializable {

    private int pageNum; //当前页数
    private int pageSize; //每页显示数
    private int totalPage; //总页数
    private int totalRecord; //总的记录数
    private List<T> data; //当前页面的数据集合
    private int start;
    private int end;

    public PageBean() {
    }

    public PageBean(int pageNum, int pageSize, int totalRecord, List<T> data) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;

        //计算总页数
        this.totalPage=totalRecord%pageSize==0?(totalRecord/pageSize):(totalRecord/pageSize+1);

        //计算每页的起始下标
        this.start=(pageNum-1)*pageSize;
        this.end=this.start+pageSize;
        this.data = data;
    }
}
