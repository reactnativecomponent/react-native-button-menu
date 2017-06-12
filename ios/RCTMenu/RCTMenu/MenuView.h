//
//  MenuView.h
//  RCTMenu
//
//  Created by Dowin on 2017/5/22.
//  Copyright © 2017年 Dowin. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTComponent.h>

@interface MenuView : UIView
@property(nonatomic,strong)NSArray *titles;
@property(nonatomic,strong)NSArray *icons;
@property(nonatomic,strong)NSArray *XXYTitle;
@property(nonatomic,strong)NSString *type;
@property(nonatomic,strong)NSDictionary *icon;
@property(nonatomic,strong)NSString *title;
@property (assign, nonatomic) CGFloat menuWidth;
@property (nonatomic, copy) RCTBubblingEventBlock onChange;
@end
