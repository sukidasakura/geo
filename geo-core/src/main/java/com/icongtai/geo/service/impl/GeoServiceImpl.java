package com.icongtai.geo.service.impl;

import com.icongtai.geo.dao.GeoRepository;
import com.icongtai.geo.model.BrandsAndBusinessCircles;
import com.icongtai.geo.model.GeoReGeoInfo;
import com.icongtai.geo.service.GeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地理位置服务具体实现
 */
@Service
@ComponentScan(basePackages = { "com.icongtai.geo" })
public class GeoServiceImpl implements GeoService {

    @Autowired
    private GeoRepository geoRepository;

    @Override
    public List<GeoReGeoInfo> getGeoReGeoInfo(String location, String distance,
                                              String types, String extensions,
                                              boolean searchGaoDe) {
        return new GeoRepository().getGeoReGeoInfo(location, distance, types, extensions, searchGaoDe);
    }

    @Override
    public BrandsAndBusinessCircles getBrandsAndBusinessCircles(
            String district, String firstly_classification,
            String secondary_classification) {
        return null;
    }
}
