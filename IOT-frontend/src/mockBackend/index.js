// mock 服务器
const {
    serverPort,
    getLampsUrl,
    getDataChartInfoUrl
} = require('../util/consts/consts.json');

// mock 服务器配置
const {
    addMockServerDelayWait
} = require('./setting.json');

// mock 数据
const {
    mockLamps,
    mockDataChartInfos
} = require('./mockData');

// 启动服务器
const Koa = require('koa');
const Router = require('koa-router');
const bodyParser = require('koa-bodyparser');

const app = new Koa();
const router = new Router();

app.use(bodyParser());

// 处理跨域
async function allowOrigin (ctx, next) {
    ctx.set('Access-Control-Allow-Origin', '*');
    await next();
}

async function mockServerDelayWait (_, next) {
    const now = Date.now();
    const mockDelay = 1000 + Math.random() * 500;
    while (Date.now() - now < mockDelay)
    {
        // mock wait
    }
    await next();
}

router.get(getLampsUrl, async function (ctx) {
    ctx.body = mockLamps;
});

router.get(getDataChartInfoUrl, async function (ctx) {
    ctx.body = mockDataChartInfos;
});

app.use(allowOrigin);
if (addMockServerDelayWait)
{
    app.use(mockServerDelayWait);
}
app.use(router.routes());

app.listen(serverPort);

