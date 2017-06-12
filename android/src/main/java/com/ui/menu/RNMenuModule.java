
package com.ui.menu;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class RNMenuModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNMenuModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNMenu";
  }
}