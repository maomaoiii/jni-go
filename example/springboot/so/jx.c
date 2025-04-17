#include "jx.h"
const char* jx_GetStringUTFChars(JNIEnv *env, jstring str, jboolean *isCopy) {
    return (*env)->GetStringUTFChars(env, str, isCopy);
}

void jx_ReleaseStringUTFChars(JNIEnv *env, jstring str, const char * res) {
    (*env)->ReleaseStringUTFChars(env, str, res);
}

jstring jx_NewStringUTF(JNIEnv *env, const char * bytes) {
    return (*env)->NewStringUTF(env, bytes);
}