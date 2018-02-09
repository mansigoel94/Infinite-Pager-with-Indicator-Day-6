package com.example.mansigoel.viewpager_video

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_page.view.*

 class PageFragment : Fragment() {

    private var mParam2: String? = null
    private var mParam1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_page, container, false)
        view.tv_title.text = mParam1
        view.tv_description.text = mParam2
        return view
    }

     companion object {
         private val ARG_PARAM1: String = "title"
         private val ARG_PARAM2: String = "desc"

         fun newInstance(param1: String, param2: String): PageFragment {
             val fragment = PageFragment()
             val args = Bundle()
             args.putString(ARG_PARAM1, param1)
             args.putString(ARG_PARAM2, param2)
             fragment.arguments = args
             return fragment
         }
     }
}