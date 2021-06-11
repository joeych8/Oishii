package com.example.oishii.calendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.oishii.R
import com.example.oishii.notification.OishiiNotificationManager
import com.example.oishii.utils.NOTIFICATION_2_ID
import java.text.DateFormat
import java.util.*

class CalendarFragment : Fragment() {


    private lateinit var sendReservationButton: TextView
    private lateinit var calendarView: CalendarView
    private lateinit var numberOfPersonsEditText: EditText
    private lateinit var selectedDateTv: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.calendar_fragment, container, false)

        sendReservationButton = view.findViewById(R.id.confirm_reservation_tv)
        calendarView = view.findViewById(R.id.calendar_view)
        numberOfPersonsEditText = view.findViewById(R.id.numberOfPerson_editText)
        selectedDateTv = view.findViewById(R.id.selected_date_tv)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        setAndSendReservation()


    }

    private fun setAndSendReservation() {


        val calendar = Calendar.getInstance()
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)

            calendarView.date = calendar.timeInMillis

            val dateFormatter = DateFormat.getDateInstance(DateFormat.MEDIUM)
            val selectedDate =
                getString(R.string.date_text) + (dateFormatter.format(calendar.time)).toString()
            selectedDateTv.text = selectedDate
            val date = (dateFormatter.format(calendar.time)).toString()

            numberOfPersonsEditText.text

            sendReservationButton.setOnClickListener {

                if (numberOfPersonsEditText.text.isEmpty()) {
                    Toast.makeText(
                        context,
                        getString(R.string.fill_in_number_of_persons_text),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {

                    OishiiNotificationManager.notificationChannel2(
                        getString(R.string.reservation_successful_text),
                        "Reservation date: $date for\n${numberOfPersonsEditText.text} person(s)",
                        NOTIFICATION_2_ID
                    )
                }
            }
        }
    }


}