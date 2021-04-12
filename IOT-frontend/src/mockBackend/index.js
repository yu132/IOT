// mock 服务器
const {
    serverPort,
    onUrl,
    offUrl,
    brightnessUrl,
    colorUrl,
    disconnectUrl,
    getLampsUrl,
    getLeaveHomeLampIdsUrl
} = require('../util/consts/consts.json');

// mock 服务器配置
const {
    addMockServerDelayWait
} = require('./setting.json');

// mock 数据
const {
    mockLamps,
    mockLeaveHomeLampIds
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

router.options(onUrl, allowOrigin);
router.post(onUrl, async function (ctx) {
    allowSimpleOrigin(ctx);
    const { lampId } = ctx.request.body;
    log(`${ onUrl } lampId:${ lampId }`);
    ctx.status = 204;
});

router.options(offUrl, allowOrigin);
router.post(offUrl, async function (ctx) {
    allowSimpleOrigin(ctx);
    const { lampId } = ctx.request.body;
    log(`${ offUrl } lampId:${ lampId }`);
    ctx.status = 204;
});

router.options(brightnessUrl, allowOrigin);
router.post(brightnessUrl, async function (ctx) {
    allowSimpleOrigin(ctx);
    const { lampId, brightness } = ctx.request.body;
    log(`${ brightnessUrl } lampId:${ lampId }, brightness:${ brightness }`);
    ctx.status = 204;
});

router.options(colorUrl, allowOrigin);
router.post(colorUrl, async function (ctx) {
    allowSimpleOrigin(ctx);
    const { lampId, color } = ctx.request.body;
    log(`${ colorUrl } lampId:${ lampId }, color:${ color }`);
    ctx.status = 204;
});

router.options(disconnectUrl, allowOrigin);
router.post(disconnectUrl, async function (ctx) {
    allowSimpleOrigin(ctx);
    const { lampId } = ctx.request.body;
    log(`${ disconnectUrl } lampId:${ lampId }`);
    ctx.status = 204;
});

router.get(getLampsUrl, async function (ctx) {
    allowSimpleOrigin(ctx);
    ctx.body = mockLamps;
});

router.get(getLeaveHomeLampIdsUrl, async function (ctx) {
    allowSimpleOrigin(ctx);
    ctx.body = mockLeaveHomeLampIds;
});

if (addMockServerDelayWait)
{
    app.use(mockServerDelayWait);
}
app.use(router.routes());

app.listen(serverPort);

