package com.example.cuanku.helper

import android.app.DatePickerDialog
import android.content.Context
import android.text.TextUtils
import java.text.NumberFormat
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

fun formatRupiah(number: Double?): String? {
    val localeID = Locale("in", "ID")
    val formatRupiah: NumberFormat = NumberFormat.getCurrencyInstance(localeID)
    return formatRupiah.format(number)
}

fun Context.showDatePicker(calendar: Calendar, listener: DatePickerDialog.OnDateSetListener) {
    val dialog = DatePickerDialog(
        this,
        listener,
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )
    dialog.datePicker.minDate = System.currentTimeMillis()
    dialog.show()


}