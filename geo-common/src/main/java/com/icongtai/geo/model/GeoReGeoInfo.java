package com.icongtai.geo.model;

import java.util.List;

/**
 * 地理位置逆编码返回值
 */
public class GeoReGeoInfo {
    // 请求状态码 0, 成功， 1，失败
    private int status;
    // 当status 为 0 的时候，放回OK， status 为1的时候，返回错误提示
    private String info;
    // 基本信息
    private GeoReGeoBaseInfo geoReGeoBaseInfo;
    // pois 信息列表
    private List<PoiInfo> poiInfos;
    // 区域信息列表
    private List<AoiInfo> aoiInfos;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public GeoReGeoBaseInfo getGeoReGeoBaseInfo() {
        return geoReGeoBaseInfo;
    }

    public void setGeoReGeoBaseInfo(GeoReGeoBaseInfo geoReGeoBaseInfo) {
        this.geoReGeoBaseInfo = geoReGeoBaseInfo;
    }

    public List<PoiInfo> getPoiInfos() {
        return poiInfos;
    }

    public void setPoiInfos(List<PoiInfo> poiInfos) {
        this.poiInfos = poiInfos;
    }

    public List<AoiInfo> getAoiInfos() {
        return aoiInfos;
    }

    public void setAoiInfos(List<AoiInfo> aoiInfos) {
        this.aoiInfos = aoiInfos;
    }

    @Override
    public String toString() {
        return "GeoReGeoInfo{" +
                "status=" + status +
                ", info='" + info + '\'' +
                ", geoReGeoBaseInfo=" + geoReGeoBaseInfo +
                ", poiInfos=" + poiInfos +
                ", aoiInfos=" + aoiInfos +
                '}';
    }
}
