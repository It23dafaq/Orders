import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.phone_app.Data.Products
import com.example.phone_app.Profile
import com.example.phone_app.Profile.Companion.Id
import com.example.phone_app.R
import com.example.phone_app.UI.Adapters.ChooseAdapter
import com.example.phone_app.UI.Adapters.OrderAdapter
import com.example.phone_app.UI.Adapters.cartAdapter
import com.example.phone_app.UI.Adapters.dailyAdapter
import com.example.phone_app.UI.ViewModelFactory.HomeViewModelFactory
import com.example.phone_app.UI.ViewModels.HomeViewModel
import kotlinx.android.synthetic.main.activity_daily_orders.*
import kotlinx.android.synthetic.main.fragment_admin_filter_by_name.*
import kotlinx.android.synthetic.main.fragment_admin_filter_by_name.view.*
import kotlinx.android.synthetic.main.order_daily.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class Admin_filterByName: Fragment() , KodeinAware {
    override val kodein by closestKodein()
    private val viewModelFactory: HomeViewModelFactory by instance()
    private lateinit var viewModel: HomeViewModel
   private var position : Int = 0
    private var chooseDate:String = "Today"
    private var SignUpUrl:String=" "
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_admin_filter_by_name, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)


        val drinksNames = arrayOf("Today","Week","Month","Year")
        //drinksSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1)
        spinner3.adapter = ChooseAdapter(this.requireContext(), drinksNames)

        spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                spinner3.setSelection(position).toString()


                viewModel.getOrders("Today")
            }
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                chooseDate =  parent.getItemAtPosition(position).toString()

                //viewModel.getOrdersByname(chooseDate)

            }
        }


        editText.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                viewModel.getOrdersByname(editText.toString(),chooseDate)
                false
            } else {
                true
            }
        }

        viewModel.ordersDaily.observe(this, Observer {


            val adapter = OrderAdapter(it)
            bynamerecyvler.adapter = adapter
            bynamerecyvler.layoutManager = LinearLayoutManager(this.context)
        })



    }



    companion object {

        @JvmStatic
        fun newInstance() =
            Admin_filterByName().apply {
                arguments = Bundle().apply {

                }
            }
    }
}