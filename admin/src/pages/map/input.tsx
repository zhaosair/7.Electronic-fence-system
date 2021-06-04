import React from 'react'


// 子组件   单独写到一个文件中去 因为只需要输出，不含有私有属性（数据）
// 所以使用函数组件较为合适

class Item extends React.Component{
  render() {
    return (
      <input name="point" value={this.props.data.point}/>
    )
  }
}
