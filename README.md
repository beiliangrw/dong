# 微信商城项目 README

## 一、项目概述
本项目是一个基于微信小程序和 Spring Boot 的商城系统，包含前端小程序和后端服务两部分。前端提供用户界面，包括商品展示、订单管理、用户信息等功能；后端使用 Spring Boot 搭建服务，结合 MyBatis 进行数据库操作，实现业务逻辑处理。

## 二、技术栈

### 前端
- **微信小程序**：构建用户界面，提供良好的用户体验。
- **Vue.js 2.6.11**：用于前端页面的交互和数据绑定。
- **Axios 0.19.2**：处理前后端数据交互，发送 HTTP 请求。

### 后端
- **Spring Boot 2.1.0**：快速构建后端服务，简化开发流程。
- **MyBatis 1.3.2**：实现数据库的映射和操作。
- **PageHelper 1.2.10**：用于分页查询，提高数据查询效率。
- **MySQL 8.0+**：作为项目的数据库，存储业务数据。

### 工具
- **Maven**：项目构建和依赖管理工具。
- **IntelliJ IDEA**：后端开发工具。
- **微信开发者工具**：前端小程序开发和调试工具。

## 三、项目结构
```
project
├── 使用前必看.docx
├── a_wx_shop.sql
└── orders
    ├── front
    │   ├── pages
    │   │   ├── address
    │   │   ├── cartInfo
    │   │   ├── comment
    │   │   ├── index
    │   │   ├── login
    │   │   ├── order
    │   │   ├── orderDetail
    │   │   ├── pay
    │   │   ├── register
    │   │   ├── search
    │   │   └── user
    │   ├── sitemap.json
    │   └── project.private.config.json
    └── springboot
        ├── src
        │   ├── main
        │   │   ├── java
        │   │   │   └── com
        │   │   │       └── example
        │   │   │           ├── controller
        │   │   │           ├── dao
        │   │   │           ├── entity
        │   │   │           └── service
        │   │   └── resources
        │   │       ├── mapper
        │   │       ├── static
        │   │       │   └── end
        │   │       │       └── js
        │   │       │           ├── vue2.6.11
        │   │       │           └── custom.js
        │   │       └── application.yml
        ├── target
        │   ├── classes
        │   │   ├── mapper
        │   │   └── static
        │   │       └── end
        │   │           └── js
        │   │               ├── vue2.6.11
        │   │               └── custom.js
        ├── pom.xml
        └── .idea
```

## 四、功能模块

### 前端功能
1. **用户登录与注册**：用户可以通过输入用户名和密码进行登录，未注册用户可以进行注册。
2. **商品展示**：展示商品列表、热卖商品、推荐商品等，用户可以点击商品查看详情。
3. **购物车**：用户可以将商品加入购物车，进行结算操作。
4. **订单管理**：用户可以查看订单列表，包括待付款、待收货、已完成等状态的订单。
5. **商品评价**：用户可以对购买的商品进行评价。
6. **充值余额**：用户可以充值余额用于支付订单。

### 后端功能
1. **用户管理**：处理用户的登录、注册、信息查询等操作。
2. **商品管理**：商品的增删改查，包括商品信息、库存、销量等。
3. **订单管理**：订单的创建、查询、更新等操作，处理订单支付、发货等业务逻辑。
4. **数据库操作**：通过 MyBatis 实现与 MySQL 数据库的交互，包括数据的增删改查。

## 五、环境搭建

### 后端环境搭建
1. **安装 JDK 1.8**：确保系统中安装了 Java 开发环境。
2. **安装 MySQL 8.0+**：创建数据库 `a_wx_shop`，执行 `a_wx_shop.sql` 脚本初始化数据库表结构。
3. **配置数据库连接**：在 `springboot/src/main/resources/application.yml` 中配置数据库连接信息：
```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: 'zyd20030724'
    url: jdbc:mysql://localhost:3306/a_wx_shop?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2b8
```
4. **导入项目**：使用 IntelliJ IDEA 导入 `springboot` 项目，等待 Maven 自动下载依赖。
5. **启动项目**：运行 `com.example.Application` 类启动 Spring Boot 服务。

### 前端环境搭建
1. **安装微信开发者工具**：从微信官方网站下载并安装微信开发者工具。
2. **导入项目**：在微信开发者工具中导入 `front` 项目。
3. **配置项目信息**：在 `front/project.private.config.json` 中配置项目信息。
4. **启动项目**：在微信开发者工具中点击运行按钮，启动小程序。

## 六、接口文档

### 用户接口
- **登录接口**：`POST /user/login`
- **注册接口**：`POST /user/register`
- **查询用户信息接口**：`GET /user/info/{id}`

### 商品接口
- **获取商品列表接口**：`GET /goodsInfo/list`
- **获取热卖商品接口**：`GET /goodsInfo/hot`
- **获取推荐商品接口**：`GET /goodsInfo/recommend`

### 订单接口
- **创建订单接口**：`POST /orderInfo/add`
- **查询订单信息接口**：`GET /orderInfo/{id}`
- **查询订单列表接口**：`GET /orderInfo/list`

## 七、注意事项
1. 确保数据库服务正常运行，数据库连接信息配置正确。
2. 前端小程序需要在微信开发者工具中运行，确保网络连接正常。
3. 项目中使用的依赖版本可能需要根据实际情况进行调整。

## 八、贡献者
北凉

## 九、许可证
本项目采用 [MIT 许可证](LICENSE)。
