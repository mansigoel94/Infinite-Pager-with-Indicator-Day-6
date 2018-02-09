package com.example.mansigoel.viewpager_video

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    private lateinit var mIndicator: MyPageIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //adding video uri and starting video
        val uri = Uri.parse("android.resource://" + packageName + "/"
                + R.raw.video)
        vv_background.setVideoURI(uri)
        vv_background.requestFocus()
        vv_background.start()

        //setting video on loop so it plays continuously and without volume
        vv_background.setOnPreparedListener(MediaPlayer.OnPreparedListener { mediaPlayer ->
            mediaPlayer.isLooping = true
            mediaPlayer.setVolume(0f, 0f)
        })

        container.bringToFront()
        pagesContainer.bringToFront()

        val fragments = ArrayList<Fragment>()
        fragments.add(PageFragment.newInstance(baseContext.getString(R.string.welcome),
                baseContext.getString(R.string.sign_up_for_free_music_on_your_phone_tablet_and_computer)))
        fragments.add(PageFragment.newInstance(getString(R.string.browse),
                getString(R.string.browse_desc)))
        fragments.add(PageFragment.newInstance(getString(R.string.search),
                getString(R.string.search_desc)))
        fragments.add(PageFragment.newInstance(getString(R.string.running),
                getString(R.string.runnig_desc)))
        fragments.add(PageFragment.newInstance(baseContext.getString(R.string.library),
                baseContext.getString(R.string.library_desc)))

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, fragments)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter
        mIndicator = MyPageIndicator(this@MainActivity, pagesContainer, container, R.drawable.indicator_circle)
        mIndicator.setPageCount(fragments.size)
        mIndicator.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mIndicator.cleanup()
    }

    inner class SectionsPagerAdapter(fm: FragmentManager, fragments: List<Fragment>) : FragmentStatePagerAdapter(fm) {
        private lateinit var mFragments: List<Fragment>

        init {
            mFragments = fragments
        }

        override fun getItem(position: Int): Fragment {
            /* when (position) {
                 0 -> return PageFragment.newInstance(baseContext.getString(R.string.welcome),
                         baseContext.getString(R.string.sign_up_for_free_music_on_your_phone_tablet_and_computer))
                 1 -> return PageFragment.newInstance(getString(R.string.browse),
                         getString(R.string.browse_desc))
                 2 -> return PageFragment.newInstance(getString(R.string.search),
                         getString(R.string.search_desc))
                 3 -> return PageFragment.newInstance(getString(R.string.running),
                         getString(R.string.runnig_desc))
                 4 -> return PageFragment.newInstance(getString(R.string.library),
                         getString(R.string.library_desc))
                 else -> return PageFragment.newInstance(baseContext.getString(R.string.welcome),
                         baseContext.getString(R.string.sign_up_for_free_music_on_your_phone_tablet_and_computer))
             }*/
            val index = position % mFragments.size
            return mFragments.get(index)
        }

        override fun getCount(): Int {
            return Int.MAX_VALUE
        }
    }
}
