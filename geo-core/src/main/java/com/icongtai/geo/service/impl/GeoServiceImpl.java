package com.icongtai.geo.service.impl;

import com.icongtai.geo.dao.GeoRepository;
import com.icongtai.geo.model.AggregationValues;
import com.icongtai.geo.model.GeoReGeoInfo;
import com.icongtai.geo.service.GeoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地理位置服务具体实现
 */
@Service
public class GeoServiceImpl implements GeoService {

    @Override
    public List<GeoReGeoInfo> getGeoReGeoInfo(String location, String distance,
                                              String types, String extensions,
                                              boolean searchGaoDe) {
        return new GeoRepository().getGeoReGeoInfo(location, distance, types, extensions, searchGaoDe);
    }

    @Override
    public AggregationValues getAggregationValues(String province,
                                                         String city, String district,
                                                         String firstly_classification,
                                                         String secondary_classification,
                                                         String aggrationField,
                                                         String from,
                                                         String size) {

        return new GeoRepository().getAggregationValues(province,city,
                district, firstly_classification, secondary_classification,
                aggrationField, from, size);
    }


    @Override
    public List<GeoReGeoInfo> getGeoReGeoQuery(String name, String locations, String distances, String types,
                                               String firstly_classification, String secondary_classification,
                                               String province, String city, String district,
                                               String township, String business_circle,
                                               String formatted_address, String avg_price,
                                               String shops, String good_comments, String lvl,
                                               String leisure_type, String fun_type,
                                               String energy_type, String numbers, String from,
                                               String size, String extentions, boolean searchGaoDe) {
        return new GeoRepository().getGeoReGeoQuery(name, locations, distances, types,
                firstly_classification, secondary_classification,
                province, city, district,
                township, business_circle,
                formatted_address, avg_price,
                shops, good_comments, lvl,
                leisure_type, fun_type,
                energy_type, numbers, from,
                size, extentions, searchGaoDe);
    }
}
