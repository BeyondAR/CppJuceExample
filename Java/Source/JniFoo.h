/*
  ==============================================================================

    JniFoo.h
    Created: 24 Sep 2014 11:29:17pm
    Author:  Joan Puig Sanz

  ==============================================================================
*/

#ifndef JNIHELPER_H_INCLUDED
#define JNIHELPER_H_INCLUDED

#include "../../Common/CommonJuceHeader.h"

#ifdef __cplusplus
extern "C"
{
#endif
	JNIEXPORT int Java_com_juceexample_Foo_newCppInstanceNative (JNIEnv *env, jobject obj);

	JNIEXPORT jstring Java_com_juceexample_foo_Foo_getStringNative (JNIEnv *env, jobject obj, long ref);

	JNIEXPORT void Java_com_juceexample_foo_Foo_destroyCppInstanceNative (JNIEnv *env, jobject obj, int64 ref);

#ifdef __cplusplus
}
#endif

#endif /* JNIHELPER_H_INCLUDED */
