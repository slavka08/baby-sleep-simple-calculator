package by.slavintodron.sleepcalc

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import by.slavintodron.sleepcalc.databinding.ActivityMainBinding
import java.time.ZoneOffset.UTC
import java.util.*
import java.util.Calendar.*


private lateinit var binding: ActivityMainBinding
var wakeUpTime: Calendar = Calendar.getInstance()
var playTime: Calendar = Calendar.getInstance()
var resultTime: Calendar = Calendar.getInstance()

class MainActivity : AppCompatActivity() {
    private var sleepTime = TimeCalc()
    val TAG = "BABY_SLEEP"
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.datetimePickerPlay.setIs24HourView(true)
        binding.datetimePickerWakeUp.setIs24HourView(true)


        //чистим пикер времени которое добавляем
        binding.datetimePickerPlay.hour = 0
        binding.datetimePickerPlay.minute = 0


        binding.datetimePickerWakeUp.setOnTimeChangedListener { view, hourOfDay, minute ->
            var cldr = getInstance() //GregorianCalendar(TimeZone.getTimeZone("UTC"));
            cldr.clear()

            cldr.set(HOUR_OF_DAY, hourOfDay)
            cldr.set(MINUTE, minute)
            cldr.set(SECOND, 0)
            cldr.set(MILLISECOND, 0)
           //cldr.timeZone = "UTC+7:00";
            sleepTime.SetWakeUpCalendar(cldr)
            Log.i(TAG, "cldr =\t" + cldr.getTime().getTime().toString())
            binding.editTextTimePlay.setText(sleepTime.timeWhenGoToBed())

        }

        binding.datetimePickerPlay.setOnTimeChangedListener { view, hourOfDay, minute ->
            var cldr = GregorianCalendar(TimeZone.getTimeZone("UTC"));

            cldr.clear()
            cldr.set(HOUR_OF_DAY, hourOfDay)
            cldr.set(MINUTE, minute)

            sleepTime.SetTimeOfPlay(cldr)

            binding.editTextTimePlay.setText(sleepTime.timeWhenGoToBed())
        }
    }
}