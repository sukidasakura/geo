package com.icongtai.geo.web.controller;

import com.icongtai.geo.model.Constance;
import com.icongtai.geo.model.GeoReGeoInfo;
import com.icongtai.geo.service.impl.GeoServiceImpl;
import com.icongtai.geo.utils.StringUtil;
import com.icongtai.geo.dao.util.GeoUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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


    @RequestMapping(path="/api/georegeo-query", method = RequestMethod.GET)
    @ResponseBody
    public List<GeoReGeoInfo> getGeoReGeoQury(String name, String locations, String distances, String types,
          String firstly_classification, String secondary_classification,
          String province, String city, String district,
          String township, String business_circle,
          String formatted_address, String avg_price,
          String shops, String good_comments, String lvl,
          String leisure_type, String fun_type,
          String energy_type, String numbers, String from,
          String size, String extentions, String searchGaoDe) {

        // 参数验证
        // 用于封装最终的返回结果信息
        List<GeoReGeoInfo> geoReGeoInfos = new CopyOnWriteArrayList<>();
        // 初步验证searchGaoDe
        GeoReGeoInfo geoReGeoInfo = new GeoReGeoInfo();
        if (StringUtil.isNotEmptyOrNull(searchGaoDe)&&
                (!"true".equals(searchGaoDe) || !"false".equals(searchGaoDe))){
            geoReGeoInfo.setStatus(Constance.STATUS_FAILED);
            geoReGeoInfo.setInfo("searchGaoDe 值不正确。");
            geoReGeoInfos.add(geoReGeoInfo);
            return  geoReGeoInfos;
        }

        // 查询高德信息暂时不支持
        if (Boolean.valueOf(searchGaoDe)) {
            // TODO 下个版本可能需要完成的功能
            geoReGeoInfo.setStatus(Constance.STATUS_FAILED);
            geoReGeoInfo.setInfo("搜索高德功能暂时未支持。");
            geoReGeoInfos.add(geoReGeoInfo);
            return  geoReGeoInfos;
        }
        // location 的值和distance 的值是否为空时
        if (StringUtil.isEmptyOrNull(locations) || StringUtil.isEmptyOrNull(distances)) {
            geoReGeoInfo.setStatus(Constance.STATUS_FAILED);
            geoReGeoInfo.setInfo("地址坐标或者搜索范围为空。");
            geoReGeoInfos.add(geoReGeoInfo);
            return  geoReGeoInfos;
        }

        // 验证distances 的数据格式，范围间的数据用"-"分隔
        if (StringUtil.validateRangeVlaues(distances)) {
            GeoUtil.returnErrorGeoRegeoInfos("搜索范围distances 格式不正确");
        }


        // 验证avg_price
        if (StringUtil.validateRangeVlaues(avg_price)) {
            GeoUtil.returnErrorGeoRegeoInfos("搜索范围avg_price 格式不正确");
        }


        // 验证 shops
        if (StringUtil.validateRangeVlaues(shops)) {
            GeoUtil.returnErrorGeoRegeoInfos("搜索范围 shops 格式不正确");
        }

        // 验证 good_comments
        if (StringUtil.validateRangeVlaues(good_comments)) {
            GeoUtil.returnErrorGeoRegeoInfos("搜索范围 good_comments 格式不正确");
        }

        // 验证 lvl
        if (StringUtil.validateRangeVlaues(lvl)) {
            GeoUtil.returnErrorGeoRegeoInfos("搜索范围 lvl 格式不正确");
        }

        // 验证 numbers
        if (StringUtil.validateRangeVlaues(numbers)) {
            GeoUtil.returnErrorGeoRegeoInfos("搜索范围 numbers 格式不正确");
        }

        // 验证 size
        if (StringUtil.validateRangeVlaues(size)) {
            GeoUtil.returnErrorGeoRegeoInfos("搜索范围 size 格式不正确");
        }

        // 验证 from
        if (StringUtil.validateRangeVlaues(from)) {
            GeoUtil.returnErrorGeoRegeoInfos("搜索范围 from 格式不正确");
        }

        return new GeoServiceImpl().getGeoReGeoQuery(name,locations, distances, types,
                firstly_classification, secondary_classification,
                province, city, district,
                township, business_circle,
                formatted_address, avg_price,
                shops, good_comments, lvl,
                leisure_type, fun_type,
                energy_type, numbers, from,
                size, extentions, Boolean.valueOf(searchGaoDe));
    }


}
