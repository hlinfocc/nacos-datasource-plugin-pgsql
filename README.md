[![GitHub release](https://img.shields.io/github/v/tag/hlinfocc/nacos-datasource-plugin-pgsql.svg?label=%E6%9C%80%E6%96%B0%E7%89%88%E6%9C%AC)](https://github.com/hlinfocc/nacos-datasource-plugin-pgsql/releases)
[![MIT License](https://img.shields.io/github/license/hlinfocc/nacos-datasource-plugin-pgsql)](https://github.com/hlinfocc/nacos-datasource-plugin-pgsql/blob/master/LICENSE)

# 简介

nacos-datasource-plugin-pgsql 是Nacos适配Postgresql数据源插件。

# 快速使用

### 1.下载nacos-datasource-plugin-pgsql插件jar:

您可以从最新的稳定版本下载打包好的jar：[点此下载](https://github.com/hlinfocc/nacos-datasource-plugin-pgsql/releases "点此下载")

获取前往maven仓库下载：[点此下载](https://search.maven.org/artifact/net.hlinfo/nacos-datasource-plugin-pgsql "点此下载")

也可以自行打包：

```
git clone https://github.com/hlinfocc/nacos-datasource-plugin-pgsql.git
cd nacos-datasource-plugin-pgsql/
mvn clan package
```

### 2下载nacos-server 

可在nacos的GitHub仓库下载

也可以自行下载nacos源码打包:

```sh
git clone https://github.com/alibaba/nacos.git
cd nacos/
mvn -Prelease-nacos -Dmaven.test.skip=true clean install -U 
echo "打包后的文件位于： ./distribution/target/:"
ls -al ./distribution/target/
```

### 3.插件配置

#### 3.1配置

在nacos安装目录下新建plugins目录，并将nacos-datasource-plugin-pgsql插件jar放入此目录,目录结构如下:

```
nacos/
├── bin
├── conf
├── data
├── derby.log
├── LICENSE
├── logs
├── NOTICE
├── plugins
│   └── nacos-datasource-plugin-pgsql-1.0.1.jar
├── target
│   └── nacos-server.jar
└── work
```

#### 3.2配置数据库链接信息

编辑conf/application.properties配置文件

```yml

#*************** Config Module Related Configurations ***************#
### If use MySQL as datasource:
spring.datasource.platform=pgsql

### Count of DB:
db.num=1

### Connect URL of DB:
db.url.0=jdbc:postgresql://127.0.0.1:5432/nacos?tcpKeepAlive=true&reWriteBatchedInserts=true&ApplicationName=nacos_java
db.user.0=postgres
db.password.0=123456
db.pool.config.driverClassName=org.postgresql.Driver

```

#### 3.3导入nacos-pgsql.sql到PostgreSQL数据库

新建数据库（假如数据库名为nacos）：

```sql
create database nacos;
```

执行导入命令

```sql
psql -U postgres -d nacos -f ./nacos-pgsql.sql
```

#### 3.4启动nacos服务

单机模式启动：

```
cd nacos/
./bin/startup.sh -m standalone
```

# 4.许可证
MIT License 
