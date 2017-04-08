 

package com.mycompany.controller.catalog;

import org.broadleafcommerce.common.exception.ServiceException;
import org.broadleafcommerce.core.web.controller.catalog.BroadleafSearchController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@Controller
@RequestMapping("/search")
public class SearchController extends BroadleafSearchController {

    @Override
    @RequestMapping("")
    public String search(Model model, HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "q") String q) throws ServletException, IOException, ServiceException {
        return super.search(model, request,response, q);
    }

}