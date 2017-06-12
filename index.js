/**
 * Created by dowin on 2017/5/31.
 */
import React, { Component, PropTypes } from 'react';
import { View, requireNativeComponent } from 'react-native';
var resolveAssetSource = require('react-native/Libraries/Image/resolveAssetSource');
export default class RCTMenu extends Component {

    _onchange=(event) =>{
        if(this.props.onChange && event.nativeEvent){
            this.props.onChange(event.nativeEvent);
        }
    }
    _processProperties(icons) {
        let arr = [];
        for(let i = 0;i<icons.length;i++){
            if(icons[i]){
                arr.push(resolveAssetSource(icons[i]));
            }
        }
        return arr;
    }
    render() {
        let props = {
            ...this.props,
            icon:resolveAssetSource(this.props.icon),
            icons:this._processProperties(this.props.icons)
        };
        return (
            <ApiMenu
                {...props}
                onChange={this._onchange}
            />
        );
    }
}

RCTMenu.propTypes = {
    ...View.propTypes,
    titles:PropTypes.array,
    icons:PropTypes.array,
    XXYTitle:PropTypes.array,
    //type 'XXYAction'底部选择框 'YBPopup':尖角提示框
    type:PropTypes.string,
    icon:PropTypes.any,
    title:PropTypes.string,
    onChange:PropTypes.func,
    menuWidth:PropTypes.number,
};

// PopupMenuBtn.defaultProps = {
//     blurType: 'dark',
//     blurAmount: 10,
// };

const ApiMenu = requireNativeComponent('Menu', RCTMenu);
