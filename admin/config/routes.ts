export default [
  {
    path: '/user',
    layout: false,
    routes: [
      {
        path: '/user',
        routes: [
          {
            name: 'login',
            path: '/user/login',
            component: './user/Login',
          },
        ],
      },
    ],
  },
  {
    path: '/welcome',
    name: 'welcome',
    icon: 'smile',
    component: './Welcome',
  },
/*   {
    path: '/admin',
    name: 'admin',
    icon: 'crown',
    access: 'canAdmin',
    component: './Admin',
    routes: [
      {
        path: '/admin/sub-page',
        name: 'sub-page',
        icon: 'smile',
        component: './Welcome',
      },
    ],
  },
  {
    name: 'list.table-list',
    icon: 'table',
    path: '/list',
    component: './TableList',
  },*/
  {
    path: '/form',
    icon: 'form',
    name: 'form',
    routes: [
      {
        path: '/',
        redirect: '/form/basic-form',
      },
      {
        name: 'basic-form',
        icon: 'smile',
        path: '/form/basic-form',
        component: './form/basic-form',
      },
      {
        name: 'step-form',
        icon: 'smile',
        path: '/form/step-form',
        component: './form/step-form',
      },
      {
        name: 'advanced-form',
        icon: 'smile',
        path: '/form/advanced-form',
        component: './form/advanced-form',
      },
    ],
  },
  {
    name: 'map.center',
    icon: 'table',
    path: '/addregion',
    component: './map/region',
  },
  {
    name: 'map.region',
    icon: 'table',
    path: '/map',
    component: './map',
  },

  {
    name: 'archor.query',
    icon: 'AimOutlined',
    path: '/archor',
    component: './archor',
  },
  {
    name: 'region.query',
    icon: 'table',
    path: '/region',
    component: './region',
  },
  {
    name: 'user.query',
    icon: 'table',
    path: '/userlist',
    component: './user',
  },
  {
    name: 'warnlog.query',
    icon: 'table',
    path: '/warnlog',
    component: './warnlog',
  },
  {
    name: 'record.query',
    icon: 'table',
    path: '/record',
    component: './record',
  },
  {
    name: 'deptment.query',
    icon: 'table',
    path: '/deptment',
    component: './deptment',
  },
  {
    name: 'task.query',
    icon: 'table',
    path: '/task',
    component: './task',
  },
  {
    name: 'admin.query',
    icon: 'table',
    path: '/adminquery',
    component: './admin',
  },
  {
    path: '/',
    redirect: '/welcome',
  },
  {
    component: './404',
  },

];
