package com.company.app.controller.command.drug;

import com.company.app.controller.command.Command;
import com.company.app.model.dto.DrugDto;
import com.company.app.service.DrugService;
import com.company.app.service.paging.Paging;
import com.company.app.service.paging.PagingUtil;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class AllDrugsCommand implements Command {
    private final DrugService drugService;
    private final PagingUtil pagingUtil;

    public AllDrugsCommand(DrugService drugService, PagingUtil pagingUtil) {
        this.drugService = drugService;
        this.pagingUtil = pagingUtil;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Paging paging = pagingUtil.getPaging(req);
        List<DrugDto> drugs = drugService.getAll(paging.getLimit(), paging.getOffset());
        long totalPages = pagingUtil.getTotalPages(drugService.count(), paging.getLimit());


        req.setAttribute("drugs", drugs);
        req.setAttribute("currentPage", paging.getPage());
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("limit", paging.getLimit());

        /*
        Paging paging = pagingUtil.getPaging(req);
        List<DrugDto> drugs = drugService.getAll(paging);
        req.setAttribute("drugs", drugs);
        ***getAll(paging) convertTo getAll(limit, offset)
         */

        return "jsp/drug/drugs.jsp";
    }
}
