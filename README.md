
# react-native-button-menu

## Getting started

`$ npm install react-native-button-menu --save`

### Mostly automatic installation

`$ react-native link react-native-button-menu`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-button-menu` and add `RNMenu.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNMenu.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNMenuPackage;` to the imports at the top of the file
  - Add `new RNMenuPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-button-menu'
  	project(':react-native-button-menu').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-button-menu/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-button-menu')
  	```
## Usage
```javascript
import RCTMenu from 'react-native-button-menu';

<RCTMenu onChange={(v)=>this.itemClick(v)}
      style={{width:22,height:22}}
      type="YBPopup"
      titles={['发起群聊','添加好友']}
      icons={[
        require('../../images/chat/createTeam.png'),
        require('../../images/chat/addFriend.png')
      ]}
      icon={require('../../images/chat/iconAdd.png')}
/>

```
  