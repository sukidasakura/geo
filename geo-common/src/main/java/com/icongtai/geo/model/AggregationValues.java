package com.icongtai.geo.model;

import java.util.List;

public class AggregationValues {
    private int status;
    private String statusInfo;
    private List<String> aggregationValuses;

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setAggregationValuses(List<String> aggregationValuses) {
        this.aggregationValuses = aggregationValuses;
    }

    public List<String> getAggregationValuses() {
        return aggregationValuses;
    }


}
