import Jurisdiction from '../../router/app'

/**
 * 通过meta.role判断是否与当前用户权限匹配
 * @param roles
 * @param route
 */
function hasPermission(roles, route) {
  if (route.meta && route.meta.roles) {
    return roles.some(role => route.meta.roles.indexOf(role) >= 0)
  }
  return true
}

/**
 * 递归过滤异步路由表，返回符合用户角色权限的路由表
 * @param asyncRouterMap
 * @param roles
 */
function filterAsyncRouter(asyncRouterMap, roles) {
  return asyncRouterMap.filter(route => {
    if (hasPermission(roles, route)) {
      if (route.children && route.children.length) {
        route.children = filterAsyncRouter(route.children, roles)
      }
      return true
    }
    return false
  })
}

const permission = {
  state: {
    addRouters: []
  },
  mutations: {
    SET_ROUTERS: (state, routers) => {
      state.addRouters = routers;
    }
  },
  actions: {
    GetRouters({commit}, roles) {
      return new Promise(resolve => {
        let accessedRouters;
        if (roles.indexOf('admin') >= 0) {
          accessedRouters = Jurisdiction
        } else {
          accessedRouters = filterAsyncRouter(Jurisdiction, roles)
        }
        commit('SET_ROUTERS', accessedRouters);
        resolve()
      })
    }
  }
};

export default permission
