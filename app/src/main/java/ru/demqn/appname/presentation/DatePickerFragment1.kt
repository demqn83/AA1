package ru.demqn.appname.presentation

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private var listener: DatePickerFragmentClicks? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is DatePickerFragmentClicks) {
            listener = context
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val c: Calendar = Calendar.getInstance()
        val year: Int = c.get(Calendar.YEAR)
        val month: Int = c.get(Calendar.MONTH)
        val day: Int = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(requireContext(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        listener?.processDatePickerResult(
            year,
            month,
            dayOfMonth,
            requireNotNull(arguments?.getString(NAME_MOVIE))
        )
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {

        const val NAME_MOVIE = "nameMovie"

        fun newInstance(nameMovie: String) = DatePickerFragment().apply {
            arguments = Bundle().apply {
                putString(NAME_MOVIE, nameMovie)
            }
        }

    }

    interface DatePickerFragmentClicks {
        fun processDatePickerResult(year: Int, month: Int, dayOfMonth: Int, movieName: String)
    }
}