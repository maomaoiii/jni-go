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
#include <signal.h>
#include <unistd.h>
static void blockSigurg() {
    sigset_t set;
    sigemptyset(&set);
    sigaddset(&set, SIGURG);
    sigprocmask(SIG_BLOCK, &set, NULL);
}
static int loop() {
	int i,j,sum=0 ;
	for (i=0;i<100000;i++) {
		for (j=0;j<100;j++) {
			sum++;
		}
	}
	return sum;
}
static void longSleep() {
	sleep(60*20);
}
static void fail() {
	int *p = NULL;
	*p = 42;
}
*/
import "C"

var count = 0

func init() {
	//C.blockSigurg()
	fmt.Println("blockSigurg")
}

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
	count = count + 1
	if count == 10000 {
		fmt.Println(count)
		C.fail()
	}
	defer C.free(unsafe.Pointer(gores))
	jres = C.jx_NewStringUTF(env, gores)
	return
}

//export Java_com_example_GoFunc_Dispatch2
func Java_com_example_GoFunc_Dispatch2(env *C.JNIEnv, clazz C.jclass, jparam C.jstring) C.jstring {
	goparam := C.jx_GetStringUTFChars(env, jparam, (*C.jboolean)(nil))
	defer C.jx_ReleaseStringUTFChars(env, jparam, goparam)
	paramRaw := C.GoString(goparam)
	result := dispatch.Dispatch(paramRaw)
	gores := C.CString(result)
	defer C.free(unsafe.Pointer(gores))
	jres := C.jx_NewStringUTF(env, gores)
	return jres
}

func main() {}
