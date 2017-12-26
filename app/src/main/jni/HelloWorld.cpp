//
// Created by tansheng on 2017/9/22.
//

#include "com_jarvis_tbaseview_jni_JniHelloWorld.h"

JNIEXPORT jint JNICALL Java_com_jarvis_tbaseview_jni_JniHelloWorld_square
        (JNIEnv * env, jclass jcls, jint number){
    return number*number;
}


//JNIEXPORT jstring JNICALL Java_com_jarvis_tbaseview_jni_JniHelloWorld_setSay
//        (JNIEnv * env, jclass jcls, jstring str){
//
//
//    return  (*env)->(NewString("Hello JNi"));
//}
