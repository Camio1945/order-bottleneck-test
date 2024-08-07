# order-bottleneck-test
提交订单瓶颈测试。

***

# 目标

1. 写一个极简的 api 接口，用于完成：插入一条订单（order）、插入 1~5 条订单商品（order_goods）、修改商品库存（goods）。确保库存不会减为负数，同时确保订单商品表商品数量等于商品表库存减少的数量。

2. 单机安装 MySQL 8.0，测试：

   a. 性能瓶颈是 CPU 还是硬盘。

   b. 阿里云服务器上 10000 iops 的固态硬盘与 50000 iops 的固态硬盘对性能的影响。

3. 单机安装 ClickHouse，测试：

   a. 跟 MySQL 的表现作对比。

4. 多机器安装分布式的 TiDB，测试：

   a. 随着机器数量增加，每秒提交的订单数的变化情况是怎样的。

***

# 分支说明

master：单机 MySQL 版本，不依赖 Redis

postgresql：单机 PostgreSQL 版本，不依赖 Redis

mysql-redis：单机 MySQL 版本，依赖 Redis

clickhouse：单机 ClickHouse 版本

tidb：集群 TiDB 版本，依赖 Redis

***

# 使用


## 1. master 分支

单机 MySQL 版本，不依赖 Redis：

1. 自行安装 MySQL 8.0 。
2. 使用 MySQL 的 root 用户执行 order-bottleneck-test-api/init_sql/1.order-bottleneck-test-mysql.sql 文件。
3. 使用 Intellij Idea 打开 order-bottleneck-test-api 项目，修改其中的 order-bottleneck-test-api/src/main/resources/application.yml 中的数据库 IP 。

---

# 压测

JMeter 压测脚本文件路径：order-bottleneck-test-api/jmeter_scripts/order-bottleneck-test-api.jmx

---

## MySQL 示例

1. 安装 MySQL 并初始化数据库：略
2. 启动 Java ，注意修改 `-Xms` 、 `-Xmx` 、`--spring.datasource.url` 以适配自己的测试环境

```sh
java -jar -Xms1G -Xmx14G order-bottleneck-test-api-mysql.jar --spring.datasource.url="jdbc:mysql://172.24.79.174:3306/order_bottleneck_test?useSSL=false&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&serverTimezone=GMT%2B8&nullCatalogMeansCurrent=true&allowPublicKeyRetrieval=true"
```

3. 压测，注意修改 `JVM_ARGS` 和 `Jip` 以适配自己的测试环境

```sh
JVM_ARGS="-Xms1G -Xmx7G" jmeter -n -t order-bottleneck-test-api.jmx -Jthreads=20 -Jrampup=2 -Jduration=180 -Jip=172.24.79.173 -l jmeter_res_threads20_rampup2_duration180.mysql.jtl
```

---

## PostgreSQL 示例

1. 安装 PostgreSQL 并初始化数据库：略
2. 启动 Java ，注意修改 `-Xms` 、 `-Xmx` 、`--spring.datasource.url` 以适配自己的测试环境

```sh
java -jar -Xms1G -Xmx14G order-bottleneck-test-api-postgresql.jar --spring.datasource.url="jdbc:postgresql://172.24.79.174:5432/order_bottleneck_test"
```

3. 压测，注意修改 `JVM_ARGS` 和 `Jip` 以适配自己的测试环境

```sh
JVM_ARGS="-Xms1G -Xmx7G" jmeter -n -t order-bottleneck-test-api.jmx -Jthreads=20 -Jrampup=2 -Jduration=180 -Jip=172.24.79.173 -l jmeter_res_threads20_rampup2_duration180.postgresql.jtl
```

