package com.example.singlemovie.data.repository

enum class Status{
    RUNNING,
    SUCCES,
    FAILED,
}

class NetworkState ( val status: Status, val msg: String ){

    companion object{

        val LOADED: NetworkState = NetworkState(Status.SUCCES, "Başarılı Bir Şekilde Yüklendi")
        val LOADING: NetworkState = NetworkState(Status.RUNNING,"Yükleniyor")
        val EROR: NetworkState = NetworkState(Status.FAILED,"Hata")
        val ENDOFLIST : NetworkState = NetworkState(Status.FAILED, "Sayfanın Sonuna Ulaştınız")

    }
}