package k.c.horizontal.move.sheet.demo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import k.c.horizontal.move.sheet.demo.R
import k.c.horizontal.move.sheet.demo.constants.UrlType
import k.c.horizontal.move.sheet.horizontalmovesheet.widget.switchRecyclerview.model.SwitchViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)!!.supportActionBar?.show()
        initView()

    }


    private fun initView(){

        horizontal_move_view.apply {
            this.setModel(mutableListOf(SwitchViewModel(UrlType.Github.name,  R.drawable.github_100,UrlType.Github.value),
                SwitchViewModel(UrlType.Instagram.name,  R.drawable.instagram_100,UrlType.Instagram.value),
                SwitchViewModel(UrlType.Google.name,  R.drawable.google_plus_100,UrlType.Google.value)))
        }.show()
        horizontal_move_view.bottomBehavior.state = BottomSheetBehavior.STATE_EXPANDED

    }
}