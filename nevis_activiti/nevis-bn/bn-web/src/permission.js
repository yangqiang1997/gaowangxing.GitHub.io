import router from './router'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css'// progress bar style
import {store} from './store'
import {Msg} from './tools/message';

NProgress.configure({showSpinner: false,speed: 1000}); // NProgress Configuration

/**
 *@author李佳莉
 */

/** 路由控制 */
router.beforeEach((to, from, next) => {
  /** 判断访问的是前台还是后台 */
  //const isAdmin = /\/admin/.test(to.path);
  /** 判断用户是否已经登录 */
  const {isLogin, userInfo} = store.getters;
  NProgress.start();

  if (to.path === '/login') {
    next();
    return;
  }
  if (isLogin) {
      if ('判断访问权限') {
        next() // store.getters.roles, to.meta.roles
      } else {
        next({path: '/404', replace: true, query: {noGoBack: true}})
      }
  } else {
      next('/login');
      Msg.error('检测到您当前未登录，请先登录');
  }
  NProgress.done();
});

router.afterEach(() => {
  NProgress.done()
});
