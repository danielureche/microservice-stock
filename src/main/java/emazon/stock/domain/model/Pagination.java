package emazon.stock.domain.model;

import java.util.List;

public class Pagination<T> {
    private List<T> content;
    private int totalPages;
    private Long totalElements;
    private int currentPage;
    private boolean ascending;
    private boolean empty;

    public Pagination(boolean ascending, int currentPage, int totalPages, Long totalElements,  List<T> content) {
        this.content = content;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.currentPage = currentPage;
        this.ascending = ascending;
        this.empty = content.isEmpty();
    }

    public List<T> getContent() {
        return content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
}
