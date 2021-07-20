
# 使用 docker 启动 rabbitMQ的方法：

```
docker run -d --hostname my-rabbit --name rabbit -p 15672:15672 -p 5672:5672 -e RABBITMQ_DEFAULT_USER=center -e RABBITMQ_DEFAULT_PASS=123qwe -e RABBITMQ_DEFAULT_VHOST=center  rabbitmq:management

```

通过上面的指令创建一个 RabitMQ：
	* 账户：center
	* 密码：123qwe
	* v-host: center
	* 15672：控制台端口号
	* 5672：应用访问端口号
	* 管理web 的端口：http://ip:15672

# 参考：

https://hub.docker.com/_/rabbitmq/
