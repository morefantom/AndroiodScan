package android.com.androiodscan.ui.module.parameter

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.com.androiodscan.R
import android.com.androiodscan.data.Variable
import android.com.androiodscan.databinding.ParameterFragmentBinding
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.parameter_fragment.*

class ParameterFragment : Fragment() {

    companion object {

        private var variable: Variable ?= null

        fun newInstance(variable: Variable): ParameterFragment{
            ParameterFragment.variable = variable
            return ParameterFragment()
        }
    }

    private var binding: ParameterFragmentBinding ?= null
    private var parameterListAdapter: ParameterListAdapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.parameter_fragment, container, false)
        binding?.variable = variable
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        variable?.let {
            if (it.type == "value"){
                it.values?.let {list ->
                    parameterListAdapter = ParameterListAdapter(list)
                    setupRecyclerView()
                }
            }
        }
    }

    private fun setupRecyclerView(){
        recyclerView_parameter.apply {
            layoutManager = LinearLayoutManager(activity)
            parameterListAdapter?.let {
                adapter = it
            }
        }
    }

}
