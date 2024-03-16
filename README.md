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

# 使用

以 MySQL 为例：

1. 自行安装 MySQL 8.0 。
2. 使用 MySQL 的 root 用户执行 order-bottleneck-test-api/init_sql/1.order-bottleneck-test-mysql.sql 文件。
3. 使用 Intellij Idea 打开 order-bottleneck-test-api 项目，修改其中的 order-bottleneck-test-api/src/main/resources/application.yml 中的数据库 IP 。