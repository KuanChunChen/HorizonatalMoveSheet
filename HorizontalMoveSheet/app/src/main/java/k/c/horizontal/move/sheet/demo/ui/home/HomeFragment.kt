package k.c.horizontal.move.sheet.demo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetBehavior
import k.c.horizontal.move.sheet.demo.R
import k.c.horizontal.move.sheet.horizontalmovesheet.widget.switchRecyclerview.model.SwitchViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }


    private fun initView(){

        horizontal_move_view.apply {
            this.setModel(mutableListOf(SwitchViewModel("google",  R.drawable.google_plus_100,"https://google.com"),
                SwitchViewModel("facebook",  R.drawable.gradient_line_100,"https://google.com"),
                SwitchViewModel("PayPal",  R.drawable.paypal_100,"https://www.paypal.com/paypalme/ElegantAccess")))
        }.show()
        horizontal_move_view.bottomBehavior.state = BottomSheetBehavior.STATE_EXPANDED

    }
}