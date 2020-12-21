package com.thaiduong.shapechallenge.base

import java.util.concurrent.Executors

class ThreadManager {
    var networkPool = Executors.newFixedThreadPool(10)

    companion object {
        private var sInstance: ThreadManager? = null

        fun getInstance(): ThreadManager {
            if (sInstance == null) {
                sInstance =
                    ThreadManager()
            }
            return sInstance as ThreadManager
        }
    }
}