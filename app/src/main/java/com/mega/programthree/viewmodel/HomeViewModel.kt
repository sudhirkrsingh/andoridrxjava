package com.mega.programthree.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mega.programthree.model.response.PostModel
import com.mega.programthree.repo.Repository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class HomeViewModel(var repository: Repository) :ViewModel() {

    val postList = MutableLiveData<List<PostModel>>()
    val errorMessage = MutableLiveData<String>()
    lateinit var disposable: Disposable


    fun getAllPosts() {
        //observer subscribing to observable
        val response = repository.getAllPosts()
        response.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(singleObservable())
    }

    fun singleObservable(): SingleObserver<Response<List<PostModel>>> {
        return object : SingleObserver<Response<List<PostModel>>> {
            override fun onSubscribe(d: Disposable) {
                disposable=d
            }

            override fun onSuccess(data: Response<List<PostModel>>) {

                var statusCode:Int=data.code()

                postList.postValue(data.body() as List<PostModel>)

                Log.v("check","thread"+Thread.currentThread())
                when (statusCode) {
                    400 -> {
                        Log.v("400", "Server returned error: User not found")
                    }
                    401 -> {

                        Log.v("401", "Server returned error: Require user authentication")
                    }
                    404 -> {
                        Log.v(
                            "404",
                            "Server returned error:Server cannot find requested resource"
                        )

                    }
                    500 -> {
                        Log.v("500", "Server returned error: Server is broken")

                    }
                    else -> Log.v("default", "Server returned error: There might be some issue")
                }

            }

            override fun onError(e: Throwable) {
                // Error or Exception thrown.
            }


        }
    }
    private fun getPostsListObserver(): Observer<Response<List<PostModel>>> {
        return object : Observer<Response<List<PostModel>>> {
            override fun onSubscribe(d: Disposable) {

                disposable=d
                Log.v("subs","done")
            }
            override fun onNext(data:  Response<List<PostModel>>) { // run on main thread

                data.code()
                postList.postValue(data.body() as List<PostModel>)

            }

            override fun onError(e: Throwable) {}
            override fun onComplete() {}
        }
    }


}

