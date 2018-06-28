package com.icongtai.geo.dao;

import com.icongtai.geo.esconfig.ElasticsearchConfig;
import com.icongtai.geo.esconfig.ElasticsearchConfigV1;
import com.icongtai.geo.model.*;
import com.icongtai.geo.utils.StringUtil;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * elasticsearch 数据处理
 */
@Component
public class GeoRepository {
    @Autowired
    private TransportClient client;

//    @Autowired
//    private ElasticsearchConfig elasticsearchConfig;

    // 需要搜索的索引
    private String index = "zebra_info";
    // 需要搜索的索引类型

    private String types = "zebra_info";

    public List<GeoReGeoInfo> getGeoReGeoInfo(String locations, String distance,
                                        String typeNames, String extensions,
                                        boolean searchGaoDe) {
        // 用于封装最终的返回结果信息
        List<GeoReGeoInfo> geoReGeoInfos = new CopyOnWriteArrayList<>();
        GeoReGeoInfo geoReGeoInfo = new GeoReGeoInfo();
        if (searchGaoDe) {
            // TODO 下个版本可能需要完成的功能
            geoReGeoInfo.setStatus(Constance.STATUS_FAILED);
            geoReGeoInfo.setInfo("搜索高德功能暂时未支持。");
            geoReGeoInfos.add(geoReGeoInfo);
            return  geoReGeoInfos;
        }
       // location 的值和distance 的值是否为空时
        if (locations == null || "".equals(locations) || distance == null && "".equals(distance)) {
            geoReGeoInfo.setStatus(Constance.STATUS_FAILED);
            geoReGeoInfo.setInfo("地址坐标或者搜索范围为空。");
            geoReGeoInfos.add(geoReGeoInfo);
            return  geoReGeoInfos;
        }
        // 存储传过来的points
        List<String> points = new CopyOnWriteArrayList<>();
        // 搜索范围，范围间的数据用"-"分隔
        String[] ranges = distance.split(Constance.SPLIT_SEARCH_RANGE);
        try{
            String[] pointArr = locations.split(Constance.SPLIT_POPINTS);
            for(String point : pointArr) {  // 点与点之间的分隔线"|"
                String []matLon =  point.split(Constance.SPLIT_POINT);
                Double.valueOf(matLon[0]); // 经纬度之间的分隔线","
                if (point.split(Constance.SPLIT_POINT).length > 1) {
                    Double.valueOf(matLon[1]); // 经纬度之间的分隔线"|,
                }
                points.add(point);
            }
            // 检查范围是否是数字,
            for (String range : ranges) {
                Double.valueOf(range);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            geoReGeoInfo.setStatus(Constance.STATUS_FAILED);
            // 返回错误信息，经纬度信息或者范围搜索的数据格式错误信息
            geoReGeoInfo.setInfo(e.getMessage());
            geoReGeoInfos.add(geoReGeoInfo);
            return  geoReGeoInfos;
        }



        //多个点的条件下的公共查询条件， 有关类型的查询类型查询，
        BoolQueryBuilder brandsBoolQuery = QueryBuilders.boolQuery();
        if (typeNames != null) {
            for (String type : typeNames.split(Constance.SPLIT_TYPES)) { // 类型之间用逗号分隔
                brandsBoolQuery.should(QueryBuilders.termQuery(Constance.FIELD_ZEBRA_TYPE, type));
            }
        }

        // 多个点分别根据传过来的条件进行查询
        // geo 逆编码后的基本信息
        GeoReGeoBaseInfo geoReGeoBaseInfo;
        for(String point:points) {
            geoReGeoBaseInfo = new GeoReGeoBaseInfo();
            // 按照范围查询进行query
            GeoDistanceQueryBuilder geoDistanceQueryBuilder1;
            GeoDistanceQueryBuilder geoDistanceQueryBuilder2 = null;
            geoDistanceQueryBuilder1 = QueryBuilders
                    .geoDistanceQuery(Constance.FIELD_ZEBRA_LOCATION)
                    .point(new GeoPoint(point)).distance(Double.parseDouble(ranges[0])/1000 + "km");
            if (ranges.length >= 2 && Double.parseDouble(ranges[0]) < Double.parseDouble(ranges[1])) {
                geoDistanceQueryBuilder2 = QueryBuilders
                        .geoDistanceQuery(Constance.FIELD_ZEBRA_LOCATION)
                    .point(new GeoPoint(point)).distance(Double.parseDouble(ranges[1])/1000 + "km");
            } else if(ranges.length >= 2 && Double.parseDouble(ranges[0]) >= Double.parseDouble(ranges[1])) {
                geoDistanceQueryBuilder1 = QueryBuilders
                        .geoDistanceQuery(Constance.FIELD_ZEBRA_LOCATION)
                        .point(new GeoPoint(point)).distance(Double.parseDouble(ranges[1])/1000 + "km");
                geoDistanceQueryBuilder2 = QueryBuilders
                        .geoDistanceQuery(Constance.FIELD_ZEBRA_LOCATION)
                        .point(new GeoPoint(point)).distance(Double.parseDouble(ranges[0])/1000 + "km");
            }

            // 按照距离进行排序query 封装
            SortBuilder sortBuilder = SortBuilders
                    .geoDistanceSort(Constance.FIELD_ZEBRA_LOCATION,
                    new GeoPoint(point)).order(SortOrder.ASC);

            // 总的query 对象
            BoolQueryBuilder totalBoolQuery = QueryBuilders.boolQuery();
            totalBoolQuery.must(brandsBoolQuery);
            if (ranges.length >= 2) {
                totalBoolQuery.mustNot(geoDistanceQueryBuilder1).must(geoDistanceQueryBuilder2);
            } else {
                totalBoolQuery.must(geoDistanceQueryBuilder1);
            }


            // 封装请求对象,第一版本中只需要返回其最近的一条内容
            SearchRequestBuilder searchRequestBuilder = null;
            try {
                searchRequestBuilder = new ElasticsearchConfigV1().getObject().prepareSearch(index)
                        .setTypes(types).setQuery(totalBoolQuery)
                        .addSort(sortBuilder).setSize(1);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 获取response 对象,进行数据返回并进行封装
            SearchResponse searchResponse = searchRequestBuilder.get();
            // 获取数据并进行封装
            SearchHits searchHits = searchResponse.getHits();
            // 实际数据存储的数据
            SearchHit[] hits = searchHits.getHits();
            // 返回实际存储最近的一条数据
            if (hits.length > 0) {
                //TODO  下个版本可能需要做的功能
                if (extensions !=null && !"".equals(extensions)
                        && Constance.EXTENSIONS_AOI.equals(extensions)) {
                    geoReGeoInfo.setStatus(Constance.STATUS_FAILED);
                    geoReGeoInfo.setInfo("返回Aoi 信息暂时未支持。");
                    geoReGeoInfos.add(geoReGeoInfo);
                    return  geoReGeoInfos;
                }
                if(StringUtil.isNotEmptyOrNull(hits[0].getSource()
                        .get(Constance.FIELD_ZEBRA_NAME))) {
                    geoReGeoBaseInfo.setName((String) hits[0].getSource().
                            get(Constance.FIELD_ZEBRA_NAME));
                }
                if(StringUtil.isNotEmptyOrNull(hits[0].getSource()
                        .get(Constance.FIELD_ZEBRA_FIRSTLY_CLASSIFICATION))) {
                    geoReGeoBaseInfo.setFirstly_classification((String) hits[0].getSource().
                            get(Constance.FIELD_ZEBRA_FIRSTLY_CLASSIFICATION));
                }
                if(StringUtil.isNotEmptyOrNull(hits[0].getSource()
                        .get(Constance.FIELD_ZEBRA_SECONDARY_CLASSIFICATION))) {
                    geoReGeoBaseInfo.setSecondary_classification((String) hits[0].getSource().
                            get(Constance.FIELD_ZEBRA_SECONDARY_CLASSIFICATION));
                }
                if(StringUtil.isNotEmptyOrNull(hits[0].getSource()
                        .get(Constance.FIELD_ZEBRA_TYPE))) {
                    geoReGeoBaseInfo.setType((String) hits[0].getSource().
                            get(Constance.FIELD_ZEBRA_TYPE));
                }
                if(StringUtil.isNotEmptyOrNull(hits[0].getSource()
                        .get(Constance.FIELD_ZEBRA_PROVINCE))) {
                    geoReGeoBaseInfo.setProvince((String) hits[0].getSource().
                            get(Constance.FIELD_ZEBRA_PROVINCE));
                }
                if(StringUtil.isNotEmptyOrNull(hits[0].getSource()
                        .get(Constance.FIELD_ZEBRA_CITY))) {
                    geoReGeoBaseInfo.setCity((String) hits[0].getSource().
                            get(Constance.FIELD_ZEBRA_CITY));
                }
                if(StringUtil.isNotEmptyOrNull(hits[0].getSource()
                        .get(Constance.FIELD_ZEBRA_CITYCODE))) {
                    geoReGeoBaseInfo.setCitycode((String) hits[0].getSource().
                            get(Constance.FIELD_ZEBRA_CITYCODE));
                }
                if(StringUtil.isNotEmptyOrNull(hits[0].getSource()
                        .get(Constance.FIELD_ZEBRA_DISTRICT))) {
                    geoReGeoBaseInfo.setDistrict((String) hits[0].getSource().
                            get(Constance.FIELD_ZEBRA_DISTRICT));
                }
                if(StringUtil.isNotEmptyOrNull(hits[0].getSource()
                        .get(Constance.FIELD_ZEBRA_ADCODE))) {
                    geoReGeoBaseInfo.setAdcode((String) hits[0].getSource().
                            get(Constance.FIELD_ZEBRA_ADCODE));
                }
                if(StringUtil.isNotEmptyOrNull(hits[0].getSource()
                        .get(Constance.FIELD_ZEBRA_TOWNSHIP))) {
                    geoReGeoBaseInfo.setTownship((String) hits[0].getSource().
                            get(Constance.FIELD_ZEBRA_TOWNSHIP));
                }
                if(StringUtil.isNotEmptyOrNull(hits[0].getSource()
                        .get(Constance.FIELD_ZEBRA_BUSINESS_CIRCLE))) {
                    geoReGeoBaseInfo.setBusiness_circle((String) hits[0].getSource().
                            get(Constance.FIELD_ZEBRA_BUSINESS_CIRCLE));
                }
                if(StringUtil.isNotEmptyOrNull(hits[0].getSource()
                        .get(Constance.FIELD_ZEBRA_FORMATTED_ADDRESS))) {
                    geoReGeoBaseInfo.setFormatted_address((String) hits[0].getSource().
                            get(Constance.FIELD_ZEBRA_FORMATTED_ADDRESS));
                }


                List<PoiInfo> poiInfos = new CopyOnWriteArrayList<>();
                if (extensions !=null && !"".equals(extensions)
                        && Constance.EXTENSIONS_POI.equals(extensions)) {
                    PoiInfo poiInfo = new PoiInfo();
                    poiInfo.setGeoReGeoBaseInfo(geoReGeoBaseInfo);

                    Map<String, Object> otherInfo = (Map<String, Object>) hits[0].getSource()
                            .get(Constance.FIELD_ZEBRA_EXTENSIONS);
                    if(StringUtil.isNotEmptyOrNull(otherInfo.get(Constance.FIELD_ZEBRA_AVG_PRICE))) {
                        poiInfo.setAvgPrice((Double) otherInfo
                                .get(Constance.FIELD_ZEBRA_AVG_PRICE));
                    }
                    if(StringUtil.isNotEmptyOrNull(otherInfo.get(Constance.FIELD_ZEBRA_SHOPS))) {
                        poiInfo.setShops((Integer) otherInfo
                                .get(Constance.FIELD_ZEBRA_SHOPS));
                    }
                    if(StringUtil.isNotEmptyOrNull(otherInfo.get(Constance.FIELD_ZEBRA_GOOD_COMMENTS))) {
                        poiInfo.setGoodComments((Integer) otherInfo
                               .get(Constance.FIELD_ZEBRA_GOOD_COMMENTS));
                    }
                    if(StringUtil.isNotEmptyOrNull(otherInfo.get(Constance.FIELD_ZEBRA_LVL))) {
                        poiInfo.setLevel((Integer) otherInfo
                               .get(Constance.FIELD_ZEBRA_LVL));
                    }
                    if(StringUtil.isNotEmptyOrNull(otherInfo.get(Constance.FIELD_ZEBRA_LEISURE_TYPE))) {
                        poiInfo.setLeisureType((String) otherInfo
                               .get(Constance.FIELD_ZEBRA_LEISURE_TYPE));
                    }
                    if(StringUtil.isNotEmptyOrNull(otherInfo.get(Constance.FIELD_ZEBRA_FUN_TYPE))) {
                        poiInfo.setFunType((String) otherInfo
                               .get(Constance.FIELD_ZEBRA_FUN_TYPE));
                    }
                    if(StringUtil.isNotEmptyOrNull(otherInfo.get(Constance.FIELD_ZEBRA_NUMBERS))) {
                        poiInfo.setNumbers((Integer) otherInfo
                               .get(Constance.FIELD_ZEBRA_NUMBERS));
                    }
                    if(StringUtil.isNotEmptyOrNull(otherInfo.get(Constance.FIELD_ZEBRA_ENERGY_TYPE))) {
                        poiInfo.setShops(Integer.parseInt((String) otherInfo
                               .get(Constance.FIELD_ZEBRA_ENERGY_TYPE)));
                    }
                    poiInfos.add(poiInfo);
                }
                geoReGeoInfo.setStatus(Constance.STATUS_SUCCESS);
                geoReGeoInfo.setInfo("ok");
                geoReGeoInfo.setGeoReGeoBaseInfo(geoReGeoBaseInfo);
                geoReGeoInfo.setPoiInfos(poiInfos);
                geoReGeoInfos.add(geoReGeoInfo);
            } else {
                geoReGeoInfo.setStatus(Constance.STATUS_SUCCESS);
                geoReGeoInfo.setInfo("ok");
                geoReGeoInfos.add(geoReGeoInfo);
            }
        }
        return geoReGeoInfos;
    }

    public BrandsAndBusinessCircles getBrandsAndBusinessCircles(
            String district, String firstly_classification,
            String secondary_classification) {
        return null;
    }
}
