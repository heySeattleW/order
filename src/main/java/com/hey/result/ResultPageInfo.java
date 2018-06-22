package com.hey.result;

/**
 * Created by heer on 2018/6/20.
 */
public class ResultPageInfo {

    //分页总数
    private int pages;

    //当前页
    private int currentPage;

    //记录总数
    private Long totalNum;

    //分页大小
    private int pageSize;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public ResultPageInfo(int pages, int currentPage, Long totalNum, int pageSize) {
        this.pages = pages;
        this.currentPage = currentPage;
        this.totalNum = totalNum;
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"pages\":")
                .append(pages);
        sb.append(",\"currentPage\":")
                .append(currentPage);
        sb.append(",\"totalNum\":")
                .append(totalNum);
        sb.append(",\"pageSize\":")
                .append(pageSize);
        sb.append('}');
        return sb.toString();
    }
}
