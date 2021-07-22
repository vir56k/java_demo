REDIS_HOME=/Users/zhangyunfei/chengxu/redis-6.2.4

######################  box1 ##############################
# 启动 box1 里的 redis
run_box1_redis:
	@ECHO "redis home 安装文件位于： $(REDIS_HOME)"
	@ECHO "hello redis #1"
	$(REDIS_HOME)/src/redis-server ./box1/redis.conf

# 启动 box1 里的 sentinel
run_box1_sentinel:
	$(REDIS_HOME)/src/redis-sentinel ./box1/sentinel.conf

look_redis1:
	$(REDIS_HOME)/src/redis-cli -p 6379

######################  box2 ##############################

# 启动 box2 里的 redis
run_box2_redis:
	@ECHO "hello redis #2"
	$(REDIS_HOME)/src/redis-server ./box2/redis.conf

# 启动 box2 里的 sentinel
run_box2_sentinel:
	$(REDIS_HOME)/src/redis-sentinel ./box2/sentinel.conf

######################  box3 ##############################
# 启动 box3 里的 redis
run_box3_redis:
	@ECHO "hello redis #3"
	$(REDIS_HOME)/src/redis-server ./box3/redis.conf

# 启动 box3 里的 sentinel
run_box3_sentinel:
	$(REDIS_HOME)/src/redis-sentinel ./box3/sentinel.conf
