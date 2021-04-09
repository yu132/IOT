// 请求工具
import axios from 'axios';
import { consts } from './consts';
const {
    serverHost,
    serverPort,
    timeout
} = consts;

const instance = axios.create({
    baseURL: `http://${ serverHost }:${ serverPort }/`,
    timeout
});

export {
    instance
};
