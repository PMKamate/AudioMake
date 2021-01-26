package com.demo.audiomake.util

import com.demo.audiomake.BuildConfig


//http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=25/xml

object ApiEndpoint {
        val ENDPOINT_SONG = BuildConfig.BASE_URL + "/MZStoreServices.woa/ws/RSS/topsongs/limit=25/xml"
}