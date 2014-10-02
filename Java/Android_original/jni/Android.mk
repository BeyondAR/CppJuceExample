# Automatically generated makefile, created by the Introjucer
# Don't edit this file! Your changes will be overwritten when you re-save the Introjucer project!

LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_ARM_MODE := arm
LOCAL_MODULE := juce_jni
LOCAL_SRC_FILES := \
  ../../../Source/Foo.cpp\
  ../../../Juce/juce_core/juce_core.cpp\

ifeq ($(CONFIG),Debug)
  LOCAL_CPPFLAGS += -fsigned-char -fexceptions -frtti -g -I "../../JuceLibraryCode" -I "../../Juce" -O0 -std=c++0x -std=gnu++0x -D "JUCE_ANDROID=1" -D "JUCE_ANDROID_API_VERSION=8" -D "JUCE_ANDROID_ACTIVITY_CLASSNAME=com_example_lib_juce_juceexamplelib" -D JUCE_ANDROID_ACTIVITY_CLASSPATH=\"com/example/lib/juce/juceexamplelib\" -D "DEBUG=1" -D "_DEBUG=1" -D "ANDROID=1" -D "JAVA=1" -D "JUCER_ANDROID_7F0E4A25=1" -D "JUCE_APP_VERSION=1.0.0" -D "JUCE_APP_VERSION_HEX=0x10000"
  LOCAL_LDLIBS := -llog -lGLESv2
  LOCAL_CFLAGS += -fsigned-char -fexceptions -frtti -g -I "../../JuceLibraryCode" -I "../../Juce" -O0 -std=c++0x -std=gnu++0x -D "JUCE_ANDROID=1" -D "JUCE_ANDROID_API_VERSION=8" -D "JUCE_ANDROID_ACTIVITY_CLASSNAME=com_example_lib_juce_juceexamplelib" -D JUCE_ANDROID_ACTIVITY_CLASSPATH=\"com/example/lib/juce/juceexamplelib\" -D "DEBUG=1" -D "_DEBUG=1" -D "ANDROID=1" -D "JAVA=1" -D "JUCER_ANDROID_7F0E4A25=1" -D "JUCE_APP_VERSION=1.0.0" -D "JUCE_APP_VERSION_HEX=0x10000"
  LOCAL_LDLIBS := -llog -lGLESv2
else
  LOCAL_CPPFLAGS += -fsigned-char -fexceptions -frtti -I "../../JuceLibraryCode" -I "../../Juce" -Os -std=c++0x -std=gnu++0x -D "JUCE_ANDROID=1" -D "JUCE_ANDROID_API_VERSION=8" -D "JUCE_ANDROID_ACTIVITY_CLASSNAME=com_example_lib_juce_juceexamplelib" -D JUCE_ANDROID_ACTIVITY_CLASSPATH=\"com/example/lib/juce/juceexamplelib\" -D "NDEBUG=1" -D "ANDROID=1" -D "JAVA=1" -D "JUCER_ANDROID_7F0E4A25=1" -D "JUCE_APP_VERSION=1.0.0" -D "JUCE_APP_VERSION_HEX=0x10000"
  LOCAL_LDLIBS := -llog -lGLESv2
  LOCAL_CFLAGS += -fsigned-char -fexceptions -frtti -I "../../JuceLibraryCode" -I "../../Juce" -Os -std=c++0x -std=gnu++0x -D "JUCE_ANDROID=1" -D "JUCE_ANDROID_API_VERSION=8" -D "JUCE_ANDROID_ACTIVITY_CLASSNAME=com_example_lib_juce_juceexamplelib" -D JUCE_ANDROID_ACTIVITY_CLASSPATH=\"com/example/lib/juce/juceexamplelib\" -D "NDEBUG=1" -D "ANDROID=1" -D "JAVA=1" -D "JUCER_ANDROID_7F0E4A25=1" -D "JUCE_APP_VERSION=1.0.0" -D "JUCE_APP_VERSION_HEX=0x10000"
  LOCAL_LDLIBS := -llog -lGLESv2
endif

include $(BUILD_SHARED_LIBRARY)
