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
 * Use the [DailyActivity.newInstance] factory method to
 * create an instance of this fragment.
 */
class DailyActivity : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_daily_activity, container, false)

        // aktivitas harian
        val activityImgArray = resources.obtainTypedArray(R.array.activity_img)
        val activityDescArray = resources.getStringArray(R.array.activity_desc)

        val dailyActivityList = (0 until activityDescArray.size).map { i ->
            DailyActivityItem(activityImgArray.getResourceId(i, 0), activityDescArray[i])
        }.toList()

        activityImgArray.recycle()

        val dailyActivityRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewDailyActivity)
        dailyActivityRecyclerView.layoutManager = LinearLayoutManager(context)
        dailyActivityRecyclerView.adapter = DailyActivityItemsAdapter(dailyActivityList)

        // Friend
        val friendImgArray = resources.obtainTypedArray(R.array.friend_img)
        val friendNameArray = resources.getStringArray(R.array.friend_name)

        val friendList = (0 until friendNameArray.size).map { i ->
            FriendItem(friendImgArray.getResourceId(i, 0), friendNameArray[i])
        }.toList()

        friendImgArray.recycle()

        val friendRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewFriends)
        friendRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        friendRecyclerView.adapter = FriendItemsAdapter(friendList)

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DailyActivity.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DailyActivity().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}