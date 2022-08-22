package com.company.app.service.paging;

import jakarta.servlet.http.HttpServletRequest;

public class PagingUtil {
    private static PagingUtil instance;

    private PagingUtil() {
    }

    public static PagingUtil getInstance() {
        if (instance == null) {
            instance = new PagingUtil();
        }
        return instance;
    }

    public Paging getPaging(HttpServletRequest req) {
        String limitStr = req.getParameter("limit");
        int limit;
        if (limitStr == null) {
            limit = 10;
        } else {
            try {
                limit = Integer.parseInt(limitStr);
                if (limit < 1 || limit > 19) {
                    limit = 10;
                }
            } catch (NumberFormatException e) {
                limit = 10;
            }
        }
        String offsetStr = req.getParameter("page");
        long page;
        if (offsetStr == null) {
            page = 1L;
        } else {
            try {
                page = Long.parseLong(offsetStr);
                if (page < 1) {
                    page = 1L;
                }
            } catch (NumberFormatException e) {
                page = 1L;
            }
        }
        long offset = (page - 1) * limit;
        return new Paging(limit, offset, page);
    }

    public long getTotalPages(long totalEntities, long limit) {
        long totalСompletPages = totalEntities / limit;
        int totalIncompletPages = (totalEntities % limit > 0 ? 1 : 0);
        return totalСompletPages + totalIncompletPages;
    }
}
