import axios from 'axios'
import qs from 'qs'
import debug from '../../config/debug.json'
import {Msg} from './message'
import {store} from '../store'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css'// progress bar style
import {ResponseCode} from '../tools/ResponseCode';
import {Tip} from '../tools/systemConstants';
import {Auth} from '../store/user/auth';

/**
 * 请求类
 */
export class BaseApi {
  servers;
  TOKEN_PREFIX;

  /**
   * 初始化servers
   * @param hot
   */
  constructor(hot) {
    const headers = new Headers();
    this.TOKEN_PREFIX ="Bearer ";
    headers.set('App-Version', '0.1.0');
    this.servers = axios.create({
      baseURL: debug.servers,
      headers,
      timeout: 5000
    });
    this.servers.defaults.timeout = 5000;
    //请求拦截可以 如果失效 可以返回登录页面
    this.servers.interceptors.request.use(function (config) {
      NProgress.configure({showSpinner: false,speed: 1000}); // NProgress Configuration
      NProgress.start();
      return config
    }, function (error) {
      Msg.error(error);
      return Promise.reject(error);
    });
    this.servers.interceptors.response.use(function (response) {
      NProgress.done();
      //如果已经过期
      if(response.data.code ==ResponseCode.INVALID_TOKEN){
        Auth.ACCOUNT_LOGOUT_FAILURE();
        Msg.info(Tip.INVALID_TOKEN);
        window.location = "#/login";
      }
      return response
    }, function (error) {
      switch (error.response.status) {
        case ResponseCode.TIME_OUT:
          error.response.data.message = Tip.TIME_OUT_ERROR;
          break;
        case ResponseCode.ERROR:
          error.response.data.message = Tip. SERVER_ERROR;
          break;
      }
      return Promise.reject(error.response.data.message || Tip.TIME_OUT_ERROR );
    })
  }

  /**
   * fetch
   * @param method
   * @param url
   * @param body
   * @returns {Promise<any>}
   */

  connection(method = 'GET', url, body, fileList, fileKey = 'files') {
    this.getStatusToken();

    if (typeof body !== 'object') body = {};
    method = method.toLocaleLowerCase(); //全部变成小写
    if (fileList && (fileList instanceof Array)) {
      let headers = {'Content-Type': 'multipart/form-data'};
      const param = new window.FormData();
      for (const key in body) {
        if (Object.prototype.hasOwnProperty.call(body, key)) param.append(key, body[key]);
      }
      fileList.forEach(file => param.append(fileKey, file));
      return Promise.resolve(this.servers[method](url, param, {headers}))
    }
    if (method === 'get'|| method=== 'post'|| method=== 'delete' || method === 'put'){
      url = `${url}?${qs.stringify(body)}`;
      body = {}
    }
    this.servers.defaults.headers['Access-Control-Allow-Origin'] ="*";

    return Promise.resolve(this.servers[method](url, body))
  }

  /**
   *
   * post请求
   */
  connectionPost(method = 'post', url, body) {
    this.reset();

    if (typeof body !== 'object') body = {};
    axios.interceptors.response.use(function (response) {
      return response
    }, function (error) {
      Msg.error(error.responsdata.message);
      return Promise.reject(error.response.data.message);
    })

    return axios({
      method:method,
      url:debug.servers+url,
      headers:{
        'Content-type': 'application/x-www-form-urlencoded'
      },
      data: qs.stringify(body)  //将对象解析成URL的形式

    })
  }

   reset() {
     delete axios.defaults.headers.common['Authorization'];
     axios.defaults.timeout = 10000;
   }



  /**
   * 设置token
   * @param isLogin
   * @param token
   */
  setToken({isLogin, token}) {
    if (isLogin) {
      this.servers.defaults.headers.common['Authorization'] =this.TOKEN_PREFIX + token;
    }
    else{
       delete this.servers.defaults.headers.common['Authorization'];
    }

  }

  /**
   * 获取登录状态，token值
   * @returns {{isLogin: string, token: string}}
   */
  getStatusToken() {
    const {isLogin, token} = store.getters;
    this.setToken({isLogin, token})
  }
}

export const server = new BaseApi();
