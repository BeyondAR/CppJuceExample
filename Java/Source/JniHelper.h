/*
  ==============================================================================

    JniHelper.h
    Created: 24 Sep 2014 11:29:17pm
    Author:  Joan Puig Sanz

  ==============================================================================
*/

#ifndef JNIHELPER_H_INCLUDED
#define JNIHELPER_H_INCLUDED

#ifdef __cplusplus
extern "C"
{
#endif
	JNIEXPORT jstring Java_com_juceexample_JniHelper_getVersionNative (JNIEnv *env, jobject obj);

	JNIEXPORT int Java_com_juceexample_JniHelper_getVersionNumberNative (JNIEnv *env, jobject obj);

#ifdef __cplusplus
}
#endif
#endif

#endif /* JNIHELPER_H_INCLUDED */
