package com.example.rightechiot.dashboard

import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rightechiot.R
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate


class DashboardAdapter(
    private val dashboards: List<PieData>,
    private val onPieChartListened: (Entry) -> Unit
) : RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>() {

    init {

        Unit
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        return DashboardViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_dashboard_pie, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dashboards.size
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        holder.onBind(dashboards[position])
    }

    inner class DashboardViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val chart = itemView.findViewById<PieChart>(R.id.chart)

        init {
            setupPie()

        }

        fun onBind(data: PieData) {
            chart.data = data
        }

        private fun setupPie() {
            chart.setUsePercentValues(true)
            chart.description.isEnabled = false
            chart.setExtraOffsets(5F, 10F, 5F, 5F)

            chart.dragDecelerationFrictionCoef = 0.95f
            chart.centerText = generateCenterSpannableText()

            chart.isDrawHoleEnabled = true
            chart.setHoleColor(Color.WHITE)

            chart.setTransparentCircleColor(Color.WHITE)
            chart.setTransparentCircleAlpha(110)

            chart.holeRadius = 58f
            chart.transparentCircleRadius = 61f

            chart.setDrawCenterText(true)

            chart.rotationAngle = 0F
            chart.isRotationEnabled = true
            chart.isHighlightPerTapEnabled = true

            chart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(e: Entry?, h: Highlight?) {
                    e?.let(onPieChartListened)
                }

                override fun onNothingSelected() = Unit

            })


            chart.animateY(1400, Easing.EaseInOutQuad)
            // chart.spin(2000, 0, 360);

            // chart.spin(2000, 0, 360);
            val l: Legend = chart.legend
            l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            l.orientation = Legend.LegendOrientation.VERTICAL
            l.setDrawInside(false)
            l.xEntrySpace = 7f
            l.yEntrySpace = 0f
            l.yOffset = 0f

            // entry label styling

            // entry label styling
            chart.setEntryLabelColor(Color.WHITE)
            chart.setEntryLabelTextSize(12f)
        }
    }

    private fun generateCenterSpannableText(): SpannableString {
        val s = SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda")
        s.setSpan(RelativeSizeSpan(1.7f), 0, 14, 0)
        s.setSpan(StyleSpan(Typeface.NORMAL), 14, s.length - 15, 0)
        s.setSpan(ForegroundColorSpan(Color.GRAY), 14, s.length - 15, 0)
        s.setSpan(RelativeSizeSpan(.8f), 14, s.length - 15, 0)
        s.setSpan(StyleSpan(Typeface.ITALIC), s.length - 14, s.length, 0)
        s.setSpan(ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length - 14, s.length, 0)
        return s
    }


}

