export default [
  {
    path: '/',
    name:'首页',
    component: () => import('../packages/ui/container'),
    children: [
      {
        path: 'index',
        name: '首页',
        component: () => import('../view/home/home'),
      },
      {
        path: 'roleManagement',
        name: 'roleManagement',
        component: () => import('../view/roleManagement/index')
      },{
        path: 'addRole',
        name: 'addRole',
        component: () => import('../view/roleManagement/add')
      },{
        path: 'modifyRole',
        name: 'modifyRole',
        component: () => import('../view/roleManagement/modify')
      },{
        path: 'distributionJurisdiction',
        name: 'distributionJurisdiction',
        component: () => import('../view/roleManagement/distributionJurisdiction')
      },{
        path: 'distributionDataScope',
        name: 'distributionDataScope',
        component: () => import('../view/roleManagement/distributionDataScope')
      },{
        path:'orgManagment',
        name: 'orgManagment',
        component: () => import('../view/orgManagment/index')
      },{
        path:'orgManage',
        name: 'orgManage',
        component: () => import('../view/orgManagment/orgManage')
      },{
        path:'userManagement',
        name: 'userManagement',
        component: () => import('../view/userManagement/index')
      },{
        path:'addNewUser',
        name: 'addNewUser',
        component: () => import('../view/userManagement/addNewUser')
      },
      {
        path:'resource',
        name: 'resource',
        component: () => import('../view/resource/index')
      },
      {
        path:'resourceAdd',
        name: 'resourceAdd',
        component: () => import('../view/resource/add')
      },
      {
        path:'resourceAddNext',
        name: 'resourceAddNext',
        component: () => import('../view/resource/addNext')
      },
      {
        path:'resourceEdit',
        name: 'resourceEdit',
        component: () => import('../view/resource/edit')
      },
      {
        path:'addOrg',
        name: 'addOrg',
        component: () => import('../view/orgManagment/addOrg')
      },
      {
        path:'editOrg',
        name: 'editOrg',
        component: () => import('../view/orgManagment/editOrg')
      },
    ]
  }
]
