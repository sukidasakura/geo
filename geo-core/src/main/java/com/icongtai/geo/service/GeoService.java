package com.icongtai.geo.service;


import com.icongtai.geo.model.BrandsAndBusinessCircles;
import com.icongtai.geo.model.GeoReGeoInfo;

import java.util.List;

/**
 * 地理位置服务
 */
public interface GeoService {

    /**
     * 地理位置逆编码接口定义
     * @param location 请求传过来的point，支持多个点，点之间用竖线隔开，经度在前
     *    （例子：114.33794980735142,22.67690198190599|116.310454,39.9927339）
     * @param distance （搜索范围， 单位米，例子： 1000， 1000-2000）
     * @param types  类型,(例子:商店， 衣服店）
     * @param extensions 可以不填，不填的时候只返回基础信息（即最近的一个点的信息）
     *     ，选择返回aoi则查询aoi信息，选择poi则查询poi信息
     * @param searchGaoDe 选择是否搜索高德地图，默认不搜索flase
     * @return 逆编码后的返回信息
     */
    List<GeoReGeoInfo> getGeoReGeoInfo(String location, String distance,
                                       String types, String extensions,
                                       boolean searchGaoDe);


    /**
     * 返回有哪些品牌和商圈
     * @param district 表示区域信息，比如西湖区，上城区
     * @param firstly_classification 表示一级地址，比如购物，餐饮，旅游，休闲
     * @param secondary_classification 表示二级地址，
     *                                 比如购物下面的商场，零售店，专卖店
     * @return 去重后的品牌和商圈列表
     */
    BrandsAndBusinessCircles getBrandsAndBusinessCircles(
            String district, String firstly_classification,
            String secondary_classification);
}
