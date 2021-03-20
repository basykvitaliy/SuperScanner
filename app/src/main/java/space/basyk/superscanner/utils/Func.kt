package space.basyk.superscanner.utils

import android.widget.Toast

fun showToast(mess: String){
    Toast.makeText(APP_ACTIVITY, mess, Toast.LENGTH_SHORT).show()
}