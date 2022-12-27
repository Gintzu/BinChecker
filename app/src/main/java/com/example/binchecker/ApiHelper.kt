package com.example.binchecker

import kotlinx.coroutines.*
import java.net.URL

class ApiHelper {
    companion object {
        suspend fun getData(arg: String, renderData: (String) -> () -> Unit) = coroutineScope {
            async {
                renderData(URL("https://lookup.binlist.net/" + arg).readText())()
            }
        }.await()
    }
}