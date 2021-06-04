import React from 'react';
import { Marker } from 'react-bmapgl';
import Map from 'react-bmapgl/Map';


window.lat=undefined;
window.lng=undefined;
class UserHome extends React.PureComponent {
    map!: BMapGL.Map;
    constructor(props:any) {
        super(props);
        this.state = {
            position: new BMapGL.Point(window.lng , window.lat),
            icon: 'loc_blue',
            reload:false
            
          }
        this.map;
    }
    timer: null
    handleResize = () => {
      this.setState({
        reload: true,
      }, () => {
        this.setState({ reload: false });
      });
    }
    componentDidMount(){
      
                   //需要定时执行的方法
                 /*   setInterval(() => {
                    this.randomParams();
                    this.handleResize();
                    console.log(window.lng);
                  },        
                   2000); */
                   const bmap = this.map.instance;
                   bmap.addOverlay(new BMapGL.Point(window.lng , window.lat)); 
 
      
    }

    randomParams() {
      const point = new BMapGL.Point(window.lng , window.lat);



    }
      componentWillUnmount = () => {
        if (this.timer != null) {
          clearInterval(this.timer);
        }
        this.setState = (state,callback)=>{
          return;
        };

        
    }
    
    render() {
      return (
        <Map
          style={{ height: 450 }}
          center="贵阳市"          
          zoom={14}
          heading={0}
          tilt={40}
          onClick={e => console.log(e)}
          enableScrollWheelZoom
          ref={e => {this.map = e}}           
        >
            </Map>
      )
    }
  }
  
export default UserHome