# 基于微信小程序酒馆点单系统的设计与实现

## 一、项目简介
本项目是一个基于微信小程序的酒馆点单系统，旨在为酒馆提供便捷的点单服务，提高运营效率和顾客体验。系统包括微信小程序前端和Spring Boot后端，实现了用户登录注册、商品展示、点单下单、订单管理、桌号选择等功能。

## 二、项目技术栈
### 后端
- **核心框架**：Spring Boot 2.1.0，用于快速构建Java后端应用。
- **ORM框架**：MyBatis 1.3.2，实现数据库与Java对象的映射。
- **分页插件**：PageHelper 1.2.10，简化分页查询逻辑。
- **数据库**：MySQL 8.0+，存储业务数据。
- **工具类库**：Hutool 5.3.7，提供字符串处理、文件操作等通用功能。
- **构建工具**：Maven，管理项目依赖和构建。

### 前端
- **微信小程序**：作为用户交互的前端界面。
- **Axios**：用于前后端数据交互。
- **Vue.js 2.6.11**：可能用于部分前端组件或兼容处理。

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
    │   │   ├── category
    │   │   ├── goodsInfo
    │   │   ├── index
    │   │   ├── login
    │   │   ├── orderDetail
    │   │   ├── orderInfo
    │   │   ├── pay
    │   │   ├── register
    │   │   ├── search
    │   │   ├── user
    │   │   └── addressOperate
    │   ├── request
    │   │   ├── config.js
    │   │   └── index.js
    │   ├── app.js
    │   └── app.json
    └── springboot
        ├── src
        │   ├── main
        │   │   ├── java
        │   │   │   └── com
        │   │   │       └── example
        │   │   │           ├── controller
        │   │   │           ├── service
        │   │   │           ├── dao
        │   │   │           └── entity
        │   │   └── resources
        │   │       ├── mapper
        │   │       ├── static
        │   │       │   └── end
        │   │       │       ├── css
        │   │       │       ├── js
        │   │       │       └── page
        │   │       └── application.yml
        └── target
```

## 四、功能模块
### 1. 用户模块
- **登录注册**：用户可以使用用户名和密码进行登录和注册。
- **个人信息管理**：用户可以查看和修改个人信息。

### 2. 商品模块
- **商品展示**：展示酒馆的商品列表，包括推荐商品和热卖商品。
- **商品分类**：按照商品类别进行分类展示。

### 3. 点单模块
- **加入购物车**：用户可以将商品加入购物车。
- **结算下单**：用户可以在购物车中结算商品并下单。

### 4. 订单模块
- **订单列表**：用户可以查看自己的订单列表。
- **订单详情**：用户可以查看订单的详细信息。
- **订单删除**：用户可以删除已完成或取消的订单。

### 5. 桌号管理模块
- **桌号选择**：用户可以选择就餐的桌号。
- **桌号编辑和删除**：用户可以编辑和删除已选择的桌号。

## 五、部署步骤
### 1. 数据库部署
- 执行 `a_wx_shop.sql` 脚本，创建数据库和表结构。
- 修改 `application.yml` 中的数据库连接信息：
```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: 'zyd20030724'
    url: jdbc:mysql://localhost:3306/a_wx_shop?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2b8
```

### 2. 后端部署
- 使用Maven构建项目：`mvn clean package`。
- 运行Spring Boot应用：`java -jar target/your-project-name.jar`。

### 3. 前端部署
- 打开微信开发者工具，导入 `orders/front` 项目。
- 修改 `request/config.js` 中的后端接口地址：
```javascript
export const config = {
  baseFileUrl: 'http://192.168.1.102:8888/files/download/'
}
```

## 六、注意事项
- 确保MySQL数据库服务已启动，并且数据库连接信息正确。
- 项目中使用了机器码校验，确保在授权环境下运行。
- 前端项目在微信开发者工具中运行时，需要配置好相应的AppID。

## 七、总结
本项目实现了一个完整的微信小程序酒馆点单系统，通过前后端分离的架构，提高了系统的可维护性和扩展性。系统功能丰富，操作简单，能够满足酒馆的日常点单需求。

## 八、致谢
感谢老师和同学们在毕业设计过程中给予的帮助和支持。
