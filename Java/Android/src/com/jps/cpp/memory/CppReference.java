package com.jps.cpp.memory;

import java.lang.ref.WeakReference;

import com.jps.cpp.CppJavaBindier;

class CppReference {

	private WeakReference<CppJavaBindier> _object;
	private Class<? extends CppJavaBindier> _clazz;
	private long _ref;
	
	CppReference(long ref, CppJavaBindier object)
	{
		_ref = ref;
		_object = new WeakReference<CppJavaBindier>(object);
		_clazz = object.getClass();
	}
	
	long getReference()
	{
		return _ref;
	}
	
	boolean isJavaObjectDeleted()
	{
		return _object.get() == null;
	}
	
	Class<?> getReferencedClazz()
	{
		return _clazz;
	}
}
