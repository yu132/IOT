// 请求工具
import axios from 'axios';
import {
    serverHost,
    serverPort,
    timeout
} from './consts';

const instance = axios.create({
    baseURL: `http://${ serverHost }:${ serverPort }/`,
    timeout
});

export {
    instance
};
