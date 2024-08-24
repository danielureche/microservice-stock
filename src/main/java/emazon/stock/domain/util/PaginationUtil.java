package emazon.stock.domain.util;

public class PaginationUtil {
    private int pageNumber;
    private int pageSize;
    private boolean ascending;
    private String nameFilter;

    public PaginationUtil() {
    }

    public PaginationUtil(int pageNumber, int pageSize, boolean ascending, String nameFilter) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.ascending = ascending;
        this.nameFilter = nameFilter;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean isAscending() {
        return ascending;
    }

    public void setAscending(Boolean ascending) {
        this.ascending = ascending;
    }

    public String getNameFilter() {
        return nameFilter;
    }

    public void setNameFilter(String nameFilter) {
        this.nameFilter = nameFilter;
    }
}
