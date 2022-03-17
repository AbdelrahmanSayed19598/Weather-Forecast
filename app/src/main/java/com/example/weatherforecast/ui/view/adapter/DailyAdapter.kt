package com.example.weatherforecast.ui.view.adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R
import com.example.weatherforecast.data.model.Daily
import com.example.weatherforecast.lat
import kotlinx.android.synthetic.main.daily_weather_row.view.*
import java.util.*

class DailyAdapter(val dailyPojo: List<Daily>,val context: Context) : RecyclerView.Adapter<DailyAdapter.MyViewHolder>() {

    lateinit var sharedPreferences: SharedPreferences

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dayTxt = itemView.txt_day!!
        val imgDaily = itemView.daily_icon!!
        val statusDaily = itemView.weather_status!!
        val tempratureProbabilty = itemView.temprature_probability!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val recyclerViewItem =
            LayoutInflater.from(parent.context).inflate(R.layout.daily_weather_row, parent, false)
        return MyViewHolder(recyclerViewItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.dayTxt.text = dayFormat(dailyPojo[position].dt.toInt())
        holder.statusDaily.text = dailyPojo[position].weather[0].description.toString()

        var min = dailyPojo[position].temp.min.toInt()
        var max = dailyPojo[position].temp.max.toInt()
        holder.tempratureProbabilty.text = min.toString() + "/" + max + "°c"

        when (dailyPojo[position].weather[0].icon) {

            "01d" -> holder.imgDaily.setImageResource(R.drawable.ic_sun_svgrepo_com)
            "01n" -> holder.imgDaily.setImageResource(R.drawable.ic_moon_svgrepo_com)
            "02d" -> holder.imgDaily.setImageResource(R.drawable.twod)
            "02n" -> holder.imgDaily.setImageResource(R.drawable.twon)
            "03d" -> holder.imgDaily.setImageResource(R.drawable.threed)
            "03n" -> holder.imgDaily.setImageResource(R.drawable.threen)
            "04d" -> holder.imgDaily.setImageResource(R.drawable.fourd)
            "04n" -> holder.imgDaily.setImageResource(R.drawable.fourn)
            "09d" -> holder.imgDaily.setImageResource(R.drawable.nined)
            "09n" -> holder.imgDaily.setImageResource(R.drawable.ninen)
            "10d" -> holder.imgDaily.setImageResource(R.drawable.tend)
            "10n" -> holder.imgDaily.setImageResource(R.drawable.tenn)
            "11d" -> holder.imgDaily.setImageResource(R.drawable.eleven_d)
            "11n" -> holder.imgDaily.setImageResource(R.drawable.eleven_n)
            "13d" -> holder.imgDaily.setImageResource(R.drawable.ic_snow_svgrepo_com)
            "13n" -> holder.imgDaily.setImageResource(R.drawable.ic_snow_svgrepo_com)
            "50d" -> holder.imgDaily.setImageResource(R.drawable.fifty_d)
            "50n" -> holder.imgDaily.setImageResource(R.drawable.fifty_n)
        }
    }

    private fun dayFormat(milliSecond: Int): String {
        sharedPreferences = context.getSharedPreferences(lat, Context.MODE_PRIVATE)
        var lang = sharedPreferences.getString("lang", "en")
        val calendar: Calendar = Calendar.getInstance()
        calendar.setTimeInMillis(milliSecond.toLong() * 1000)
        var date = calendar.get(Calendar.DAY_OF_MONTH)
            .toString() + "/" + (calendar.get(Calendar.MONTH) + 1).toString()
        var day = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale(lang))
            .toString()
        return day
    }

    override fun getItemCount(): Int {
        return dailyPojo.size
    }
}