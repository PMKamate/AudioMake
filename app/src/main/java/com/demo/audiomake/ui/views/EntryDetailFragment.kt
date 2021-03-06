package com.demo.audiomake.ui.views

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.demo.audiomake.R
import com.demo.audiomake.databinding.EntryDetailFragmentBinding
import com.demo.audiomake.ui.viewmodels.EntryDetailViewModel
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.entry_detail_fragment.*

/**
 * Details screen for Entry
 * */

class EntryDetailFragment : Fragment() {
    val TAG: String = EntryDetailFragment::class.java.simpleName

    companion object {
        const val ENTRYOBJ = "entryObj"
    }

    private lateinit var viewModel: EntryDetailViewModel
    private lateinit var binding: EntryDetailFragmentBinding
    private lateinit var simpleExoPlayer: SimpleExoPlayer
    private lateinit var mediaSource: MediaSource
    private lateinit var dataSourceFactory: DefaultDataSourceFactory
    var isAudioPlaying = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.entry_detail_fragment, container, false)
        viewModel = ViewModelProvider(this)[EntryDetailViewModel::class.java]
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            viewModel.entryLiveData.value = it.getParcelable(ENTRYOBJ)
        }
        init()
    }

    private fun init() {
        setupExoPlayer()
    }

    private fun setupExoPlayer() {
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(activity)
        dataSourceFactory =
            DefaultDataSourceFactory(activity, Util.getUserAgent(activity, "exoPlayerSample"))
        mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(viewModel.entryLiveData.value?.audioLink))
        simpleExoPlayer.prepare(mediaSource)
        with(simpleExoPlayer) {
            btnFab.setOnClickListener {
                if (isAudioPlaying) {
                    pauseMedia()
                    playWhenReady = false
                } else {
                    playWhenReady = true
                    playMedia()
                }
            }
        }
    }

    private fun playMedia() {
        Log.d(TAG, "play media")
        isAudioPlaying = true
        btnFab.setImageResource(R.drawable.ic_stop)
    }

    private fun pauseMedia() {
        Log.d(TAG, "stop media")
        isAudioPlaying = false
        btnFab.setImageResource(R.drawable.ic_play)
    }

    override fun onDestroy() {
        simpleExoPlayer.playWhenReady = false
        super.onDestroy()

    }
}