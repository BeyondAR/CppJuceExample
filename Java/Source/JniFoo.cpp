/*
  ==============================================================================

    JniHelper.cpp
    Created: 24 Sep 2014 11:29:17pm
    Author:  Joan Puig Sanz

  ==============================================================================
*/
#include "JniFoo.h"

#include "../../Common/Foo/Foo.h"
#include "../../Common/Util/Log.h"

extern "C"
{

JNIEXPORT long Java_com_juceexample_foo_Foo_newCppInstanceNative (JNIEnv *env, jobject obj)
{
	Foo* newFoo = new Foo();
    int64 ref = reinterpret_cast<int64>(newFoo);
    LOG_D("New Foo Cpp instance, reference = " + String (ref));
    return ref;
}

JNIEXPORT jstring Java_com_juceexample_foo_Foo_getStringNative (JNIEnv *env, jobject obj, long ref)
{
	Foo* foo = reinterpret_cast<Foo*>(ref);
	jstring jStringResult = env->NewStringUTF (foo->getString().toUTF8());
	return jStringResult;
}

JNIEXPORT void Java_com_juceexample_foo_Foo_destroyCppInstanceNative (JNIEnv *env, jobject obj, int64 ref)
{
	if (ref == 0) return;

	Foo* foo = reinterpret_cast<Foo*>(ref);
	delete foo;
    LOG_D("Destroyed Foo Cpp instance, reference = " + String (ref));
}

}
