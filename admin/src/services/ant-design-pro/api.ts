// @ts-ignore
/* eslint-disable */
import { request } from 'umi';

/** 获取当前的用户 GET /api/currentUser */
export async function currentUser(options?: { [key: string]: any }) {

  let data=await request<API.CurrentUser>('/fence/login/currentUser', {

    method: 'GET',
    ...(options || {}),
  }).then();
  return data.data;
}

/** 注销接口 POST /api/login/outLogin */
export async function outLogin(options?: { [key: string]: any }) {
  return request<Record<string, any>>('/fence/login/doLogOut', {
    method: 'POST',
    ...(options || {}),
  });
}

/** 登录接口 POST /api/login/account */
export async function login(body: API.LoginParams, options?: { [key: string]: any }) {
  return request<API.LoginResult>('/fence/login/auth', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}


/** 此处后端没有提供注释 GET /api/notices */
/* export async function getNotices(options?: { [key: string]: any }) {
  return request<API.NoticeIconList>('/api/notices', {
    method: 'GET',
    ...(options || {}),
  });
} */

/** 获取规则列表 GET /api/rule */
export async function rule(
  params: {
    // query
    /** 当前的页码 */
    current?: number;
    /** 页面的容量 */
    pageSize?: number;
  },
  options?: { [key: string]: any },
) {

  return request<API.RuleList>('/api/rule', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });

}
/** 获取archor GET /api/rule */
export async function getArchor(
  params: {
    // query
    /** 当前的页码 */
    current?: number;
    /** 页面的容量 */
    pageSize?: number;
  },
  options?: { [key: string]: any },
) {
  let data=await request<API.ArchorList>('/fence/archor/likeSelect', {
    method: 'POST',
    data: {
      ...params,
    },
    headers: {
      "Content-Type": "application/json; charset=UTF-8",
      "Accept": "application/json; charset=UTF-8"
    },
    ...(options || {}),
  }).then();
   data=data.data,
    data=data.result,
    data.data=data
  return data.data
}
/** 获取archor GET /api/rule */
export async function getRegion(
  params: {
    // query
    /** 当前的页码 */
    current?: number;
    /** 页面的容量 */
    pageSize?: number;
  },
  options?: { [key: string]: any },
) {
  let data=await request<API.RegionList>('/fence/region/likeSelect', {
    method: 'POST',
    data: {
      ...params,
    },
    headers: {
      "Content-Type": "application/json; charset=UTF-8",
      "Accept": "application/json; charset=UTF-8"
    },
    ...(options || {}),
  }).then();
  data=data.data,
  data=data.result,
  data.data=data
  return data.data
}
/** 获取deptment GET  */
export async function getDeptment(
  params: {
    // query
    /** 当前的页码 */
    current?: number;
    /** 页面的容量 */
    pageSize?: number;
  },
  options?: { [key: string]: any },
) {
  let data=await request<API.DeptmentList>('/fence/deptment/likeSelect', {
    method: 'POST',
    data: {
      ...params,
    },
    headers: {
      "Content-Type": "application/json; charset=UTF-8",
      "Accept": "application/json; charset=UTF-8"
    },
    ...(options || {}),
  }).then();
    data=data.data,
    data=data.result,
    data.data=data
  return data.data
}
/** 获取archor GET /api/rule */
export async function getRecord(
  params: {
    // query
    /** 当前的页码 */
    current?: number;
    /** 页面的容量 */
    pageSize?: number;
  },
  options?: { [key: string]: any },
) {
  let data=await request<API.RecordList>('/fence/record/likeSelect', {
    method: 'POST',
    data: {
      ...params,
    },
    headers: {
      "Content-Type": "application/json; charset=UTF-8",
      "Accept": "application/json; charset=UTF-8"
    },
    ...(options || {}),
  }).then();
  data=data.data,
    data=data.result,
    data.data=data
  return data.data
}

/** 获取task GET  */
export async function getTask(
  params: {
    // query
    /** 当前的页码 */
    current?: number;
    /** 页面的容量 */
    pageSize?: number;
  },
  options?: { [key: string]: any },
) {
  let data=await request<API.TaskList>('/fence/task/likeSelect', {
    method: 'POST',
    data: {
      ...params,
    },
    headers: {
      "Content-Type": "application/json; charset=UTF-8",
      "Accept": "application/json; charset=UTF-8"
    },
    ...(options || {}),
  }).then();
    data=data.data,
    data=data.result,
    data.data=data
  return data.data
}
/** 获取record GET  */
export async function getWarnlog(
  params: {
    // query
    /** 当前的页码 */
    current?: number;
    /** 页面的容量 */
    pageSize?: number;
  },
  options?: { [key: string]: any },
) {
  let data=await request<API.WarnlogList>('/fence/warnlog/likeSelect', {
    method: 'POST',
    data: {
      ...params,
    },
    headers: {
      "Content-Type": "application/json; charset=UTF-8",
      "Accept": "application/json; charset=UTF-8"
    },
    ...(options || {}),
  }).then();
    data=data.data,
    data=data.result,
    data.data=data;
  return data.data
}
/** 获取record GET  */
export async function getUsers(
  params: {
    // query
    /** 当前的页码 */
    current?: number;
    /** 页面的容量 */
    pageSize?: number;
  },
  options?: { [key: string]: any },
) {
  let data=await request<API.UserList>('/fence/user/likeSelect', {
    method: 'POST',
    data: {
      ...params,
    },
    headers: {
      "Content-Type": "application/json; charset=UTF-8",
      "Accept": "application/json; charset=UTF-8"
    },
    ...(options || {}),
  }).then();
  data=data.data,
    data=data.result,
    data.data=data;
  return data.data
}
/** 新建规则 PUT /api/rule */
export async function updateRule(options?: { [key: string]: any }) {
  return request<API.RuleListItem>('/api/rule', {
    method: 'PUT',
    ...(options || {}),
  });
}


/** 新建规则 POST /api/rule */
export async function addRule(options?: { [key: string]: any }) {
  return request<API.RuleListItem>('/api/rule', {
    method: 'POST',
    ...(options || {}),
  });
}

/** 删除规则 DELETE /api/rule */
export async function removeRule(options?: { [key: string]: any }) {
  return request<Record<string, any>>('/api/rule', {
    method: 'DELETE',
    ...(options || {}),
  });
}
/** 新建用户 POST /api/rule */
export async function addDeptment(body:API.DeptmentListItem,options?: { [key: string]: any }) {
  return request<API.UserListItem>('/fence/deptment/add', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}
/** 新建用户 POST /api/rule */
export async function addUser(body:API.UserListItem,options?: { [key: string]: any }) {
  return request<API.UserListItem>('/fence/user/add', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}
/** 新建任务 POST /api/rule */
export async function addTask(body:API.TaskListItem,options?: { [key: string]: any }) {
  return request<API.TaskListItem>('/fence/task/add', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}
/** 根据ID 批量删除规则 */
export async function removeData(body:any,pojo:string,options?: { [key: string]: any }) {
  return request<Record<string, any>>('/fence/'+pojo+'/remove', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}