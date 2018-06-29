package com.icongtai.geo.dao.util;

import com.icongtai.geo.model.Constance;
import com.icongtai.geo.model.GeoReGeoInfo;
import org.apache.lucene.util.QueryBuilder;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GeoUtil {
    /**
     * @param  errorMessage 返回的错误提示信息
     * @return 当数据格式或者程序错误的时候，返回的结果
     */
    public static List<GeoReGeoInfo> returnErrorGeoRegeoInfos(String errorMessage) {
        GeoReGeoInfo geoReGeoInfo = new GeoReGeoInfo();
        geoReGeoInfo.setStatus(Constance.STATUS_FAILED);
        geoReGeoInfo.setInfo(errorMessage);
        List<GeoReGeoInfo> geoReGeoInfos = new CopyOnWriteArrayList<>();
        geoReGeoInfos.add(geoReGeoInfo);
        return geoReGeoInfos;
    }

//    /**
//     * 根据传进来的值，用来进行划分数据
//     * @param queryStr
//     * @return
//     */
//    public QueryBuilder getSholdMutilQueryValue(String queryStr) {
//
//    }

}
