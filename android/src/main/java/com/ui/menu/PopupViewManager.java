package com.ui.menu;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dowin on 2017/5/31.
 */

public class PopupViewManager extends SimpleViewManager<TextView> {

    final String TAG = "Menu";
    private String title;
    private ReactContext context;
    private String[] menu;
    private List<String> action;
    private Drawable[] icon;
    private Type curType = Type.YBPopup;

    enum Type {
        XXYAction("XXYAction", "底部选择框"), YBPopup("YBPopup", "尖角提示框");

        private String type;
        private String value;

        Type(String type, String value) {
            this.type = type;
            this.value = value;

        }

        public static Type typeOfValue(String type) {

            for (Type type1 : Type.values()) {
                if (type1.type.equals(type)) {
                    return type1;
                }
            }
            return Type.YBPopup;
        }

        public String getValue() {
            return value;
        }

        public String getType() {
            return type;
        }
    }

    @Override
    public String getName() {
        return "Menu";
    }

    @Override
    protected TextView createViewInstance(ThemedReactContext reactContext) {


        context = reactContext;
        final TextView textView = new TextView(reactContext);
        textView.setText(title);
        textView.setGravity(Gravity.CENTER);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (curType) {

                    case XXYAction:
                        showAction(textView, title, action);
                        break;
                    case YBPopup:
                        if (listener == null) {
                            listener = new PopupMenu.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem item) {
                                    WritableMap map = Arguments.createMap();
                                    map.putString("title", item.getTitle().toString());
                                    map.putString("index", Integer.toString(item.getOrder()));
                                    context.getJSModule(RCTEventEmitter.class).receiveEvent(textView.getId(), "topChange", map);
                                    return false;
                                }
                            };
                        }
                        showPopup(context.getCurrentActivity(), textView, menu, listener);
                        break;
                    default:
                        break;
                }

            }
        });
        return textView;
    }

    private PopupMenu.OnMenuItemClickListener listener;

    @ReactProp(name = "titles")
    public void setTitles(TextView view, ReadableArray titles) {
        if (titles != null && titles.size() > 0) {
            this.menu = new String[titles.size()];
            for (int i = 0; i < titles.size(); i++) {
                this.menu[i] = titles.getString(i);
            }
        }
    }

    @ReactProp(name = "icons")
    public void setIcons(TextView view, ReadableArray icons) {

        if (icons != null && icons.size() > 0) {

            this.icon = new Drawable[icons.size()];
            for (int i = 0; i < icons.size(); i++) {
                ReadableMap map = icons.getMap(i);
                if (map.hasKey("uri")) {
                    icon[i] = ImageLoader.loadImage(context, map.getString("uri"));
                }
            }
        }
    }

    @ReactProp(name = "XXYTitle")
    public void setXXYTitle(TextView view, ReadableArray XXYTitle) {
        if (XXYTitle != null && XXYTitle.size() > 0) {
            action = new ArrayList<>(XXYTitle.size());
            for (int i = 0; i < XXYTitle.size(); i++) {
                this.action.add(XXYTitle.getString(i));
            }
        }
    }

    @ReactProp(name = "type")
    public void setType(TextView view, String type) {
        curType = Type.typeOfValue(type);
    }

    @ReactProp(name = "icon")
    public void setIcon(TextView view, ReadableMap icon) {
        Log.i(TAG, "" + icon);
        if (icon.hasKey("uri"))
            view.setBackground(ImageLoader.loadImage(context, icon.getString("uri")));
    }

    @ReactProp(name = "title")
    public void setTitle(TextView view, String title) {
        view.setText(title);
        this.title = title;
    }

    //    titles:PropTypes.array,
//    icons:PropTypes.array,
//    XXYTitle:PropTypes.array,
//
//    type:PropTypes.string,
//    icon:PropTypes.string,
//    title:PropTypes.string,
    private void showAction(final TextView textView, String title, final List<String> action) {
        View view = LayoutInflater.from(context).inflate(R.layout.action, null);
        final PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        ((TextView) view.findViewById(R.id.title)).setText(title);
        int length = 0;
        if (action != null && action.size() > 0) {
            length = action.size();
            ListView listView = (ListView) view.findViewById(R.id.list_view);

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, action);
            listView.setAdapter(arrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    popupWindow.dismiss();
                    WritableMap map = Arguments.createMap();
                    map.putString("title", action.get(position));
                    map.putString("index", Integer.toString(position));
                    context.getJSModule(RCTEventEmitter.class).receiveEvent(textView.getId(), "topChange", map);
                }
            });
        }
        final Button btn = (Button) view.findViewById(R.id.cancel);
        final int finalLength = length;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                WritableMap map = Arguments.createMap();
                map.putString("title", btn.getText().toString());
                map.putString("index", Integer.toString(finalLength));
                context.getJSModule(RCTEventEmitter.class).receiveEvent(textView.getId(), "topChange", map);
            }
        });

        ColorDrawable drawable = new ColorDrawable(Color.parseColor("#00000000"));
        popupWindow.setBackgroundDrawable(drawable);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    private void setIconEnable(Menu menu, boolean enable) {
        try {// android.support.v7.internal.view.menu.MenuBuilder
            Class<?> clazz = Class.forName("com.android.internal.view.menu.MenuBuilder");
            Method m = clazz.getDeclaredMethod("setOptionalIconsVisible", boolean.class);
            m.setAccessible(true);
            m.invoke(menu, enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showPopup(final Activity activity, final TextView moreMenu, final String[] menuList, final PopupMenu.OnMenuItemClickListener onMenuItemClickListener) {

        PopupMenu popup = new PopupMenu(activity, moreMenu);
        //Inflating the Popup using xml file
        setIconEnable(popup.getMenu(), true);

        if (menuList != null && menuList.length > 0) {
            for (int i = 0; i < menuList.length; i++) {
                MenuItem menuItem = popup.getMenu().add(i, i, i, menuList[i]);
                if (icon != null) {
                    if (i < icon.length) {
                        menuItem.setIcon(icon[i]);
                    }
                }
            }
        }
        setIconEnable(popup.getMenu(), true);
//                popup.getMenuInflater().inflate(getMenuId(activity, menuName), popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(onMenuItemClickListener);

        popup.show(); //showing popup menu
    }

    public int getDrawableById(String text) {
        int id = context.getResources().getIdentifier(text, "drawable", context.getPackageName());
        return id;
    }
}
