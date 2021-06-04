import { ConsoleSqlOutlined, PlusOutlined } from '@ant-design/icons';
import { Button, message, Input, Drawer } from 'antd';
import React, { useState, useRef } from 'react';
import { useIntl, FormattedMessage } from 'umi';
import { PageContainer, FooterToolbar } from '@ant-design/pro-layout';
import type { ProColumns, ActionType } from '@ant-design/pro-table';
import ProTable from '@ant-design/pro-table';
import { ModalForm, ProFormDatePicker, ProFormSelect, ProFormText, ProFormTextArea } from '@ant-design/pro-form';
import type { ProDescriptionsItemProps } from '@ant-design/pro-descriptions';
import ProDescriptions from '@ant-design/pro-descriptions';
import type { FormValueType } from './components/UpdateForm';
import UpdateForm from './components/UpdateForm';
import { addUser, updateRule, getUsers, removeData, getDeptment} from '@/services/ant-design-pro/api';
import request from 'umi-request';

/**
 * 添加节点
 *
 * @param fields
 */
const handleAdd = async (fields: API.UserListItem) => {
  const hide = message.loading('正在添加');
  try {
    await addUser({ ...fields });
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
    await updateRule({
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
const handleRemove = async (selectedRows: API.UserListItem[]) => {
  const hide = message.loading('正在删除');
  if (!selectedRows) return true;
  try {
    await removeData({
      id: selectedRows.map((row) => row.id),
    },'user');
    hide();
    message.success('用户删除成功，即将刷新');
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
  const [currentRow, setCurrentRow] = useState<API.UserListItem>();
  const [selectedRowsState, setSelectedRows] = useState<API.UserListItem[]>([]);
  //初始化部门
  const [deptmentList, setDeptment] = useState<options[]>([]);//公司別

  type options={
    
    label: string;
    value: number;
  
  }

  

  async function initDeptment() {
      //这里异步请求后台将数据拿到
      let data = await request<API.DeptmentList>('/fence/deptment/likeSelect', {
        method: 'POST',
        data: {
          current:1,
          PageSize:100,
        },
        headers: {
          "Content-Type": "application/json; charset=UTF-8",
          "Accept": "application/json; charset=UTF-8"
        },
        
      }).then();
      let data1=data.data;
      let data2=data1.result;
      let dictList=data2;
      let deptmentlist: API.DeptmentListItem[] = [];

      console.log(dictList);

      dictList.forEach(item => {
              const valueObj = item;
              console.log(valueObj)
              //注意 <ProFormSelect>组件下拉框的值需要label与value属性，因此这里将数据放到一个对象的label与value
              const tempDetail = { label: '', value: '', };
              tempDetail.label = valueObj.name;
              tempDetail.value = valueObj.id;
              deptmentlist.push(tempDetail);
          })
      setDeptment(deptmentlist);
  };

  /** 国际化配置 */
  const intl = useIntl();

  const columns: ProColumns<API.UserListItem>[] = [
    {
      title: (
        <FormattedMessage
          id="pages.searchTable.updateForm.userListName.nameLabel"
          defaultMessage="序号"
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
      title: '姓名',
      dataIndex: 'name',
      valueType: 'textarea',
    },
    {
      title: '账户',
      dataIndex: 'login',
      valueType: 'textarea',
    },
    {
      title: '性别',
      dataIndex: 'sex',
      hideInForm: true,
      valueEnum: {
        0: {
          text: '女',
          status: 'Default',
        },
        1: {
          text: '男',
          status: 'Processing',
        },
      },
    },
    {
      title: '状态',
      dataIndex: 'status',
      hideInForm: true,

    },
    {
      title: '生日',
      sorter: true,
      dataIndex: 'birthday',
      valueType: 'dateTime',
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

      ],
    },
  ];

  return (
    <PageContainer>
      <ProTable<API.UserListItem, API.PageParams>
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
        request={getUsers}
        columns={columns}
        rowSelection={{
          onChange: (_, selectedRows) => {
            setSelectedRows(selectedRows);
          },
        }}
      />
      {selectedRowsState?.length > 0 && (
        <FooterToolbar
          extra={
            <div>
              <FormattedMessage id="pages.searchTable.chosen" defaultMessage="已选择" />{' '}
              <a style={{ fontWeight: 600 }}>{selectedRowsState.length}</a>{' '}
              <FormattedMessage id="pages.searchTable.item" defaultMessage="项" />

            </div>
          }
        >
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
        title={intl.formatMessage({
          id: 'pages.searchTable.createForm.userRule',
          defaultMessage: '新建用户',
        })}
        width="400px"
        visible={createModalVisible}
        onVisibleChange={handleModalVisible}
        onFinish={async (value) => {
          const success = await handleAdd(value as API.UserListItem);
          if (success) {
            handleModalVisible(false);
            if (actionRef.current) {
              actionRef.current.reload();
            }
          }
        }}
      >
        <ProFormText
        label="姓名"
          rules={[
            {
              required: true,
              message: (
                <FormattedMessage
                  id="pages.searchTable.ruleName"
                  defaultMessage="名称为必填项"
                />
              ),
            },
          ]}
          width="md"
          name="name"
        />
        <ProFormText 
            label="账号"
            rules={[
                    {
                      required: true,
                      message: (
                        <FormattedMessage
                          id="pages.searchTable.ruleName"
                          defaultMessage="账号为必填项"
                        />
                      ),
                    },
                  ]}width="md" name="login" />
        <ProFormSelect placeholder="性别"
        name="sex"
        rules={[{ required: true, message: '性别' }]}
        options={[
          {
            label: '女',
            value: '0',
          },
          {
            label: '男',
            value: '1',
          },
        ]}
        width="md"
        />
                
        <ProFormDatePicker
                label="生日"
                name="birthday"
                width="md"
                rules={[{ required: true, message: '出生日期' }]}
              />

         <ProFormSelect width="md" name="dpid" label="部门" 
        options={deptmentList}

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
          <ProDescriptions<API.UserListItem>
            column={2}
            title={currentRow?.name}
            request={async () => ({
              data: currentRow || {},
            })}
            params={{
              id: currentRow?.name,
            }}
            columns={columns as ProDescriptionsItemProps<API.UserListItem>[]}
          />
        )}
      </Drawer>
    </PageContainer>
  );
};

export default TableList;
