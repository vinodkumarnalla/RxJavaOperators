package com.example.rxjavaoperators

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Function

class MainActivity : AppCompatActivity() {
    private val util = Util()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sampleBufferOperator()
        sampleMapOperator().subscribe{
           println(it)
        }
        sampleFlatMapOperator().subscribe{
            println(it)
        }
        sampleSwitchMapOperator()
    }

    //Simple Buffer operator example
    private fun sampleBufferOperator(){
        util.getObservable1().buffer(2).subscribe {
            println(it)
        }
    }

    //Simple map function which returns the $ if the value is null or empty
    private fun sampleMapOperator(): Observable<String> {
        return util.getObservable1().map(object :Function<String,String>{
            override fun apply(t: String?): String {
                return t?:"$"
            }

        })
    }

    //Flat map returns the observable
    private fun sampleFlatMapOperator():Observable<String>{
        return util.getObservable1().flatMap(object: Function <String,Observable<String>>{
            override fun apply(t: String?): Observable<String> {
               return Observable.just(t)
            }
        })
    }

    //Switch map returns the latest observable
    private fun sampleSwitchMapOperator(){
        util.getObservable1().switchMap(object: Function <String,Observable<String>>{
            override fun apply(t: String?): Observable<String> {
                return Observable.just(t)
            }
        }).subscribe {
            println("$it")
        }
    }
}