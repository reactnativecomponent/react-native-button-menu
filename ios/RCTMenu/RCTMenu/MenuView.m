//
//  MenuView.m
//  RCTMenu
//
//  Created by Dowin on 2017/5/22.
//  Copyright © 2017年 Dowin. All rights reserved.
//

#import "MenuView.h"
#import "XXYActionSheetView.h"
#import "YBPopupMenu.h"
#import <React/RCTConvert.h>
#define TITLES @[@"修改", @"删除", @"扫一扫",@"付款"]
#define ICONS  @[@"motify",@"delete",@"saoyisao",@"pay"]

@interface MenuView()<YBPopupMenuDelegate,XXYActionSheetViewDelegate>

@end

@implementation MenuView

-(void)setTitles:(NSArray *)titles{
    if (_titles != titles) {
        _titles = titles;
    }
}
-(void)setIcons:(NSArray *)icons{
    if (_icons != icons) {
        _icons = icons;
    }
}
-(void)setXXYTitle:(NSArray *)XXYTitle{
    if (_XXYTitle != XXYTitle) {
        _XXYTitle = XXYTitle;
    }
}
-(void)setType:(NSString *)type{
    if (_type != type) {
        _type = type;
    }
}
-(void)setIcon:(NSDictionary *)icon{
    if (_icon != icon) {
        _icon = icon;
    }
}

-(void)setTitle:(NSString *)title{
    if (_title != title) {
        _title = title;
    }
}

- (void)setMenuWidth:(CGFloat)menuWidth{
    if (_menuWidth != menuWidth) {
        _menuWidth = menuWidth;
    }
}

-(void)layoutSubviews{


    UIImage *iconImage = nil;
    iconImage = [RCTConvert UIImage:_icon];
    UIButton *but = [[UIButton alloc]initWithFrame:CGRectMake(0, 0, self.bounds.size.width, self.bounds.size.height)];
    [but setTitle:_title forState:UIControlStateNormal];
   but.titleLabel.font = [UIFont systemFontOfSize:15.0];
    but.titleLabel.textAlignment = NSTextAlignmentCenter;
//    [but setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    [but setImage:iconImage forState:UIControlStateNormal];
    [self addSubview:but];
    [but addTarget:self action:@selector(show:) forControlEvents:UIControlEventTouchUpInside];
    
    

}

-(void)show:(UIButton *)but{
    
    if ([_type isEqualToString:@"XXYAction"]) {
         XXYActionSheetView *alertSheetView = [[XXYActionSheetView alloc] initWithTitle:nil delegate:self cancelButtonTitle:@"取消" destructiveButtonTitle:nil otherButtonTitles:_XXYTitle];
       [alertSheetView xxy_show];
   }else if ([_type isEqualToString:@"YBPopup"]){
        [YBPopupMenu showRelyOnView:but titles:_titles icons:_icons menuWidth:_menuWidth delegate:self];
    }
}



#pragma mark - YBPopupMenuDelegate
- (void)ybPopupMenuDidSelectedAtIndex:(NSInteger)index ybPopupMenu:(YBPopupMenu *)ybPopupMenu
{
    self.onChange(@{@"index":[NSString stringWithFormat:@"%ld", index]});
    NSLog(@"点击了 %@ 选项",TITLES[index]);
}
#pragma mark - XXYActionSheetViewDelegate
- (void)actionSheet:(XXYActionSheetView *)actionSheet clickedButtonAtIndex:(NSInteger)buttonIndex{
    NSLog(@"delegate点击的是:%zd", buttonIndex);
   self.onChange(@{@"index":[NSString stringWithFormat:@"%ld", buttonIndex]});
}

@end
