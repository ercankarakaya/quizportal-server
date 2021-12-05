package com.ercan.response;

import com.ercan.enums.ResponseStatusEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.*;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageResponse extends Response{
     boolean first;
     boolean last;
     int currentPageNumber;
     int itemsInPage;
     int pageSize;
     int totalPages;
     long totalItems;
    Sort sort;
    List items;

    public void setPageStats(Page pg, boolean setDefaultMessage) {
        this.first = pg.isFirst();
        this.last = pg.isLast();
        this.currentPageNumber = pg.getNumber();
        this.itemsInPage = pg.getNumberOfElements();
        this.pageSize = pg.getSize();
        this.totalPages = pg.getTotalPages();
        this.totalItems = pg.getTotalElements();
        //this.items             = pg.getContent();
        this.sort = pg.getSort();
        if (setDefaultMessage == true) {
            this.setStatus(ResponseStatusEnum.SUCCESS);
            this.setMessage("Page " + (pg.getNumber() + 1) + " of " + pg.getTotalPages());
        }
    }

    public void setPageTotal(int count, boolean setDefaultMessage) {
        //this.items             = list;
        this.first = true;
        this.last = true;
        this.itemsInPage = count;
        this.totalItems = count;
        this.totalPages = 1;
        this.pageSize = count;
        if (setDefaultMessage == true) {
            this.setStatus(ResponseStatusEnum.SUCCESS);
            this.setMessage("Total " + count + " items ");
        }
    }
}
