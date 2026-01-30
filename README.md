y# 用户服务

ShopMind 用户中心服务，负责用户信息的完整管理，包括用户档案、凭证、偏好设置、行为埋点等功能。

## 技术栈

- **Java** 17
- **Spring Boot** 3 + **Spring Cloud Alibaba** (Nacos 注册中心/配置中心)
- **数据库**: PostgreSQL + PostGIS (空间数据支持)
- **ORM**: MyBatis Plus
- **对象存储**: AWS S3 SDK
- **地理服务**: 百度地图 Geocoding API

## 核心功能

- 用户信息管理（注册、登录、资料更新）
- 用户凭证管理（密码设置等）
- 用户地址管理（地理位置编码）
- 用户偏好设置
- 用户行为埋点（浏览、收藏、购买等）
- 用户兴趣标签管理

## 启动方式

### 本地启动

```bash
# 配置好 Nacos 和 PostgreSQL 后，直接运行主类
mvn spring-boot:run
```

### Docker 构建

```bash
# 构建镜像
docker build -t shopmind-user-service .

# 运行容器
docker run -p 8080:8080 shopmind-user-service
```

### 环境要求

- JDK 17+
- PostgreSQL (支持 PostGIS 扩展)
- Nacos 服务端