# **学生选课系统 Course selection system**

## 一个用于学生选课用的服务系统

## By: -gregPerlinLi-

## 项目描述

### 项目需求

​	通过 JavaEE 与 Tomcat ，在在线环境中开发和设计了一个学生选课系统。在此系统中，我们可以实现学生的选退课，学生，课程信息以及组织架构的管理。该系统面向学生和教务处工作人员，因此设计了两个登录接口

### 使用到的技术

JavaEE、Tomcat、Servlet、MySQL、JDBC、HTML、CSS、JavaScript

### 项目亮点

1. 连接池以及每个连接通过过滤器和 `ThreadLocal` 实现了并发安全
2. 将对数据表的通用操作统一封装到了`BaseDao` 中
3. 对95%以上的`service` 和 `util` 层的代码进行了单元测试
4. 通过一个 `BaseServlet` 实现了根据 URI 调用对应 Controller 对应方法，并统一处理异常
5. 通过过滤器实现了学生和管理员的权限管理
6. 在登录和注册的时候使用了验证码，防止重复提交
7. 所有密码在传输之前均会在前端做好 `MD5` 加密，以保证数据安全

### 目录描述

#### 前端

- **`WEB-INF`：** 放置 Tomcat 服务器配置和一些依赖库文件
- **`pages/admin`：** 放置管理员相关的网页
- **`pages/user`：** 放置学生相关的网页
- **`pages/login`：** 放置登录/注册相关的页面
- **`pages/error`：** 放置错误提示页面
- **`static/css`：** 放置各种样式表文件
- **`static/js`：** 放置各种 JavaScript 脚本文件
- **`static/img`：** 放置各种图片文件

#### 后端

- **`resource`：** 放置各种配置（包括 JDBC 连接和连接池配置）
- **`db`：**  放置数据库文件（在 `resource` 目录下）
- **`com.gregperlinli.pojo`：** 放置与数据库一一对应的实体类
- **`com.gregperlinli.dao`：** 放置数据库或文件读写相关的代码，其中的 `impl` 目录放置所有接口的实现类
- **`com.gregperlinli.service`：** 放置与事务和逻辑处理相关的代码，其中的 `impl` 目录放置所有接口的实现类
- **`com.gregperlinli.web`：** 放置与 Controller 有关的代码
- **`com.gregperlinli.filter`：**  放置与过滤器有关的代码
- **`com.gregperlinli.utils`：** 工具类
- **`com.gregperlinli.test`：** 单元测试类，其中的每一个包分别对应前面的包

### MySQL 数据表结构

| 表                    | 用途             |
| --------------------- | ---------------- |
| **`admin`**           | 存放管理员信息   |
| **`student`**         | 存放学生信息     |
| **`course`**          | 存放课程信息     |
| **`selected_course`** | 存放所有选课信息 |
| **`college`**         | 存放学院信息     |
| **`grade`**           | 存放年级信息     |
| **`classes`**         | 存放班级信息     |

---

## 准备工作

### 基本要求

- **`Java`：** 最低版本为 `16.0.1` 或以上
- **`JavaEE`：** 最低版本为 `8.0` 或以上
- **`Tomcat`：** 最低版本为 `9.0.43`（`10.0` 以上的版本暂不支持）
- **`MySQL`：** 最低版本为 `8.0.22` 或以上

### 必要依赖库

- **`MySQL Connector/j`** 最低版本为 `8.0.25` 或以上
- **`Java Servlet`：** 最低版本为 `4.0.1` 或以上
- **`Junit`：** 最低版本为 `5.7.2` 或以上
- **`Gson`：** 最低版本为 `2.8.7` 或以上
- **`Apache Commons BeanUtils`：** 最低版本为 `20030211.134440` 或以上
- **`Kaptcha`：** 最低版本为 `2.3` 或以上

### 数据库配置

- 运行 `.resource/db/course_selection_system.sql` 导入数据库

### 配置数据库连接

​		JDBC 以及 数据库连接池配置： `.resource/jdbc.properties`

```properties
# 以下为数据库连接池的初始化参数（必填）
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/course_selection_system?rewriteBatchedStatements=true&serverTimezone=Asia/Shanghai
jdbc.username=course_management
jdbc.password=course_management

# 以下为数据库连接池的额外参数（选填）
pool.initialSize=10
pool.incrementalConnections=5
pool.maxActive=50
```

---

## 主要功能

### 用户分类及其权限

1. **普通学生：**
	- 注册学生账号
	- 修改自己的信息
	- 查看所有的课程和开始选课的课程
	- 选课/退选
	- 查看自己的已选课程
2. **管理员：**
	- 管理员信息的增删改查管理
	- 学院信息的增删改查管理
	- 年级信息的增删改查管理
	- 班级信息的增删改查管理
	- 学生信息的增删改查管理
	- 课程信息的增删改查管理
	- 选课信息管理

### 其他功能

- 学生和管理员均可以查看课程的选课状态
- 学生和管理员可以查看自己的登录信息
- 在修改信息的时候，程序会自动将现有信息填充到表格中（密码除外）
- 密码检查功能

---

## 一些问题

1. 在修改学生信息的时候，有时候程序无法正确显示此学生所在的学院/年级/班级
2. 在修改学生/管理员的时候，有时候即使密码为空也是能进行提交修改
3. 在更改学生姓名的时候，选课信息中的选课学生的名字有一定的概率会没有更改
4. 在未登录的情况下有概率会直接进入到学生/管理员界面，不过在刷新之后还是会强制跳转回登录页面

---

## 未来目标

- [ ] 优化代码
- [ ] 使用 `Log4j` 进行日志记录
- [ ] 在页面添加当前时间显示
- [ ] 支持批量导入学院/年级/班级信息
- [ ] 支持批量导出学院/年级/班级信息
- [ ] 将数据导出为 Excel 格式
- [ ] 在 `course` 表中添加课程的上课时间
- [ ] 添加课表查看
- [ ] 添加课程冲突处理
- [ ] 添加教师类用户
- [ ] 实现教师申请开课功能
- [ ] 使用 SSM
