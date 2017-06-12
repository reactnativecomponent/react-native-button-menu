//
//  Menu.m
//  RCTMenu
//
//  Created by Dowin on 2017/5/22.
//  Copyright © 2017年 Dowin. All rights reserved.
//

#import "Menu.h"
#import "RCTUtils.h"
@implementation Menu
{
    MenuView *_menuV;
    BOOL _isSelect;
  
}
RCT_EXPORT_MODULE()
- (UIView *)view
{
    _menuV = [[MenuView alloc] init];
    _isSelect = YES;
    
    return _menuV;
}
//将所需参数导出给JS
RCT_EXPORT_VIEW_PROPERTY(titles,NSArray)
RCT_EXPORT_VIEW_PROPERTY(icons, NSArray)
RCT_EXPORT_VIEW_PROPERTY(XXYTitle, NSArray)
RCT_EXPORT_VIEW_PROPERTY(type, NSString)
RCT_EXPORT_VIEW_PROPERTY(icon, NSDictionary)
RCT_EXPORT_VIEW_PROPERTY(title, NSString)
RCT_EXPORT_VIEW_PROPERTY(menuWidth, CGFloat)
RCT_EXPORT_VIEW_PROPERTY(onChange, RCTBubblingEventBlock)
RCT_EXPORT_METHOD(longAction:( float)topX topY:(float)topY){
  //  UIViewController *rootVC = RCTKeyWindow().rootViewController;
   // while (rootVC.presentedViewController) {
   //    rootVC = rootVC.presentedViewController;
   // };
//    //创建菜单控制器
  //  UIMenuController * menuvc = [UIMenuController sharedMenuController];
   // UIMenuItem * menuItem1 = [[UIMenuItem alloc]initWithTitle:@"复制" action:@selector(firstItemAction:)];
   // UIMenuItem * menuItem2 = [[UIMenuItem alloc]initWithTitle:@"粘贴" action:@selector(secondItemAction:)];
 //   UIMenuItem * menuItem3 = [[UIMenuItem alloc]initWithTitle:@"删除" action:@selector(threeItemAction:)];
  //  menuvc.menuItems = @[menuItem1, menuItem2, menuItem3];
   // [menuvc setTargetRect:CGRectMake(100, 100, 0, 0) inView:rootVC.view];
   // [menuvc setMenuVisible:YES animated:YES];
}

@end
