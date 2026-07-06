// https://github.com/YunaiV/yudao-cloud/tree/38e73c2b10b868431cd8e1238fc61f031571247c/yudao-module-iot/yudao-module-iot-biz/src/main/java/cn/iocoder/yudao/module/iot/service/rule/action/databridge/IotRedisStreamMQDataBridgeExecute.java#L50-L78
public class TempClass {
    @Override
    protected RedisTemplate<String, Object> initProducer(IotDataBridgeRedisStreamMQConfig config) {
        // 1.1 创建 Redisson 配置
        Config redissonConfig = new Config();
        SingleServerConfig serverConfig = redissonConfig.useSingleServer()
                .setAddress("redis://" + config.getHost() + ":" + config.getPort())
                .setDatabase(config.getDatabase());
        // 1.2 设置密码（如果有）
        if (StrUtil.isNotBlank(config.getPassword())) {
            serverConfig.setPassword(config.getPassword());
        }

        // TODO @huihui：看看能不能简化一些。按道理说，不用这么多的哈。
        // 2.1 创建 RedissonClient
        RedissonClient redisson = Redisson.create(redissonConfig);
        // 2.2 创建并配置 RedisTemplate
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 设置 RedisConnection 工厂。😈 它就是实现多种 Java Redis 客户端接入的秘密工厂。感兴趣的胖友，可以自己去撸下。
        template.setConnectionFactory(new RedissonConnectionFactory(redisson));
        // 使用 String 序列化方式，序列化 KEY 。
        template.setKeySerializer(RedisSerializer.string());
        template.setHashKeySerializer(RedisSerializer.string());
        // 使用 JSON 序列化方式（库是 Jackson ），序列化 VALUE 。
        template.setValueSerializer(buildRedisSerializer());
        template.setHashValueSerializer(buildRedisSerializer());
        template.afterPropertiesSet();// 初始化
        return template;
    }


}