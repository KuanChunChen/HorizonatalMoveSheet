package k.c.horizontal.move.sheet.demo.ui.mine

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import k.c.horizontal.move.sheet.demo.R
import k.c.horizontal.move.sheet.demo.constants.UrlType
import k.c.horizontal.move.sheet.demo.ui.WebViewActivity
import kotlinx.android.synthetic.main.fragment_notifications.*

class MineFragment : Fragment(), View.OnClickListener {


    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
//        val textView: TextView = root.findViewById(R.id.text_notifications)
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
        })

        layout_ig.setOnClickListener(this)
        return root
    }

    private fun startWebActivity(urlType: UrlType) {
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra("START_URL", urlType.value)
        requireActivity().startActivity(intent)

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.layout_ig -> startWebActivity(UrlType.IG)
            R.id.layout_github -> startWebActivity(UrlType.GITHUB)
            R.id.layout_donate -> startWebActivity(UrlType.PAYPAL)
        }

    }
}