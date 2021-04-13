// 浏览器存储管理
const {
    mockLamps,
    mockLeaveHomeLampIds,
    mockReturnHomeLampIds,
    mockPartyLampIds,
    mockIsParty
} = require('./../mockBackend/mockData');

const keyPre = "IOT_FRONTEND";

function resetData () {
    if (getLampsData() === null)
    {
        setLampsData(mockLamps);
    }
    if (getPartyLampIdsData() === null)
    {
        setPartyLampIdsData(mockPartyLampIds);
    }
    if (getLeaveHomeLampIdsData() === null)
    {
        setLeaveHomeLampIdsData(mockLeaveHomeLampIds);
    }
    if (getReturnHomeLampIdsData() === null)
    {
        setReturnHomeLampIdsData(mockReturnHomeLampIds);
    }
    if (getIsPartyData() === null)
    {
        setIsPartyData(mockIsParty);
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
function setLeaveHomeLampIdsData (lampIds) {
    localStorage.setItem(leaveHomeLampIdsKey, JSON.stringify(lampIds));
}

function getLeaveHomeLampIdsData () {
    return JSON.parse(localStorage.getItem(leaveHomeLampIdsKey));
}

const partyLampIdsKey = `${ keyPre }_partyLampIds`;
function setPartyLampIdsData (lampIds) {
    localStorage.setItem(partyLampIdsKey, JSON.stringify(lampIds));
}

function getPartyLampIdsData () {
    return JSON.parse(localStorage.getItem(partyLampIdsKey));
}

const returnHomeLampIdsKey = `${ keyPre }_returnHomeLampIds`;
function setReturnHomeLampIdsData (lampIds) {
    localStorage.setItem(returnHomeLampIdsKey, JSON.stringify(lampIds));
}

function getReturnHomeLampIdsData () {
    return JSON.parse(localStorage.getItem(returnHomeLampIdsKey));
}

const isPartyKey = `${ keyPre }_isParty`;
function getIsPartyData (isParty) {
    localStorage.setItem(isPartyKey, isParty ? '1' : '0');
}

function setIsPartyData () {
    return isPartyKey.getItem(returnHomeLampIdsKey) === '1';
}

export {
    resetData,
    setLampsData,
    getLampsData,
    setLeaveHomeLampIdsData,
    getLeaveHomeLampIdsData,
    setPartyLampIdsData,
    getPartyLampIdsData,
    setReturnHomeLampIdsData,
    getReturnHomeLampIdsData,
    setIsPartyData,
    getIsPartyData
};
