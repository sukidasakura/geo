package com.icongtai.geo.model;

/**
 * 一些常用的常量字段
 */
public class Constance {
    // 请求成功状态返回码
    public final static int STATUS_SUCCESS = 0;
    // 请求失败状态返回码
    public final static int STATUS_FAILED = 1;

    // 传过来的location 经纬度点points 中。point 和point 之间的分隔符号
    public final static String SPLIT_POPINTS = "\\|";
    // 一个点中经度和维度的分隔符号
    public final static String SPLIT_POINT = ",";
    // 搜索范围的分隔线
    public final static String SPLIT_SEARCH_RANGE = "-";
    // 类型分隔符
    public final static String SPLIT_TYPES = ",";


    // 以下是索引相关filed 字段名字
    // name 字段名，
    public final static  String FIELD_ZEBRA_NAME = "name";
    // firstly_classification 一级地址字段名
    public final  static String FIELD_ZEBRA_FIRSTLY_CLASSIFICATION = "firstly_classification";
    // secondary_classification 二级地址字段名，
    public final  static String FIELD_ZEBRA_SECONDARY_CLASSIFICATION = "secondary_classification";
    // 类型名字段
    public final static String FIELD_ZEBRA_TYPE = "type_name";
    // 省份字段名字
    public final  static String FIELD_ZEBRA_PROVINCE = "province";
    // 城市字段名
    public final static String FIELD_ZEBRA_CITY = "city";
    // 城市代码字段名
    public final static String FIELD_ZEBRA_CITYCODE = "citycode";
    // 区域字段名
    public final static String FIELD_ZEBRA_DISTRICT = "district";
    // 区域编码字段
    public final static  String FIELD_ZEBRA_ADCODE = "adcode";
    // 乡镇街道字段名
    public final  static  String FIELD_ZEBRA_TOWNSHIP = "township";
    // bussiness_circle 商圈字段名
    public final static  String FIELD_ZEBRA_BUSINESS_CIRCLE = "business_circle";
    // 详细地址字段名字
    public final static String FIELD_ZEBRA_FORMATTED_ADDRESS = "formatted_address";
    //  geopoint ,地理位置索引字段名
    public final static String FIELD_ZEBRA_LOCATION = "location";
    // 扩展字段
    public final static String FIELD_ZEBRA_EXTENSIONS = "extensions";
    // map_lat 维度字段名
    public final static String FIELD_ZEBRA_MAP_LAT = "map_lat";
    //  map_lng 字段名字
    public final static String FIELD_ZEBRA_MAP_LNG = "map_lng";
    // 均价字段
    public final static String FIELD_ZEBRA_AVG_PRICE = "avg_price";
    // 店铺数字段
    public final static String FIELD_ZEBRA_SHOPS = "shops";
    // 好评字段
    public final static String FIELD_ZEBRA_GOOD_COMMENTS = "good_comments";
    // 星级字段
    public final static String FIELD_ZEBRA_LVL = "lvl";
    // 休闲类型字段
    public final static String FIELD_ZEBRA_LEISURE_TYPE = "leisure_type";
    // 娱乐类型字段
    public final static String FIELD_ZEBRA_FUN_TYPE = "fun_type";
    // 公司规模
    public final static String FIELD_ZEBRA_NUMBERS = "numbers";
    // 能源类型
    public final static String FIELD_ZEBRA_ENERGY_TYPE = "energy_type";


    //以表示是否返回api 或者poi 信息
    public final static String EXTENSIONS_AOI = "aoi";
    public final static String EXTENSIONS_POI = "poi";
}
