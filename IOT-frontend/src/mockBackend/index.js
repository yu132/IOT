// mock 服务器
const {
    serverPort,
    getLampsUrl,
    getDataChartInfoUrl
} = require('../util/consts/consts.json');

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
    // TODO 返回 mock 的灯泡设备数据
    ctx.body = [];
});

router.get(getDataChartInfoUrl, async function (ctx) {
    // TODO 返回 mock 的数据图表信息数据
    ctx.body = [];
});

app.use(allowOrigin);
app.use(mockServerDelayWait);
app.use(router.routes());

app.listen(serverPort);

console.log(`mock server 已启动 localhost:${ serverPort }`);
