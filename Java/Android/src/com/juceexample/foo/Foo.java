package com.juceexample.foo;

import com.jps.cpp.CppJavaBindier;
import com.jps.cpp.memory.CppDestructor;
import com.jps.cpp.memory.ReferenceConuter;

public class Foo implements CppJavaBindier{
	
	private native long newCppInstanceNative();
	
	@CppDestructor
	private static native void destroyCppInstanceNative(long ref);
	
	private native String getStringNative(long ref);
	
	// C++ Object reference
	private long _ref = 0; //nullptr in cpp
	private Object _lock = new Object();
	
	public Foo()
	{
		_ref = newCppInstanceNative();
		ReferenceConuter.addCppReference(_ref, this);
	}
	
	public void destroy()
	{
		synchronized (_lock) {
			checkReference();
			ReferenceConuter.deleteCppReference	(_ref);
		}
	}
	
	public String getString()
	{
		synchronized (_lock) {
			checkReference();
			return getStringNative(_ref);
		}
	}
	
	private void checkReference()
	{
		if (_ref == 0){
			throw new IllegalAccessError("The Cpp object is not created.");
		}
	}
}
