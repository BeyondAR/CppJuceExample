# If you use Introjucer to generate this file it will override all the needed changes.
# So if Introjucer is used compare the files to make sure that everything works.
#
# This lib includes all the common code for all the platform and the specific ones for java platforms.

PROJECT_ROOT := $(abspath $(call my-dir)../../../../)
# The path where the common C++ code is located
COMMON := $(abspath $(PROJECT_ROOT)/Common/)
JNI_SOURCES := $(abspath $(PROJECT_ROOT)/Java/Source/)
JUCE_ROOT := $(abspath $(PROJECT_ROOT)/Java/JuceLibraryCode/)
JUCE_CORE := $(abspath $(PROJECT_ROOT)/Juce/juce_core/)
JUCE_EXAMPLE_MODULE := juceexample

ACTIVITY := \"com/juceexample/android/JniHelperAndroid\"
ACTIVITY_CLASSNAME := com_juceexample_android_JniHelperAndroid

include $(CLEAR_VARS)

# To speed up the compile process you can tell the NDK to compile only for
# some specyfic platforms. See Application.mk file ro set it up.
##############################################################################
##############################################################################
ifeq ($(DEBUG),1)
  LOCAL_CPPFLAGS += -D DEBUG=1 -D NDK_DEBUG=1
  LOCAL_CFLAGS += -D DEBUG=1 -D NDK_DEBUG=1
  APP_OPTIM := debug
else
 APP_OPTIM := release
 LOCAL_CFLAGS += -D NDK_DEBUG=0
endif

ifeq ($(UNIT_TEST),1)
  LOCAL_CPPFLAGS += -D UNIT_TESTS=1
  LOCAL_CFLAGS += -D UNIT_TESTS=1
else
  LOCAL_CPPFLAGS += -D UNIT_TESTS=0
  LOCAL_CFLAGS += -D UNIT_TESTS=0
endif

LOCAL_LDLIBS := -llog

# arm features are not used, so "thumbs" arm mode is used instead of "arm" to 
# generate a smaller binary and to save battery
#LOCAL_ARM_MODE := arm

LOCAL_MODULE := $(JUCE_EXAMPLE_MODULE)

# Add here all the files that need to be compiled
LOCAL_SRC_FILES := \
  $(COMMON)/Util/Log.cpp\
  $(COMMON)/Foo/Foo.cpp\
  $(COMMON)/Foo/Bar/Bar.cpp\
  $(JNI_SOURCES)/JniHelper.cpp\
  $(JNI_SOURCES)/JniFoo.cpp\
  $(JUCE_CORE)/juce_core.cpp\
  
# Add here all the path that are used by the above code
LOCAL_C_INCLUDES := \
  $(COMMON) \
  $(JUCE_CORE) \
  
LOCAL_CPPFLAGS += -D ANDROID=1 -D JAVA=1
LOCAL_CFLAGS += -D ANDROID=1 -D JAVA=1

# Juce configuration
LOCAL_CPPFLAGS += -fsigned-char -fexceptions -frtti -g -I "$(JUCE_ROOT)" -I "$(JUCE_ROOT)" -O0 -std=c++0x -std=gnu++0x -D "JUCE_ANDROID=1" -D "JUCE_ANDROID_API_VERSION=4" -D "JUCE_ANDROID_ACTIVITY_CLASSNAME=$(ACTIVITY_CLASSNAME)" -D JUCE_ANDROID_ACTIVITY_CLASSPATH=$(ACTIVITY)
LOCAL_CFLAGS += -fsigned-char -fexceptions -frtti -g -I "$(JUCE_ROOT)" -I "$(JUCE_ROOT)" -O0 -std=c++0x -std=gnu++0x -D "JUCE_ANDROID=1" -D "JUCE_ANDROID_API_VERSION=4" -D "JUCE_ANDROID_ACTIVITY_CLASSNAME=$(ACTIVITY_CLASSNAME)" -D JUCE_ANDROID_ACTIVITY_CLASSPATH=$(ACTIVITY)

#ifeq ($(CONFIG),Debug)
ifeq ($(DEBUG),1)
  LOCAL_CPPFLAGS += -D "DEBUG=1" -D "_DEBUG=1"
  LOCAL_CFLAGS += -D "DEBUG=1" -D "_DEBUG=1"
else
  LOCAL_CPPFLAGS += -D "NDEBUG=1"
  LOCAL_CFLAGS += -D "NDEBUG=1"
endif
#END Juce configuration

# This flags are used to reduce the size of the library
LOCAL_CPPFLAGS += -ffunction-sections -fdata-sections -fvisibility=hidden
LOCAL_CFLAGS += -ffunction-sections -fdata-sections 
ifeq ($(TARGET_ARCH),mips)
  LOCAL_LDFLAGS += -Wl,--gc-sections
else
  LOCAL_LDFLAGS += -Wl,--gc-sections,--icf=safe
endif

include $(BUILD_SHARED_LIBRARY)

