package com.icongtai.geo.esconfig;

import org.apache.log4j.Logger;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 *  Elasticsearch  客户端依赖注入
 */
@Configuration
@Component
public class ElasticsearchConfig implements FactoryBean<TransportClient>,
        InitializingBean, DisposableBean {

    private Logger LOG = Logger.getLogger(ElasticsearchConfig.class);

    /**
     * es集群地址
     */
    @Value("${elasticsearch.ip}")
    private String hostName;
    /**
     * 端口
     */
    @Value("${elasticsearch.port}")
    private String port;
    /**
     * 集群名称
     */
    @Value("${elasticsearch.cluster.name}")
    private String clusterName;

    /**
     * 连接池
     */
    @Value("${elasticsearch.pool}")
    private String poolSize;

    private TransportClient client;


    @Override
    public TransportClient getObject() throws Exception {
        return client;
    }

    @Override
    public Class<TransportClient> getObjectType() {
        return TransportClient.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            // 配置信息
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

    @Override
    public void destroy() throws Exception {
        try {
            LOG.info("Closing elasticSearch client");
            if (client != null) {
                client.close();
            }
        } catch (final Exception e) {
            LOG.error("Error closing ElasticSearch client: ", e);
        }
    }
}
