 

package com.mycompany.controller.catalog;

import org.broadleafcommerce.core.web.controller.catalog.BroadleafSkuController;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class works in combination with the SkuHandlerMapping which finds a sku based upon
 * the passed in URL.
 */
@Controller("blSkuController")
public class SkuController extends BroadleafSkuController {
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return super.handleRequest(request, response);
    }

}
