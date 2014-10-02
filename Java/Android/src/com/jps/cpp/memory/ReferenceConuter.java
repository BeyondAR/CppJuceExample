package com.jps.cpp.memory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.jps.cpp.CppJavaBindier;

public class ReferenceConuter {

	private static HashMap<Long, CppReference> _cppReferences = new HashMap<Long, CppReference>();
	private static Object _lock = new Object();

	private ReferenceConuter() {
	}

	/** Keep track of a Cpp java binder in order to avoid memory leaks. */
	public static void addCppReference(long ref, CppJavaBindier object) {
		synchronized (_lock) {
			if (_cppReferences.containsKey(ref)) {
				throw new IllegalAccessError(
						"Trying to add an existing Cpp reference.");
			}
			_cppReferences.put(ref, new CppReference(ref, object));
		}
	}

	/**
	 * Checks if there is any object that has been destroyed by the GC and
	 * delete the Cpp reference if possible.
	 */
	public static void cleanCppReferences()
	{
		// We need to call the garbage collector in order to know which Objects are not used anymore.
		System.gc();
		synchronized (_lock) {
			Iterator<Map.Entry<Long, CppReference>> it = _cppReferences.entrySet().iterator();
			while (it.hasNext())
			{
				Map.Entry<Long, CppReference> cppRef = it.next();
				if (cppRef.getValue().isJavaObjectDeleted()) {
					searchForDeleteMethodAndDestroy(cppRef.getValue());
					it.remove();
				}
			}
		}
	}

	/**
	 * Return whether the reference is being tracked or not.
	 * 
	 * @param ref
	 *            The reference to the Cpp object.
	 * @return true if the reference is being tracked, false otherwise.
	 */
	public static boolean containsReference(long ref) {
		return _cppReferences.containsKey(ref);
	}

	/**
	 * Check if the Cpp java binding object is been removed by the garbage
	 * collector. If so the Cpp object can be removed.
	 * 
	 * @param ref
	 *            The reference to the Cpp object.
	 * @return True if the java object has been removed by the GC.
	 */
	public static boolean isCppJavaBindingObjectNull(long ref) {
		CppReference cppRef = _cppReferences.get(ref);
		return cppRef.isJavaObjectDeleted();
	}

	/**
	 * Delete the Cpp reference and free the memory.
	 * 
	 * @param ref
	 *            The reference to the Cpp object.
	 */
	public static void deleteCppReference(long ref) {
		synchronized (_lock) {
			CppReference cppRef = _cppReferences.get(ref);
			if (cppRef == null) {
				throw new IllegalAccessError(
						"Trying to delete a non existing Cpp reference.");
			}
			searchForDeleteMethodAndDestroy(cppRef);
			_cppReferences.remove(cppRef.getReference());
		}
	}

	private static void searchForDeleteMethodAndDestroy(CppReference cppRef) {
		Method m = getDestroyMethod(cppRef.getReferencedClazz());
		if (m == null) {
			throw new IllegalAccessError("Missing annotation "
					+ CppDestructor.class.getSimpleName()
					+ " when trying to destroy a Cpp reference.");
		}
		try {
			m.invoke(null, cppRef.getReference());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	private static Method getDestroyMethod(Class<?> clazz) {
		try {
			for (Method m : clazz.getDeclaredMethods()) {
				if (m.isAnnotationPresent(CppDestructor.class)) {
					if (!Modifier.isStatic(m.getModifiers())) {
						throw new IllegalAccessError("The method "
								+ m.getName() + " in the class "
								+ clazz.getName() + " is not static.");
					}
					if (Modifier.isPrivate(m.getModifiers())) {
						m.setAccessible(true);
					}
					return m;
				}
			}
		} catch (Exception e) {
		}
		return null;
	}
}
