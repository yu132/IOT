// mock 服务器
const {
    serverPort,
    onUrl,
    offUrl,
    brightnessUrl,
    colorUrl,
    disconnectUrl,
    getLampsUrl,
    getLeaveHomeLampIdsUrl,
    getReturnHomeLampIdsUrl,
    getPartyLampIdsUrl,
    setLeaveHomeLampIdsUrl,
    setReturnHomeLampIdsUrl,
    setPartyLampIdsUrl,
    leaveHomeUrl,
    returnHomeUrl,
    getIsPartyUrl,
    setIsPartyUrl
} = require('../util/consts/consts.json');

// mock 服务器配置
const {
    addMockServerDelayWait
} = require('./setting.json');

// mock 数据
const {
    mockLamps,
    mockLeaveHomeLampIds,
    mockReturnHomeLampIds,
    mockPartyLampIds
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

router.get(getReturnHomeLampIdsUrl, async function (ctx) {
    allowSimpleOrigin(ctx);
    ctx.body = mockReturnHomeLampIds;
});

router.get(getPartyLampIdsUrl, async function (ctx) {
    allowSimpleOrigin(ctx);
    ctx.body = mockPartyLampIds;
});

router.get(getIsPartyUrl, async function (ctx) {
    allowSimpleOrigin(ctx);
    ctx.body = false;
});

router.get(leaveHomeUrl, async function (ctx) {
    allowSimpleOrigin(ctx);
    ctx.status = 204;
});

router.get(returnHomeUrl, async function (ctx) {
    allowSimpleOrigin(ctx);
    ctx.status = 204;
});

router.options(setLeaveHomeLampIdsUrl, allowOrigin);
router.post(setLeaveHomeLampIdsUrl, async function (ctx) {
    allowSimpleOrigin(ctx);
    const { lampIds } = ctx.request.body;
    log(`${ setLeaveHomeLampIdsUrl } lampIds:${ lampIds }`);
    ctx.status = 204;
});

router.options(setReturnHomeLampIdsUrl, allowOrigin);
router.post(setReturnHomeLampIdsUrl, async function (ctx) {
    allowSimpleOrigin(ctx);
    const { lampIds } = ctx.request.body;
    log(`${ setReturnHomeLampIdsUrl } lampIds:${ lampIds }`);
    ctx.status = 204;
});

router.options(setPartyLampIdsUrl, allowOrigin);
router.post(setPartyLampIdsUrl, async function (ctx) {
    allowSimpleOrigin(ctx);
    const { lampIds } = ctx.request.body;
    log(`${ setPartyLampIdsUrl } lampIds:${ lampIds }`);
    ctx.status = 204;
});

router.options(setIsPartyUrl, allowOrigin);
router.post(setIsPartyUrl, async function (ctx) {
    allowSimpleOrigin(ctx);
    const { isParty } = ctx.request.body;
    log(`${ setIsPartyUrl } isParty:${ isParty }`);
    ctx.status = 204;
});

if (addMockServerDelayWait)
{
    app.use(mockServerDelayWait);
}
app.use(router.routes());

app.listen(serverPort);

