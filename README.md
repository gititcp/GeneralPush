# GeneralPush
通用推送

gradle使用：

一、Project下的build.gradle文件下添加

allprojects {
    repositories {
      ...
      maven { url 'https://jitpack.io' }
    }
}

二、Module下的build.gradle文件下添加

dependencies {
          compile 'com.github.Lvluffy:push:1.0.1'
}

或者

dependencies {
          implementation 'com.github.Lvluffy:push:1.0.1'
}
# GeneralPush
# GeneralPush
# GeneralPush
