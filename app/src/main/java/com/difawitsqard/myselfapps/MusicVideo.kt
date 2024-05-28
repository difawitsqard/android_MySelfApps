package com.difawitsqard.myselfapps

// Created : 26/05/24
// NIM     : 10121916
// Nama    : Difa Witsqa RD
// Kelas   : IF9K

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MusicVideo.newInstance] factory method to
 * create an instance of this fragment.
 */
class MusicVideo : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_music_video, container, false)

        // music
        val musicTitles = resources.getStringArray(R.array.music_titles)
        val musicArtists = resources.getStringArray(R.array.music_artist)

        val musicList = mutableListOf<MusicItem>()
        for (i in musicTitles.indices) {
            val title = musicTitles[i]
            val artist = musicArtists.getOrNull(i) ?: ""
            musicList.add(MusicItem(title, artist))
        }

        val musicRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewMusic)
        musicRecyclerView.layoutManager = LinearLayoutManager(context)
        musicRecyclerView.adapter = MusicItemsAdapter(musicList)

        // video
        val videoTitles = resources.getStringArray(R.array.video_titles)
        val videoDurations = resources.getStringArray(R.array.video_duration)

        val videoList = mutableListOf<VideoItem>()
        for (i in videoTitles.indices) {
            val title = videoTitles[i]
            val duration = videoDurations.getOrNull(i) ?: ""
            videoList.add(VideoItem(title, duration))
        }

        val videoRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewVideo)
        videoRecyclerView.layoutManager = LinearLayoutManager(context)
        videoRecyclerView.adapter = VideoItemsAdapter(videoList)

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MusicVideo.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MusicVideo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}