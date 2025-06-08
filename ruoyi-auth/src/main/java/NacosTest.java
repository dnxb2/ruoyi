import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.Properties;
import java.util.concurrent.Executor;

public class NacosTest {

    // Nacos 服务器地址
    private static final String NACOS_SERVER_ADDR = "10.129.0.18:8091";
    // 如果开启鉴权需要填写
    private static final String USERNAME = "nacos";
    private static final String PASSWORD = "nacos";

    public static void main(String[] args) {
        testConfigConnection();
    }

    /**
     * 测试配置中心连接
     */
    private static void testConfigConnection() {
        try {
            // 1. 创建配置服务
            Properties configProps = new Properties();
            configProps.put(PropertyKeyConst.SERVER_ADDR, NACOS_SERVER_ADDR);
            configProps.put(PropertyKeyConst.USERNAME, USERNAME);
            configProps.put(PropertyKeyConst.PASSWORD, PASSWORD);

            ConfigService configService = NacosFactory.createConfigService(configProps);

            // 2. 定义测试配置
            String dataId = "application-dev.yml";
            String group = "DEFAULT_GROUP";
            // 4. 获取配置
            String receivedContent = configService.getConfig(dataId, group, 5000);
            System.out.println("[Config] 获取配置: " + receivedContent);



            System.out.println("✅ 配置中心连接测试成功");
        } catch (NacosException e) {
            System.err.println("❌ 配置中心连接失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}