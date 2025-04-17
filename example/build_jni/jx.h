#ifndef JIN_WRAPPER
#define JIN_WRAPPER

#include <jni.h>
const char* jx_GetStringUTFChars(JNIEnv *env, jstring str, jboolean *isCopy) ;

void jx_ReleaseStringUTFChars(JNIEnv *env, jstring str, const char * res);

jstring jx_NewStringUTF(JNIEnv *env, const char * bytes);

#endif

