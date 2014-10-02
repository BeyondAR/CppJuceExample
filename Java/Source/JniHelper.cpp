/*
  ==============================================================================

    JniHelper.cpp
    Created: 24 Sep 2014 11:29:17pm
    Author:  Joan Puig Sanz

  ==============================================================================
*/

#include "../../Common/CommonJuceHeader.h"

extern "C"
{

JNIEXPORT jstring Java_com_juceexample_JniHelper_getVersionNative (JNIEnv *env, jobject obj)
{
    String value = ProjectInfo::versionString;
    jstring jStringResult = env->NewStringUTF (value.toUTF8());
    return jStringResult;
}

JNIEXPORT int Java_com_juceexample_JniHelper_getVersionNumberNative (JNIEnv *env, jobject obj)
{
	return ProjectInfo::versionNumber;
}

}
