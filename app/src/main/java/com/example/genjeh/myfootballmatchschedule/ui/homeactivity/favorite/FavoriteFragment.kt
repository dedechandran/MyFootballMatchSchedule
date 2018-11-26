package com.example.genjeh.myfootballmatchschedule.ui.homeactivity.favorite


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.genjeh.myfootballmatchschedule.R
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_favorite.view.*


/**
 * A simple [Fragment] subclass.
 *
 */
class FavoriteFragment : Fragment() {
    companion object {
        const val FRAGMENT_NAME = "Favorite"

        private const val ARG_SECTION_NUMBER = "section_number"
        private const val ARG_FRAGMENT_NAME = "fragment_name"

        fun newInstance(sectionNumber : Int, fragmentName : String) : FavoriteFragment{
            val fragment = FavoriteFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER,sectionNumber)
            args.putString(ARG_FRAGMENT_NAME,fragmentName)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var tabNavigation : TabLayout
    private lateinit var viewPager : ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        val activity = activity as AppCompatActivity
        activity.setSupportActionBar(view.favoriteToolbar)
        activity.supportActionBar?.title = context?.resources?.getString(R.string.favorite)

        tabNavigation = view.tabFavorite
        viewPager = view.viewPagerFavorite
        viewPager.adapter = FavoritePagerAdapter(childFragmentManager)
        tabNavigation.setupWithViewPager(viewPager)



        return view
    }


}
