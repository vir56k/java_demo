# 文件说明
* docker-compose.yml 是个启动 mongeoDB 的 docker 容器的配置文件


# 启动docker容器

docker compose up -d


# 进入容器
docker exec -it mongodb_mongo_1 /bin/bash


# 打开进入 mongoDB 的命令行
mongo --host localhost --authenticationDatabase "admin" -u "root" -p'123456'
