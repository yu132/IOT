# iot-frontend

## 项目初始化

需要先安装 node 和 npm 依赖，然后在本目录下运行以下代码安装项目运行所需的依赖。

```
npm install
```

## 启动项目

使用以下命令启动前端项目

```
npm run serve
```

启动完成后即可根据提示信息访问页面，开始使用本项目，默认启动路径为 [http://localhost:8080](http://localhost:8080) 。

默认使用前端基于 `浏览器存储` 的 mock 数据，可以修改 [consts 文件](./src/util/consts/consts.json) 中的 `useMock` 常量为 `false` 来启动连接后端。

目前暂时请求前端搭设的 mock 服务器，需要使用以下命令启动前端 mock 服务器。

```
npm run mock-backend
```

如果需要连接真实后端，需要修改 [consts 文件](./src/util/consts/consts.json) 中的 `serverHost` 常量和 `serverPort` 来设定后端服务器配置。
