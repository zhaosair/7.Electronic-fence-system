import Map from 'react-bmapgl/Map'
import React, {ReactElement, useEffect, useRef, useState} from "react";
import DrawingManager from 'react-bmapgl/Library/DrawingManager'
import {Avatar, Button, Space, Card, Col, Divider, Input, Row, Tag, Form} from 'antd';
import { GridContent } from '@ant-design/pro-layout';
import request from 'umi-request';


interface Point{
  lng: string;
  lat: string;
}


class regionExample extends React.Component <any, any>{
  map!: BMapGL.Map;


  constructor(props:any) {
    super(props);
    this.state= {
      Point: [],
      type: null,
      typename: null,
      center:null,
      radius:null,
      name:null,

    };
    this.map;
    this.clearOverlay = this.clearOverlay.bind(this);
    this.handleChange = this.handleChange.bind(this);
    this.addInput =this.addInput.bind(this);
  }
  handleChange(event){
    // 读取输入的值
  const value=event.target.value;
  //   更新状态
  this.setState({
      name:value
  })
}
  overcomplete(e: any){
      let typename;

      if(e.drawingMode==="circle"){
        console.log(e)
        this.setState({
          typename: "圆形",
          radius: e.overlay.radius,
          center: e.overlay.point,
          type:1,
        })
      }
      else{
        let lists: Point[]=e.overlay.points;
        let last=lists.pop();
        typename="多边形";
      this.setState({
        Point: lists,
        typename : typename,
        tyep :0,
      });
      }
  } ;    

  
  clearOverlay(){
  console.log(this.map);
   const bmap =this.map.instance;
   bmap.clearOverlays();
   this.setState({
     Point:null,
     type:null,
     center:null,
     radius:null,
   })
  }
  addRegion(){

  }
  // @ts-ignore
  addInput(){

    request<API.UserListItem>('/fence/region/add', {
      method: 'POST',
      data: {
        name:this.state.name,
        type:this.state.type,


        
      },
      
    });

  }

  render() {

    return (
      <GridContent>
        <Row gutter={24}>
          <Col lg={7} md={24}>
            <Card bordered={false} style={{ marginBottom: 24,display:"flex" }}>
              <div>
                <Space>
                  <Button type="primary" onClick={this.clearOverlay}>清除坐标</Button>
                </Space>
              </div>
              <Divider dashed />
              <Form>
                <label htmlFor="regionName">区域名称</label>
                <Input  type="text" placeholder={'区域名称'} id="regionName" value={this.state.name} onChange={this.handleChange}/>
                <label htmlFor="regionType">区域类型</label>
                <Input id="regionType" value={this.state.typename}  onChange={this.overcomplete} readOnly/>
                
                {this.state.Point && this.state.Point.map((e:any,index)=>(                  
                  <Input value={e.lng+"_"+e.lat} key={index} id={"point"+index}/>
                ))}
                {this.state.radius && ( 
                  <div>             
                  <label htmlFor="point">中心点</label>
                  <Input value={this.state.center.lng+"_"+this.state.center.lat} id={"point"}/>
                  <label htmlFor="radius">半径(单位: 米)</label>
                  <Input value={this.state.radius} id={"radius"}/>                                  
                  </div>   
                  )}
                
                <Button type="primary" onClick={this.addInput}>提交区域</Button>
              </Form>
            </Card>
          </Col>
          <Col lg={17} md={24}>
            <Card>
              <div style={{display: 'flex', justifyContent: 'space-around'}} id="map">
                <Map
                  style={{ height: 500, width: '100%' }}
                  center="韶关市"
                  zoom={14}  
                  ref={e => {this.map = e}}
                  
                >
                  <DrawingManager
                    enableLimit
                    enableCalculate
                    onOverlaycomplete={(e, info) => {
                      this.overcomplete(e)
                    }}
                    
                    />
                </Map>
              </div>
            </Card>
          </Col>
        </Row>
      </GridContent>

    )
  }
}
export default regionExample




