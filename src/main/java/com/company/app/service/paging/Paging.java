package com.company.app.service.paging;

import lombok.Data;

@Data
public class Paging {
    private final int limit;
    private final long offset;
    private final long page;

}
