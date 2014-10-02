# If you use Introjucer (not recomended) to generate this dile it will override all the needed changes.
# So if Introjucer is used, compare the files to make sure that everything works.

##############################################################################
##############################################################################
ifneq ($(RELEASE),1)
 # Set this flag to true to create a debug build. Remember that if you want to
 # create a debug build you need to add android:debuggable="true" in your 
 # manifest (in the "application" label)
 DEBUG = 1
 # Set this flag to compile the unit test code
 UNIT_TEST = $(DEBUG)
else
 # This statement is for the release mode.
 DEBUG = 0
 UNIT_TEST = 0
endif

##############################################################################
##############################################################################

# To speed up the process compile only for the architectures that you need. The 
# availablea architectures are:  armeabi armeabi-v7a x86 mips. when making the 
# final build, use "APP_ABI := armeabi x86 mips" to compile for all the platforms.
# armbi-v7 supports armeabi and we don't use the performance features that 
# armebi-v7a provides
ifeq ($(DEBUG),1)
  APP_ABI := armeabi
else
 APP_ABI := armeabi x86 mips
endif

# This is used in the build script
ifeq ($(ALL),1)
 APP_ABI := armeabi x86 mips
endif

# Toolchan 4.8 generate binaries that doesn't work on older devices (NDK known bug)
NDK_TOOLCHAIN_VERSION=4.6
# We use gnustl_static because it creates a smallest package than the gnustl_shared
APP_STL := gnustl_static
APP_CPPFLAGS += -fsigned-char -fexceptions -frtti -Wno-psabi
APP_PLATFORM := android-4
