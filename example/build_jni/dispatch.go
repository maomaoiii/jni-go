package main

import "C"
import (
	"fmt"
	"unsafe"

	"github.com/maomaoiii/jni-go/example/dispatch"
)

/*
#include <stdio.h>
#include <stdlib.h>
#include <jni.h>
#include "jx.h"
*/
import "C"

//export Java_com_example_GoFunc_Dispatch
func Java_com_example_GoFunc_Dispatch(env *C.JNIEnv, clazz C.jclass, jparam C.jstring) (jres C.jstring) {
	defer func() {
		if err := recover(); err != nil {
			fmt.Printf("Recovered from panic: %v\n", err)
			empty := C.CString("")
			defer C.free(unsafe.Pointer(empty))
			jres = C.jx_NewStringUTF(env, empty)
		}
	}()
	goparam := C.jx_GetStringUTFChars(env, jparam, (*C.jboolean)(nil))
	if goparam == nil {
		// 如果获取失败，返回空字符串或抛出异常
		return C.jx_NewStringUTF(env, C.CString("?"))
	}
	defer C.jx_ReleaseStringUTFChars(env, jparam, goparam)
	paramRaw := C.GoString(goparam)
	result := dispatch.Dispatch(paramRaw)
	gores := C.CString(result)
	if gores == nil {
		// 如果分配失败，返回空字符串
		return C.jx_NewStringUTF(env, C.CString("!"))
	}
	defer C.free(unsafe.Pointer(gores))
	jres = C.jx_NewStringUTF(env, gores)
	return
}

func main() {}
