// 浏览器存储管理
const {
    mockLamps,
    mockLeaveHomeLampIds,
    mockReturnHomeLampIds,
    mockPartyLampIds
} = require('./../mockBackend/mockData');

const keyPre = "IOT_FRONTEND";

function resetData () {
    if (getLampsData() === null)
    {
        setLampsData(mockLamps);
    }
    if (getPartyLampIds() === null)
    {
        setPartyLampIds(mockPartyLampIds);
    }
    if (getLeaveHomeLampIds() === null)
    {
        setLeaveHomeLampIds(mockLeaveHomeLampIds);
    }
    if (getReturnHomeLampIds() === null)
    {
        setReturnHomeLampIds(mockReturnHomeLampIds);
    }
}

const lampsDataKey = `${ keyPre }_lamps`;
function setLampsData (lamps) {
    localStorage.setItem(lampsDataKey, JSON.stringify(lamps));
}

function getLampsData () {
    return JSON.parse(localStorage.getItem(lampsDataKey));
}

const leaveHomeLampIdsKey = `${ keyPre }_leaveHomeLampIds`;
function setLeaveHomeLampIds (lampIds) {
    localStorage.setItem(leaveHomeLampIdsKey, JSON.stringify(lampIds));
}

function getLeaveHomeLampIds () {
    return JSON.parse(localStorage.getItem(leaveHomeLampIdsKey));
}

const partyLampIdsKey = `${ keyPre }_partyLampIds`;
function setPartyLampIds (lampIds) {
    localStorage.setItem(partyLampIdsKey, JSON.stringify(lampIds));
}

function getPartyLampIds () {
    return JSON.parse(localStorage.getItem(partyLampIdsKey));
}

const returnHomeLampIdsKey = `${ keyPre }_returnHomeLampIds`;
function setReturnHomeLampIds (lampIds) {
    localStorage.setItem(returnHomeLampIdsKey, JSON.stringify(lampIds));
}

function getReturnHomeLampIds () {
    return JSON.parse(localStorage.getItem(returnHomeLampIdsKey));
}

export {
    resetData,
    setLampsData,
    getLampsData,
    setLeaveHomeLampIds,
    getLeaveHomeLampIds,
    setPartyLampIds,
    getPartyLampIds,
    setReturnHomeLampIds,
    getReturnHomeLampIds
};
