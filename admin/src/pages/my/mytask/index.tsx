import { useRef, useEffect, useState } from 'react';
import { Map, APILoader, useMarker, useMap } from '@uiw/react-baidu-map';


type Point={
    lng: string,
    lat: string
}
const Example = () => {
  const divElm = useRef(null);
  const { setContainer, map } = useMap({ widget: ['GeolocationControl', 'NavigationControl'], zoom: 8 });
  const { setType, marker } = useMarker({ map, position: { lng: 121.444017, lat: 31.237787 } });
  

  useEffect(() => {
    if (divElm.current && !map) {    
      
      setContainer(divElm.current);
    }
    
  });
  useEffect(() => {  // 可以在函数组件内处理生命周期事件，默认情况，每次渲染都会调用该函数
    const t = setInterval(() => {
     marker.setAnimation(2),
     marker.setPosition(new BMap.Point(window.lng,window.lat)),

     console.log(window.lng);
     map.setCenter(new BMap.Point(window.lng,window.lat)),
     map.setZoom(14)
    }, 3000)

    return () => {  // 每次卸载都执行此函数，清楚定时器
      clearTimeout(t)
    }
  }, [marker])

  return (
    <>
      <button onClick={() => setType('red2')}>设置 red2</button>
      <button onClick={() => setType('loc_blue')}>设置 loc_blue</button>
      <button onClick={() => marker.setPosition(new BMap.Point(window.lng, window.lat))}>设置坐标点</button>
      <button onClick={() => marker.setAnimation(2)}>设置动画</button>
      <button onClick={() => marker.setAnimation(null)}>取消动画</button>
      <button >{window.lng}</button>
      <div ref={divElm} style={{ height: '100%' }} />
    </>
  )
}

const Demo = () => (
  <div style={{ width: '100%', height: '500px' }}>
    <APILoader akay="56qAN0woZGi81AtWMEmbZh8h8xpBuHYw">
      <Example />
    </APILoader>
  </div>
);
export default Demo;