package com.example.rxjavaoperators

import io.reactivex.rxjava3.core.Observable

class Util {
    fun getObservable1(): Observable<String> {
        return Observable.just("1","2","3","4")
    }

    fun getObservable2():Observable<String>{
        return Observable.just("5","6","7","8")
    }
}