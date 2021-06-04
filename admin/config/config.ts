// https://umijs.org/config/
import { defineConfig } from 'umi';
import { join } from 'path';
import defaultSettings from './defaultSettings';
import proxy from './proxy';
const { REACT_APP_ENV } = process.env;
export default defineConfig({
  hash: true,
  antd: {},
  dva: {
    hmr: true,
  },
  layout: {
    // https://umijs.org/zh-CN/plugins/plugin-layout
    locale: true,
    siderWidth: 208,
    ...defaultSettings,
  },
  // https://umijs.org/zh-CN/plugins/plugin-locale
  locale: {
    // default zh-CN
    default: 'zh-CN',
    antd: true,
    // default true, when it is true, will use `navigator.language` overwrite default
    baseNavigator: true,
  },
  dynamicImport: {
    loading: '@ant-design/pro-layout/es/PageLoading',
  },
  targets: {
    ie: 11,
  },
  // umi routes: https://umijs.org/docs/routing
  // Theme for antd: https://ant.design/docs/react/customize-theme-cn
  // Theme for antd: https://ant.design/docs/react/customize-theme-cn
  routes:[{
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
  {
    path: '/userhome',
    name: 'userhome',
    icon: 'smile',
    component: './my/userhome',
  },
  {
    path: '/mytask',
    name: 'mytask',
    icon: 'smile',
    component: './my/mytask',
  },
  {
    path: '/myinfo',
    name: 'myinfo',
    icon: 'smile',
    component: './my/myinfo',
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
    path: '/profile',
    name: 'profile',
    icon: 'profile',
    routes: [
      {
        name: 'basic',
        icon: 'smile',
        path: '/profile/basic',
        component: './profile/basic',
      },
      {
        name: 'advanced',
        icon: 'smile',
        path: '/profile/advanced',
        component: './profile/advanced',
      },
    ],
  },


  {
    component: './404',
  },
],
  // Theme for antd: https://ant.design/docs/react/customize-theme-cn
  theme: {
    'primary-color': defaultSettings.primaryColor,
  },
  // esbuild is father build tools
  // https://umijs.org/plugins/plugin-esbuild
  esbuild: {},
  title: '电子围栏系统',
  ignoreMomentLocale: true,
  proxy: proxy[REACT_APP_ENV || 'dev'],
  manifest: {
    basePath: '/',
  },
  // Fast Refresh 热更新
  fastRefresh: {},
  openAPI: [
    {
      requestLibPath: "import { request } from 'umi'",
      // 或者使用在线的版本
      // schemaPath: "https://gw.alipayobjects.com/os/antfincdn/M%24jrzTTYJN/oneapi.json"
      schemaPath: join(__dirname, 'oneapi.json'),
      mock: false,
    },
    {
      requestLibPath: "import { request } from 'umi'",
      schemaPath: 'https://gw.alipayobjects.com/os/antfincdn/CA1dOm%2631B/openapi.json',
      projectName: 'swagger',
    },
  ],
});
