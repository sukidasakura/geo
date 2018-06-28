package com.icongtai.geo.web.controller;

import com.icongtai.geo.model.GeoReGeoBaseInfo;
import com.icongtai.geo.model.GeoReGeoInfo;
import com.icongtai.geo.service.GeoService;
import com.icongtai.geo.service.impl.GeoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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

    @RequestMapping(path = "/api/georegeo", method = RequestMethod.GET)
    @ResponseBody
    public List<GeoReGeoInfo> getGeoReGeo(String locations, String distance,
                              String types, String extensions, String searchGaoDe) {

        return new GeoServiceImpl().getGeoReGeoInfo(locations, distance, types,
                extensions, Boolean.parseBoolean(searchGaoDe));
    }


}
