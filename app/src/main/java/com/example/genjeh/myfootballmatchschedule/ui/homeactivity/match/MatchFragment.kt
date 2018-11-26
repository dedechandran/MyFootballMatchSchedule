package com.example.genjeh.myfootballmatchschedule.ui.homeactivity.match


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.*

import com.example.genjeh.myfootballmatchschedule.R
import com.example.genjeh.myfootballmatchschedule.ui.searchactivity.SearchActivity
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.fragment_match.*
import kotlinx.android.synthetic.main.fragment_match.view.*


/**
 * A simple [Fragment] subclass.
 *
 */
class MatchFragment : Fragment() {
    companion object {
        const val FRAGMENT_NAME = "Match"
        private const val ARG_FRAGMENT_NAME = "fragment_name"
        private const val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(sectionNumber: Int, fragmentName: String): MatchFragment {
            val fragment = MatchFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            args.putString(ARG_FRAGMENT_NAME, fragmentName)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.search_menu){
            val intent = Intent(context,SearchActivity::class.java)
            intent.putExtra(SearchActivity.ARG_ACTIVE_FRAGMENT, FRAGMENT_NAME)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    private lateinit var tabNavigation: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_match, container, false)
        val activity = activity as AppCompatActivity
        activity.setSupportActionBar(view.matchToolbar)
        activity.supportActionBar?.title = context?.resources?.getString(R.string.match)
        setHasOptionsMenu(true)

        tabNavigation = view.tabMatch
        viewPager = view.viewPagerMatch
        viewPager.adapter = MatchPagerAdapter(childFragmentManager)
        tabNavigation.setupWithViewPager(viewPager)

        return view
    }


}
