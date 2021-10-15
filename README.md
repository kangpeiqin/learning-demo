## Redis 延迟队列的实现
- `windows`系统中 `Kafka` 相关操作
> 启动 `Zookeeper`
```
bin\windows\zookeeper-server-start.bat config\zookeeper.properties
```
> 启动 `Kafka`
```
bin\windows\kafka-server-start.bat config\server.properties
```
> 创建 `Topic` 
```
bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
```
> 查看 `Topic` 列表
```
bin\windows\kafka-topics.bat --list --zookeeper localhost:2181
```
## 分布式锁的实现
- 基于 `Redisson`
## Spring Cloud
- 服务治理 Eureka
- 声明式服务调用 Feign
- 服务容错(降级与熔断) Hystrix
- 客户端负载均衡 Ribbon
- 服务网关 Zuul
- 微服务跟踪 Sleuth