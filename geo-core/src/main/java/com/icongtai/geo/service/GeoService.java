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

    /**
     * 查询地理位置的接口
     * @param locations 具体点的经纬度,需要支持多点输入，多个点之间用竖线|分隔
     * @param distances 搜索范围，单位米
     * @param types 需要查询的类型，比如美食，商品，医院等。(类型，字段名字需要改成类型，
     *              对应location 表格中的brands)， 可以有多个值，多个值之间由逗号隔开
     * @param firstly_classification 一级地址
     * @param secondary_classification  二级地址
     * @param province 省份
     * @param city  城市
     * @param district 区域
     * @param township 街道小区、城镇
     * @param business_circle 商圈
     * @param formatted_address  详细地址
     * @param avg_price  平均价格，单位元
     * @param shops 店铺数
     * @param good_comments 好评分
     * @param lvl 星级
     * @param leisure_type 休闲类型
     * @param fun_type 娱乐类型
     * @param energy_type 能源类型
     * @param numbers 公司规模
     * @param from 返回附近的点和区域信息的时候，附近aoi和poi 需要从第几条开始返回，
     *             默认返回10条
     * @param size 返回附近的点和区域信息的时候，附近aoi和poi 一共需要返回多少条内容
     *             ，默认返回10条
     * @param extentions 可以不填，不填的时候只返回基础信息（即最近的一个点的信息），
     *                   选择返回aoi则查询aoi信息，选择poi则查询poi信息
     * @param searchGaoDe 是否查询高德，默认值false,即不查高德的信息。
     *                    在自己的库中查询不到内容的时候，可选择查询高德地图。
     * @return 查询Geo 地理信息库后返回的结果
     */
    List<GeoReGeoInfo> getGeoReGeoQuery(
            String locations, String distances, String types,
            String firstly_classification, String secondary_classification,
            String province, String city, String district,
            String township, String business_circle,
            String formatted_address, String avg_price,
            String shops, String good_comments, String lvl,
            String leisure_type, String fun_type,
            String energy_type, String numbers, String from,
            String size, String extentions, boolean searchGaoDe
            );
}
