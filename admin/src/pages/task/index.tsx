import { PlusOutlined } from '@ant-design/icons';
import { Button, message, Input, Drawer } from 'antd';
import React, { useState, useRef } from 'react';
import { useIntl, FormattedMessage } from 'umi';
import { PageContainer, FooterToolbar } from '@ant-design/pro-layout';
import type { ProColumns, ActionType } from '@ant-design/pro-table';
import ProTable from '@ant-design/pro-table';
import { ModalForm, ProFormDatePicker, ProFormDateTimePicker, ProFormSelect, ProFormText, ProFormTextArea, ProFormTimePicker } from '@ant-design/pro-form';
import type { ProDescriptionsItemProps } from '@ant-design/pro-descriptions';
import ProDescriptions from '@ant-design/pro-descriptions';
import type { FormValueType } from './components/UpdateForm';
import UpdateForm from './components/UpdateForm';
import {rule, addTask, updateTask, removeData, getTask} from '@/services/ant-design-pro/api';
import request from 'umi-request';

/**
 * 添加节点
 *
 * @param fields
 */
const handleAdd = async (fields: API.TaskListItem) => {
  const hide = message.loading('正在添加');
  try {
    await addTask({ ...fields });
    hide();
    message.success('添加成功');
    return true;
  } catch (error) {
    hide();
    message.error('添加失败请重试！');
    return false;
  }
};

/**
 * 更新节点
 *
 * @param fields
 */
const handleUpdate = async (fields: FormValueType) => {
  const hide = message.loading('正在配置');
  try {
    await updateTask({
      name: fields.name,
      desc: fields.desc,
      key: fields.key,
    });
    hide();

    message.success('配置成功');
    return true;
  } catch (error) {
    hide();
    message.error('配置失败请重试！');
    return false;
  }
};

/**
 * 删除节点
 *
 * @param selectedRows
 */
const handleRemove = async (selectedRows: API.TaskListItem[]) => {
  const hide = message.loading('正在删除');
  if (!selectedRows) return true;
  try {
    await removeData({
      key: selectedRows.map((row) => row.id),
    },'task');
    hide();
    message.success('删除成功，即将刷新');
    return true;
  } catch (error) {
    hide();
    message.error('删除失败，请重试');
    return false;
  }
};

const TableList: React.FC = () => {
  /** 新建窗口的弹窗 */
  const [createModalVisible, handleModalVisible] = useState<boolean>(false);
  /** 分布更新窗口的弹窗 */
  const [updateModalVisible, handleUpdateModalVisible] = useState<boolean>(false);

  const [showDetail, setShowDetail] = useState<boolean>(false);

  const actionRef = useRef<ActionType>();
  const [currentRow, setCurrentRow] = useState<API.TaskListItem>();
  const [selectedRowsState, setSelectedRows] = useState<API.TaskListItem[]>([]);


  const [regionList, setRegionList] = useState<options[]>([]);
  const [userList, setUserList] = useState<options[]>([]);
  type options={    
    label: string;
    value: number;  
  }
  async function initDeptment() {
      //这里异步请求后台将数据拿到
      let data = await request<API.RegionList>('/fence/region/likeSelect', {
        method: 'POST',
        data: {
          current:1,
          PageSize:100,
        }        
      }).then();
      let data1=data.data;
      let dictList=data1.result;
      let regionlist: API.RegionListItem[] = [];
      dictList.forEach(item => {
              const valueObj = item;
              console.log(valueObj)
              //注意 <ProFormSelect>组件下拉框的值需要label与value属性，因此这里将数据放到一个对象的label与value
              const tempDetail = { label: '', value: '', };
              tempDetail.label = valueObj.name;
              tempDetail.value = valueObj.id;
              regionlist.push(tempDetail);
          })
      setRegionList(regionlist);
      let userdata = await request<API.UserList>('/fence/user/likeSelect', {
        method: 'POST',
        data: {
          current:1,
          PageSize:100,
        }        
      }).then();
      let userdata1=userdata.data;
      let userdictList1=userdata1.result;
      let userlist: API.UserListItem[] = [];
      userdictList1.forEach(item => {
              const valueObj = item;
              console.log(valueObj)
              //注意 <ProFormSelect>组件下拉框的值需要label与value属性，因此这里将数据放到一个对象的label与value
              const tempDetail = { label: '', value: '', };
              tempDetail.label = valueObj.name;
              tempDetail.value = valueObj.id;
              userlist.push(tempDetail);
          })
      setUserList(userlist);
  };

  /** 国际化配置 */
  const intl = useIntl();

  const columns: ProColumns<API.TaskListItem>[] = [
    {
      title: (
        <FormattedMessage
          id="pages.searchTable.updateForm.ruleName.nameLabel"
          defaultMessage="序列"
        />
      ),
      dataIndex: 'id',
      tip: '唯一的 key',
      render: (dom, entity) => {
        return (
          <a
            onClick={() => {
              setCurrentRow(entity);
              setShowDetail(true);
            }}
          >
            {dom}
          </a>
        );
      },
    },
    {
      title: '规则名称',
      dataIndex: 'name',
      valueType: 'textarea',
    },
    {
      title: '用户',
      dataIndex: 'uid',
      valueType: 'textarea',
    },
    {
      title: '活动区域',
      dataIndex: 'rid',
      valueType: 'textarea',
    },
    {
      title: '起点经度',
      dataIndex: 'startlng',
      valueType: 'textarea',
    },
    {
      title: '起点经度',
      dataIndex: 'startlat',
      valueType: 'textarea',
    },
    {
      title: '终点经度',
      dataIndex: 'endlng',
      valueType: 'textarea',
    },
    {
      title: '终点维度',
      dataIndex: 'endlat',
      valueType: 'textarea',
    },
    {
      title: '创建时间',
      dataIndex: 'creatime',
      valueType: 'dateTime',
    },
    {
      title: '开始时间',
      dataIndex: 'startime',
      valueType: 'dateTime',
    },
    {
      title: '终止时间',
      dataIndex: 'endtime',
      valueType: 'dateTime',
    },
    {
      title: '完成时间',
      dataIndex: 'finishtime',
      valueType: 'dateTime',
    },
    {
      title: '状态',
      dataIndex: 'status',
      hideInForm: true,
      valueEnum: {
        0: {
          text: '未开始',
          status: 'Default',
        },
        1: {
          text: '进行中',
          status: 'Processing',
        },
        2: {
          text: '已完成',
          status: 'Success',
        },
        3: {
          text: '失败',
          status: 'Error',
        },
      },
    },
    {
      title: <FormattedMessage id="pages.searchTable.titleOption" defaultMessage="操作" />,
      dataIndex: 'option',
      valueType: 'option',
      render: (_, record) => [
        <a
          key="config"
          onClick={() => {
            handleUpdateModalVisible(true);
            setCurrentRow(record);
          }}
        >
          <FormattedMessage id="pages.searchTable.config" defaultMessage="配置" />
        </a>,
        <a key="subscribeAlert" href="https://procomponents.ant.design/">
          <FormattedMessage id="pages.searchTable.subscribeAlert" defaultMessage="订阅警报" />
        </a>,
      ],
    },
  ];

  return (
    <PageContainer>
      <ProTable<API.TaskListItem, API.PageParams>
        headerTitle={intl.formatMessage({
          id: 'pages.searchTable.title',
          defaultMessage: '查询表格',
        })}
        actionRef={actionRef}
        rowKey="id"
        search={{
          labelWidth: 120,
        }}
        toolBarRender={() => [
          <Button
            type="primary"
            key="primary"
            onClick={() => {
              initDeptment();
              handleModalVisible(true);
            }}
          >
            <PlusOutlined /> <FormattedMessage id="pages.searchTable.new" defaultMessage="新建" />
          </Button>,
        ]}
        request={getTask}
        columns={columns}
        rowSelection={{
          onChange: (_, selectedRows) => {
            setSelectedRows(selectedRows);
          },
        }}
      />
      {selectedRowsState?.length > 0 && (
        <FooterToolbar>
          <Button
            onClick={async () => {
              await handleRemove(selectedRowsState);
              setSelectedRows([]);
              actionRef.current?.reloadAndRest?.();
            }}
          >
            <FormattedMessage id="pages.searchTable.batchDeletion" defaultMessage="批量删除" />
          </Button>
          <Button type="primary">
            <FormattedMessage id="pages.searchTable.batchApproval" defaultMessage="批量审批" />
          </Button>
        </FooterToolbar>
      )}
      <ModalForm
        title="新建任务规则"
        width="400px"
        visible={createModalVisible}
        onVisibleChange={handleModalVisible}
        onFinish={async (value) => {
          const success = await handleAdd(value as API.TaskListItem);
          if (success) {
            handleModalVisible(false);
            if (actionRef.current) {
              actionRef.current.reload();
            }
          }
        }}
      >
        <ProFormText
          rules={[
            {
              required: true,
              message: (
                <FormattedMessage
                  id="pages.searchTable.ruleName"
                  defaultMessage="规则名称为必填项"
                />
              ),
            },
          ]}
          label="任务名"
          width="md"
          name="name"
        />
        <ProFormSelect 
          name="uid"
          label="执行用户"  
          width="md"
          options={userList}        
        />
        <ProFormSelect
          name="rid"
          label="区域/路线"
          width="md"
          options={regionList}
        />
        <ProFormDateTimePicker
                label="开始时间"
                name="startime"
                width="md"
                rules={[{ required: true, message: '开始' }]}
              />
               <ProFormDateTimePicker
                label="结束时间"
                name="endtime"
                width="md"
                rules={[{ required: true, message: '结束' }]}
              />
      </ModalForm>
      <UpdateForm
        onSubmit={async (value) => {
          const success = await handleUpdate(value);
          if (success) {
            handleUpdateModalVisible(false);
            setCurrentRow(undefined);
            if (actionRef.current) {
              actionRef.current.reload();
            }
          }
        }}
        onCancel={() => {
          handleUpdateModalVisible(false);
          setCurrentRow(undefined);
        }}
        updateModalVisible={updateModalVisible}
        values={currentRow || {}}
      />

      <Drawer
        width={600}
        visible={showDetail}
        onClose={() => {
          setCurrentRow(undefined);
          setShowDetail(false);
        }}
        closable={false}
      >
        {currentRow?.name && (
          <ProDescriptions<API.TaskListItem>
            column={2}
            title={currentRow?.name}
            request={async () => ({
              data: currentRow || {},
            })}
            params={{
              id: currentRow?.name,
            }}
            columns={columns as ProDescriptionsItemProps<API.TaskListItem>[]}
          />
        )}
      </Drawer>
    </PageContainer>
  );
};

export default TableList;
