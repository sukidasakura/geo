package com.icongtai.geo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hexu
 * @since 2018-05-07 23:26
 */
@Controller
public class ApiStatusController {


    @RequestMapping(path = "/", method = RequestMethod.GET)
    @ResponseBody
    public String home() {
        return "interface server! ";
    }

    @RequestMapping(path = "/apiStatus", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public String status() {
        return "ok";
    }


}
