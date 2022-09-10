package com.example.cuanku.helper

import android.app.DatePickerDialog
import android.content.Context
import android.text.TextUtils
import java.sql.Timestamp
import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

fun String.isEmailValid(): Boolean {
    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun isMinimalPasswordLength(password: String): Boolean {
    val passwordRegex = Pattern.compile(
        "^" +
                ".{8,}" +
                "$"
    )

    return passwordRegex.matcher(password).matches()
}

fun isPasswordContainUppercase(password: String): Boolean {
    val passwordRegex = Pattern.compile(
        "^" +
                "(?=.*[A-Z])" +
                ".{1,}" +
                "$"
    )

    return passwordRegex.matcher(password).matches()
}

fun isPasswordContainLowercase(password: String): Boolean {
    val passwordRegex = Pattern.compile(
        "^" +
                "(?=.*[a-z])" +
                ".{1,}" +
                "$"
    )

    return passwordRegex.matcher(password).matches()
}

fun isPasswordContainNumber(password: String): Boolean {
    val passwordRegex = Pattern.compile(
        "^" +
                "(?=.*[0-9])" +
                ".{1,}" +
                "$"
    )

    return passwordRegex.matcher(password).matches()
}

fun convertToRupiah(rupiah: Double?): String? {
    if (rupiah == null) return "Rp. 0"
    val invoceString = rupiah.toString() + ""
    val rupiahFormat = NumberFormat.getInstance(Locale("id", "ID"))
    return ("Rp" + rupiahFormat.format(invoceString.toDouble()).replace(",", "."))
}

fun convertDate(
    date: String?,
    format: String = "dd-MM-yyyy"
): String {
    var formattedDate = ""
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone("UTC")

    try {
        val parseDate = sdf.parse(date)
        val LOCALE_ID = Locale("id")
        formattedDate = SimpleDateFormat(format, LOCALE_ID).format(parseDate)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return formattedDate
}
