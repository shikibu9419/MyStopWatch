package info.shikibu.android.my_stop_watch

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val handler = Handler()
    var time = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val runnable = object : Runnable {
            override fun run() {
                time++

                timeToText(time)?.let{
                    text_display_time.text = it
                }

                handler.postDelayed(this, 1000)
            }
        }

        start_stop_button.setOnClickListener {
            if (start_stop_button.text == "START") {
                handler.post(runnable)
            } else {
                handler.removeCallbacks(runnable)
            }
            buttonTextToggle()
        }

        reset_lap_button.setOnClickListener {
            if (reset_lap_button.text == "RESET") {
                time = 0
                timeToText(time)?.let {
                    text_display_time.text = it
                }
            }
        }
    }

    private fun buttonTextToggle() {
        if (start_stop_button.text == "START") {
            start_stop_button.text = getString(R.string.stop_text)
            reset_lap_button.text = getString(R.string.lap_text)
        } else {
            start_stop_button.text = getString(R.string.start_text)
            reset_lap_button.text = getString(R.string.reset_text)
        }
    }

    private fun timeToText(time: Int): String? {
        return if (time < 0) {
            null
        } else {
            val h = time / 3600
            val m = time % 3600 / 60
            val s = time % 60
            "%1$02d:%2$02d:%3$02d".format(h, m, s)
        }
    }
}
