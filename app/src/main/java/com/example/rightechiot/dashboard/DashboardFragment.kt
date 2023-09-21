package com.example.rightechiot.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rightechiot.databinding.FragmentDashboardBinding
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF

import kotlin.Array

import kotlin.Int
import kotlin.apply
import kotlin.random.Random


class DashboardFragment : Fragment(com.example.rightechiot.R.layout.fragment_dashboard) {

    lateinit var binding: FragmentDashboardBinding
    private val dashboardAdapter = DashboardAdapter(generateData()) { entry ->

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDashboardBinding.inflate(inflater, container, false).apply {
            binding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dashboardsPies.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@DashboardFragment.dashboardAdapter

        }
    }


    private fun generateData() = Array(Random.nextInt(1, 5)) {
        val entries: ArrayList<PieEntry> = ArrayList()
        val count = 10

        for (i in 0 until count) {
            entries.add(
                PieEntry(
                    (Random.nextFloat() / 5), ""
                )
            )
        }

        val dataSet = PieDataSet(entries, "Election Results")

        dataSet.setDrawIcons(false)

        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        // add a lot of colors


        // add a lot of colors
        val colors: ArrayList<Int> = ArrayList()

        for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)

        for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)

        for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)

        for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)

        for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)

        colors.add(ColorTemplate.getHoloBlue())

        dataSet.colors = colors
        PieData(dataSet)
    }.toList()

}