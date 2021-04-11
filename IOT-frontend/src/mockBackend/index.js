// mock 服务器
const {
    serverPort,
    onUrl,
    offUrl,
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

const log = console.log;

// 启动服务器
const Koa = require('koa');
const Router = require('koa-router');
const bodyParser = require('koa-bodyparser');

const app = new Koa();
const router = new Router();

app.use(bodyParser());

// 处理简单跨域
function allowSimpleOrigin (ctx) {
    ctx.set('Access-Control-Allow-Origin', '*');
}

// 处理跨域
function allowOrigin (ctx) {
    allowSimpleOrigin(ctx);
    ctx.set('Access-Control-Allow-Method', 'POST,GET,OPTIONS');
    ctx.set('Access-Control-Allow-Headers', 'Content-Type');
    ctx.status = 200;
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

router.post(onUrl, async function (ctx) {
    allowSimpleOrigin(ctx);
    const { lampId } = ctx.request.body;
    log(`${ onUrl } lampId:${ lampId }`);
    ctx.status = 204;
});

router.options(onUrl, allowOrigin);

router.post(offUrl, async function (ctx) {
    allowSimpleOrigin(ctx);
    const { lampId } = ctx.request.body;
    log(`${ offUrl } lampId:${ lampId }`);
    ctx.status = 204;
});

router.options(offUrl, allowOrigin);

router.get(getLampsUrl, async function (ctx) {
    allowSimpleOrigin(ctx);
    ctx.body = mockLamps;
});

router.get(getDataChartInfoUrl, async function (ctx) {
    allowSimpleOrigin(ctx);
    ctx.body = mockDataChartInfos;
});

if (addMockServerDelayWait)
{
    app.use(mockServerDelayWait);
}
app.use(router.routes());

app.listen(serverPort);

