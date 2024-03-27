/** 说明：删除 order_bottleneck_test 数据库，并重建它，然后创建三张表 */

drop database if exists order_bottleneck_test;
create database order_bottleneck_test;
use order_bottleneck_test;

create table goods
(
    id        Int32 comment '主键',
    name      String comment '名称',
    price     Decimal(10, 2) comment '价格',
    stock     Int32 comment '库存',
    is_on     Int8 comment '是否上架',
    first_img String comment '第1张图片',
    intro Nullable(String) comment '详情',
    add_time  DateTime comment '添加时间'
)
    engine = Memory comment '商品';

create table order
(
    id              Int32 comment '主键',
    sn              String comment '订单号',
    user_id         Int32 comment '用户ID',
    name            String comment '下单人姓名',
    shipping_mobile String comment '收货地址中的手机号',
    shipping_name   String comment '收货地址中的姓名',
    shipping_detail String comment '收货地址',
    total_amount    Decimal(10, 2) comment '订单总金额',
    status          Int8 comment '状态：1-待支付，2-待发货，3-待收货，4-已完成，5-已取消',
    add_time        DateTime comment '下单时间'
)
    engine = Memory comment '主键';

create table order_item
(
    id           Int32 comment '主键',
    order_id     Int32 comment '订单ID',
    goods_id     Int32 comment '商品ID',
    goods_count  Int32 comment '商品数量',
    goods_price  Decimal(10, 2) comment '商品单价',
    goods_img    String comment '商品图片',
    total_amount Decimal(10, 2) comment '单商品总金额'
)
    engine = Memory comment '订单项';

INSERT INTO `goods` (`id`, `name`, `price`, `stock`, `is_on`, `first_img`, `intro`, `add_time`) VALUES (1, '锤子（smartisan ) 坚果 Pro 2S 6G+64GB 炫光蓝 全面屏双摄 全网通4G手机 双卡双待 游戏手机', 1998.00, 100001, 1, 'https://resource.smartisan.com/resource/25cc6e783a664fbdf83c3c34774a9826.png', 'https://resource.smartisan.com/resource/25cc6e783a664fbdf83c3c34774a9826.png', '2024-03-14 17:38:13');
INSERT INTO `goods` (`id`, `name`, `price`, `stock`, `is_on`, `first_img`, `intro`, `add_time`) VALUES (2, '坚果 R2 浅黑色 8G+128G', 2699.00, 100001, 1, 'https://resource.smartisan.com/resource/4d9e7683b590cf4a6996d3b13136bcf8.png?x-oss-process=image/resize,w_791/format,webp', 'https://resource.smartisan.com/resource/4d9e7683b590cf4a6996d3b13136bcf8.png?x-oss-process=image/resize,w_791/format,webp', '2024-03-14 18:22:53');
INSERT INTO `goods` (`id`, `name`, `price`, `stock`, `is_on`, `first_img`, `intro`, `add_time`) VALUES (3, 'Smartisan TNT go 有线版', 1199.00, 100001, 1, 'https://resource.smartisan.com/resource/2d68b229cc7cafbb1e74bc0f996fa57c.png?x-oss-process=image/resize,w_791/format,webp', 'https://resource.smartisan.com/resource/2d68b229cc7cafbb1e74bc0f996fa57c.png?x-oss-process=image/resize,w_791/format,webp', '2024-03-14 18:24:35');
INSERT INTO `goods` (`id`, `name`, `price`, `stock`, `is_on`, `first_img`, `intro`, `add_time`) VALUES (4, 'Smartisan 手机支架', 39.00, 100001, 1, 'https://resource.smartisan.com/resource/8b0fe3117164dab7d91439b93dc112e0.png?x-oss-process=image/resize,w_791/format,webp', 'https://resource.smartisan.com/resource/8b0fe3117164dab7d91439b93dc112e0.png?x-oss-process=image/resize,w_791/format,webp', '2024-03-14 18:25:52');
INSERT INTO `goods` (`id`, `name`, `price`, `stock`, `is_on`, `first_img`, `intro`, `add_time`) VALUES (5, 'Smartisan 智能手写笔', 499.00, 100001, 1, 'https://resource.smartisan.com/resource/c9a55fe8b5bc506fec60659aa2dcebe9.png?x-oss-process=image/resize,w_791/format,webp', 'https://resource.smartisan.com/resource/c9a55fe8b5bc506fec60659aa2dcebe9.png?x-oss-process=image/resize,w_791/format,webp', '2024-03-14 18:26:32');
