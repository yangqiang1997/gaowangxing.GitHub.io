import Vue from 'vue'
import Router from 'vue-router'
import app from './app'

Vue.use(Router);

const router = new Router({
  routes: [
    {
      path: '/login',
      name: 'login',
      component: () => import('../view/login/login')
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('../view/login/reg')
    },
    {
      path: '/404',
      name: '404',
      component: () => import('@/packages/ui/errorPage/404.vue')
    },
    {
      path: '*',
      name: 'err',
      redirect: '404',
    },
    ...app,
  ],
  scrollBehavior: () => ({y: 0}),
});

export default router
