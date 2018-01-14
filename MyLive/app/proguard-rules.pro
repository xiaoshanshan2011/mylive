# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#网易云混淆
-dontwarn com.netease.**
-dontwarn io.netty.**
-keep class com.netease.** {*;}
#如果 netty 使用的官方版本，它中间用到了反射，因此需要 keep。如果使用的是我们提供的版本，则不需要 keep
-keep class io.netty.** {*;}

#如果你使用全文检索插件，需要加入
-dontwarn org.apache.lucene.**
-keep class org.apache.lucene.** {*;}
