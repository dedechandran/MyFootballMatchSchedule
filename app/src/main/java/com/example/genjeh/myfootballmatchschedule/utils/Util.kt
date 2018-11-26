package com.example.genjeh.myfootballmatchschedule.utils


import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class Util {
    companion object {
        fun convertFormatDate(dateString: String?): String {
            val inputPattern = SimpleDateFormat("yyyy-M-d", Locale.ENGLISH)
            val outputPattern = SimpleDateFormat("E, d MMMM yyyy", Locale.ENGLISH)
            val str2date = inputPattern.parse(dateString)
            return outputPattern.format(str2date)
        }

        fun getGmtTime(timeString : String? ,hours : String?) : String{
            val inputPattern = SimpleDateFormat("HH:mm:ssZ", Locale.getDefault())
            inputPattern.timeZone = TimeZone.getTimeZone("GMT+$hours")
            val outputPattern = SimpleDateFormat("HH:mm a", Locale.getDefault())

            val str2date = inputPattern.parse(timeString)

            return outputPattern.format(str2date)
        }





    }

}