package com.example.rightechiot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rightechiot.databinding.FragmentDashboardsBinding
import retrofit2.Call
import retrofit2.Response

class DashboardsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dashBoardArrayList: ArrayList<DashBoardDescription>

    lateinit var dashBoardName : Array<String>
    lateinit var dashBoardDescription : Array<String>
    lateinit var dashBoardStar : Array<Int>
    lateinit var binding : FragmentDashboardsBinding

    private val adapter = ChartAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        retrofit.getModels().enqueue(
            object: retrofit2.Callback<ArrayOfUniqueModel> {
                override fun onResponse(
                    call: Call<ArrayOfUniqueModel>,
                    response: Response<ArrayOfUniqueModel>
                ) {
                    if (response.body() != null) {
                        for(i in response.body()?.array!!) {
                            val dasboardDescription = DashBoardDescription(i.name,i.description,false)
                            adapter.addItem(dasboardDescription)
                        }
                    } else{
                        Toast.makeText(requireContext(), "no models to add", Toast.LENGTH_SHORT).show();
                    }
                }

                override fun onFailure(call: Call<ArrayOfUniqueModel>, t: Throwable) {
                    Toast.makeText(requireContext(), "Can't get models from server", Toast.LENGTH_SHORT).show();
                }
            }
        )
        binding = FragmentDashboardsBinding.inflate(inflater,container,false)
        var counter = 0
        binding.addDashboardButton.setOnClickListener{
            val dasboardDescription = DashBoardDescription("Dashboard- $counter","Description - $counter",false)
            val obj = UniqueModel("mqtt",dasboardDescription.name,dasboardDescription.description)
            retrofit.createModel(obj)

            adapter.addItem(dasboardDescription)
            counter++
        }
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.dashboards_rv6)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = DashboardsFragment()
    }
}