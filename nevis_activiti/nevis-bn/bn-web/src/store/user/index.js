/**
 * user
 */

import {LoginApi} from '@/view/login/login-api';
import {ResponseCode} from '../../tools/ResponseCode';
import {Auth} from './auth';

const defaultUser = {username: '', password: '', remember: false};

export default {
  state: {
    /** token */
    token: Auth.getToken() || null,
    /** 用户登陆角色信息 */
    userInfo: Auth.getUserInfo() || {},
    /** 用户登陆账户密码 */
    accountPwd: Auth.getAccountPwd() || defaultUser ,
    /** 是否登陆 */
    isLogin: Auth.getLogin() || false,

    menuInfo : Auth.getMenu() || {},

    Authorities : Auth.getAuthorities() || []
  },
  /** 计算属性 */
  getters: {
    // doneTodos: state => {
    //   return state.todos.filter(value => value.done)
    // }
  },
  /** 更改 Vuex 的 store 中的状态的唯一方法是提交 mutation */
  mutations: {
    ACCOUNT_AUTH_STATUS_CHANGED(state, data) {
      const res = data.data.data;
      if(res) {
        state.token = res.token;
        state.userInfo = res.userName;
        state.isLogin = true;
        Auth.setUserInfo(state.userInfo);
        Auth.setLogin(state.isLogin);
        Auth.setToken(state.token);
        Auth.setAuthorities(res.authorities);
        Auth.setMenu({});
      }
    },
    ACCOUNT_LOGOUT_FAILURE(state) {
      //state.accountPwd = {};
      state.isLogin = false;
      //Auth.removeAccountPwd();
      Auth.removeUserInfo();
      Auth.removeLogin();
      Auth.removeToken();
    },

    handleRemember(state, data){
      const userInfo = data.params;
      if (userInfo && userInfo.remember) {
        this.state.accountPwd = userInfo; //记住用户名和密码
        Auth.setAccountPwd(this.state.accountPwd);
      }
      else{
        Auth.removeAccountPwd();
      }
    },

  },
  /** Action 提交的是 mutation，而不是直接变更状态。Action 可以包含任意异步操作。 */
  actions: {
    /** 登录 */
    accountLoginSubmit({commit}, params) {
      return new Promise((resolve, reject) => {
        LoginApi.login(params).then((res) => {
          console.log('%c 身份被服务器接受', 'color:#fa8c16');
          if(res.data.code == ResponseCode.SUCCESS) {
            commit('ACCOUNT_AUTH_STATUS_CHANGED', {...res, params});
            commit('handleRemember', {...res, params});
            resolve(res.data)
          }
          else if(res.data.code == ResponseCode.ERROR){
            commit('ACCOUNT_LOGOUT_FAILURE');
            reject(res.data.code);
          }

          else {
            resolve(res.data);
          }

        }).catch(err => {
          console.log('%c 身份被服务器拒绝', 'color:#fa8c16');
          commit('ACCOUNT_LOGOUT_FAILURE');
          reject(err)
        })
      })
    },
    /** 登出 */
    accountLogoutSubmit({commit}) {
      return new Promise((resolve, reject) => {
        LoginApi.logout().then(res => {
          commit('ACCOUNT_LOGOUT_FAILURE');
          resolve()
        }).catch(err => {
          reject(err)
        })
      })
    }
  }
}
