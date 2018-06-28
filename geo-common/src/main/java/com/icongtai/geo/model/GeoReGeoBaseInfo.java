package com.icongtai.geo.model;

/**
 * 地理位置反编码后的基本信息
 */
public class GeoReGeoBaseInfo {
    protected String name; // 店铺或者机构名称
    protected String firstly_classification; //一级地址, 比如旅游，购物，休闲
    protected String secondary_classification; //二级地址，比如购物下的商场，装卖店
    protected String type;   //类型，比如美容医院，宠物医院，
    protected String province; // 省份
    protected String city; // 城市
    protected String citycode; // 城市编号
    protected String district; // 区域
    protected String adcode;  // 区域编号
    protected String township;  // 城镇、街道、小区
    protected String business_circle; // 商圈
    protected String formatted_address; // 详细地址

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstly_classification() {
        return firstly_classification;
    }

    public void setFirstly_classification(String firstly_classification) {
        this.firstly_classification = firstly_classification;
    }

    public String getSecondary_classification() {
        return secondary_classification;
    }

    public void setSecondary_classification(String secondary_classification) {
        this.secondary_classification = secondary_classification;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getTownship() {
        return township;
    }

    public void setTownship(String township) {
        this.township = township;
    }

    public String getBusiness_circle() {
        return business_circle;
    }

    public void setBusiness_circle(String business_circle) {
        this.business_circle = business_circle;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    @Override
    public String toString() {
        return "GeoReGeoBaseInfo{" +
                "name='" + name + '\'' +
                ", firstly_classification='" + firstly_classification + '\'' +
                ", secondary_classification='" + secondary_classification + '\'' +
                ", type='" + type + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", citycode='" + citycode + '\'' +
                ", district='" + district + '\'' +
                ", adcode='" + adcode + '\'' +
                ", township='" + township + '\'' +
                ", business_circle='" + business_circle + '\'' +
                ", formatted_address='" + formatted_address + '\'' +
                '}';
    }
}
