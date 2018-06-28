package com.icongtai.geo.esconfig;

import org.apache.log4j.Logger;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 *  Elasticsearch  客户端依赖注入
 */
@Component
public class ElasticsearchConfigV1 {

    private Logger LOG = Logger.getLogger(ElasticsearchConfigV1.class);

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
     * 连接池
     */
    private String poolSize = "5";

    private TransportClient client;


    public TransportClient getObject() {
        return client;
    }

    public ElasticsearchConfigV1() {
        try {
            // 配置信息
            System.out.println("============" + poolSize);
            Settings esSetting = Settings.builder()
                    .put("cluster.name", clusterName)
                    // 增加嗅探机制，找到ES集群
                    .put("client.transport.sniff", true)
                    // 增加线程池个数，默认为设置成5
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
