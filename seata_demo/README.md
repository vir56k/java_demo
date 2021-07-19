
# 访问 service1
提交成功
http://localhost:8081/pay/commit
回滚
http://localhost:8081/pay/rollback

# 访问 service2
http://localhost:8082/count/change?id=1&count=1
