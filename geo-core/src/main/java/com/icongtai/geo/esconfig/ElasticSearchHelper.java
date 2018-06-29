package com.icongtai.geo.esconfig;

import org.apache.log4j.Logger;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

public class ElasticSearchHelper {
    private Logger LOG = Logger.getLogger(ElasticSearchHelper.class);

    /**
     * es集群地址
     */
    private String hostName = "47.97.79.158";
    /**
     * 端口
     */
    private String port = "9300";
    /**
     * 集群名称
     */
    private String clusterName = "elasticsearch";

    /**
     * elasticsearch client 内部使用线程个数
     */
    private String poolSize = "5";

    private static TransportClient client;

    public TransportClient getClient() {
        return client;
    }

    private static transient ElasticSearchHelper instance;

    public static ElasticSearchHelper getInstance() {
        if(instance == null) {
            synchronized(ElasticSearchHelper.class) {
                if(instance == null) {
                    instance = new ElasticSearchHelper();
                }
            }
        }
        return instance;
    }

    private ElasticSearchHelper() {
        try {
            // 配置信息
            Settings esSetting = Settings.builder()
                    .put("cluster.name", clusterName)
                    // 增加嗅探机制，找到ES集群
                    .put("client.transport.sniff", true)
                    // 增加线程个数，默认为设置成5
                    .put("thread_pool.search.size", Integer.parseInt(poolSize))
                    .build();

            client = new PreBuiltTransportClient(esSetting);
            InetSocketTransportAddress inetSocketTransportAddress =
                    new InetSocketTransportAddress(InetAddress.getByName(hostName),
                            Integer.valueOf(port));
            client.addTransportAddresses(inetSocketTransportAddress);

        } catch (Exception e) {
            LOG.error("elasticsearch TransportClient create error!!!", e);
        }
    }

}
