// @ts-ignore
/* eslint-disable */

declare namespace API {
  type CurrentUser = {
    id: number;
    name: string;
    login: string;
    sex: number;
    phone: string;
    passwd: string;
    birthday: Date;
    dpid: number;
    accsess: string;
  };

  type LoginResult = {
    status?: string;
    type?: string;
    currentAuthority?: string;
  };

  type PageParams = {
    current?: number;
    pageSize?: number;
  };

  type RuleListItem = {
    key?: number;
    disabled?: boolean;
    href?: string;
    avatar?: string;
    name?: string;
    owner?: string;
    desc?: string;
    callNo?: number;
    status?: number;
    updatedAt?: string;
    createdAt?: string;
    progress?: number;
  };

  type RuleList = {
    data?: RuleListItem[];
    /** 列表的内容总数 */
    total?: number;
    success?: boolean;
  };
  type  ArchorList ={
    data?: ArchorListItem[];
    total?: number;
    success?: boolean;
  }
  type ArchorListItem ={
    id: number;
    name: string;
    lng: string;
    lat: string;
    rid: number;
    order_sec: number;

  };
  type  RegionList ={
    data?: RegionListItem[];
    total?: number;
    success?: boolean;
  }
  type RegionListItem ={
    id: number;
    name: string;
    type: number;
    color: string;
    status: number;

  };

  type RecordListItem ={
    id: number;
    uid: number;
    lng: string
    lat: string;
    creatime: Date;
  }

  type RecordList ={
    data: RecordListItem[];
    total?: number;
    success?: boolean;
  }
  type WarnlogListItem={
    id: number;
    tid: number;
    uid: number;
    errortime: Date;
    lng: string;
    lat: strign;
  }
  type WarnlogList={
    data: WarnlogListItem[];
    total?: number;
    success?: boolean;
  }
  type AdminListItem={
    id: number;
    name: string;
    passwd: string;
    role: string
  }
  type AdminList={
    data: AdminListItem[];
    total?: number;
    success?: boolean;
  }
  type TaskList={

    data: TaskListItem[];
    total?: number;
    success?: boolean;
  }
  type TaskListItem={
    id: number;
    name: string;
    uid: number;
    rid: number;
    creatime: Date;
    startime: Date;
    endtime: Date;
    finishtime: Date;
    status: number;
    startlng: string;
    startlat: string;
    endlng: string;
    endlat: string;

  }
  type DeptmentListItem={
    id: number;
    name: string;
    region: number;
    status: number;
  }
  type DeptmentList={

    data: DeptmentListItem[];
    total?: number;
    success?: boolean;
  }
  type UserListItem={
    id: number;
    name: string;
    login: string;
    sex: number;
    phone: string;
    passwd: string;
    birthday: Date;
  }
  type UserList={

    data: UserListItem[];
    total?: number;
    success?: boolean;
  }



  type FakeCaptcha = {
    code?: number;
    status?: string;
  };

  type LoginParams = {
    login?: string;
    passwd?: string;
    autoLogin?: boolean;
    type?: string;
  };

  type ErrorResponse = {
    /** 业务约定的错误码 */
    errorCode: string;
    /** 业务上的错误信息 */
    errorMessage?: string;
    /** 业务上的请求是否成功 */
    success?: boolean;
  };

  type NoticeIconList = {
    data?: NoticeIconItem[];
    /** 列表的内容总数 */
    total?: number;
    success?: boolean;
  };

  type NoticeIconItemType = 'notification' | 'message' | 'event';

  type NoticeIconItem = {
    id?: string;
    extra?: string;
    key?: string;
    read?: boolean;
    avatar?: string;
    title?: string;
    status?: string;
    datetime?: string;
    description?: string;
    type?: NoticeIconItemType;
  };
}
