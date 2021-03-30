package ru.demqn.appname.presentation

import android.app.Dialog
import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class CalendarSelectionDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        val uri: Uri = CalendarContract.Calendars.CONTENT_URI
//        val selection: String = "((${CalendarContract.Calendars.ACCOUNT_NAME} = ?) AND (" +
//                "${CalendarContract.Calendars.ACCOUNT_TYPE} = ?) AND (" +
//                "${CalendarContract.Calendars.OWNER_ACCOUNT} = ?))"
//        val selectionArgs: Array<String> = arrayOf("hera@example.com", "com.example", "hera@example.com")
        val cur: Cursor? = requireContext().contentResolver.query(uri, EVENT_PROJECTION, null, null, null)

        while (cur!!.moveToNext()) {
            // Get the field values
            val calID: Long = cur.getLong(PROJECTION_ID_INDEX)
            val displayName: String = cur.getString(PROJECTION_DISPLAY_NAME_INDEX)
            val accountName: String = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX)
            val ownerName: String = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX)

            Log.d("qwerty", "$calID - $displayName - $accountName - $ownerName")

        }

        cur.close()

        return requireActivity().let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Title")
                .setItems(
                    arrayOf("Select1", "Select2", "Select3"),
                    { dialog, which ->
                        Log.d("Enter", "$dialog - $which")
                    })
            builder.create()
        }
    }


    companion object {
        private const val PROJECTION_ID_INDEX: Int = 0
        private const val PROJECTION_DISPLAY_NAME_INDEX: Int = 2
        private const val PROJECTION_ACCOUNT_NAME_INDEX: Int = 1
        private const val PROJECTION_OWNER_ACCOUNT_INDEX: Int = 3
        private val EVENT_PROJECTION: Array<String> = arrayOf(
            CalendarContract.Calendars._ID,                     // 0
            CalendarContract.Calendars.ACCOUNT_NAME,            // 1
            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,   // 2
            CalendarContract.Calendars.OWNER_ACCOUNT            // 3
        )
    }

}