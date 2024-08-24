package emazon.stock.domain.util;

public class PaginationUtil {
    private int pageNumber;
    private int pageSize;
    private boolean ascending;
    private String nameFilter;

    public PaginationUtil() {
    }

    public PaginationUtil(int pageSize, int pageNumber, String nameFilter, boolean ascending) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.nameFilter = nameFilter;
        this.ascending = ascending;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isAscending() {
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
