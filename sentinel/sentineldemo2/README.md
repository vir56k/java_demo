# sentinel 的控制台

# 1. 简介
> Sentinel 提供一个轻量级的开源控制台，它提供机器发现以及健康情况管理、监控（单机和集群），规则管理和推送的功能。

Sentinel 控制台包含如下功能:

* 查看机器列表以及健康情况：收集 Sentinel 客户端发送的心跳包，用于判断机器是否在线。
* 监控 (单机和集群聚合)：通过 Sentinel 客户端暴露的监控 API，定期拉取并且聚合应用监控信息，最终可以实现秒级的实时监控。
* 规则管理和推送：统一管理推送规则。
* 鉴权：生产环境中鉴权非常重要。这里每个开发者需要根据自己的实际情况进行定制。

# 2. 启动 sentinel 的控制台
### 下载最新版本的控制台 jar 包
从 release 页面 

### 命令行启动
```
java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.8.2.jar 
```