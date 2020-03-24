package org.kevin.demo0212.model.dto;

import lombok.Data;
import org.kevin.demo0212.common.Constant;

/**
 * @author Kevin.Z
 * @version 2020-03-15
 */
@Data
public class PageModel {
    private boolean hasPrevious;
    private boolean hasNext;
    private int currentPage;
    private int pageSize;
    private long count;
    private long totalPage;
    private int previousPage;
    private int nextPage;

    public PageModel(Integer currentPage, Integer pageSize, long count) {
        this.currentPage = currentPage == null ? 0 : currentPage;
        this.pageSize = pageSize == null ? Constant.DEFAULT_PAGE_SIZE : pageSize;

        totalPage = count / this.pageSize;

        if (this.currentPage == 0 || totalPage == 0) {
            this.hasPrevious = false;
        } else {
            this.hasPrevious = true;
            this.previousPage = currentPage - 1;
        }

        if (this.currentPage == totalPage) {
            this.hasNext = false;
        } else {
            this.hasNext = true;
            this.nextPage = this.currentPage + 1;
        }
    }

}
