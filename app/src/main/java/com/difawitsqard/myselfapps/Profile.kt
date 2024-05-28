package com.difawitsqard.myselfapps

// Created : 26/05/24
// NIM     : 10121916
// Nama    : Difa Witsqa RD
// Kelas   : IF9K

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.difawitsqard.myselfapps.databinding.FragmentProfileBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Profile.newInstance] factory method to
 * create an instance of this fragment.
 */
class Profile : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var map: GoogleMap


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
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        // Setup the map fragment
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync { googleMap ->
            map = googleMap
            val homeLocation = LatLng(-6.893610170200492, 107.63127325407258) // Replace with your location
            map.addMarker(MarkerOptions().position(homeLocation).title("Home"))
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(homeLocation, 10f))
        }

        // Setup contact buttons
        binding.buttonCall.setOnClickListener { dialPhoneNumber("+6281280788963") }
        binding.buttonSendEmail.setOnClickListener { sendEmail("difawitsqard@gmail.com") }
        binding.buttonInstagram.setOnClickListener { openSocialMedia(getString(R.string.instagram_url)) }
        binding.buttonTiktok.setOnClickListener { openSocialMedia(getString(R.string.tiktok_url)) }
        binding.buttonFacebook.setOnClickListener { openSocialMedia(getString(R.string.facebook_url)) }
        binding.buttonAbout.setOnClickListener { showAboutDialog() }

        return view
    }

    private fun dialPhoneNumber(phoneNumber: String) {
        try {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phoneNumber")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Tidak dapat membuka aplikasi telepon", Toast.LENGTH_SHORT).show()
        }
    }


    private fun sendEmail(emailAddress: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // only email apps should handle this
            putExtra(Intent.EXTRA_EMAIL, arrayOf(emailAddress))
        }
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(requireContext(), "Tidak dapat membuka aplikasi email", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openSocialMedia(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showAboutDialog() {

        val aboutDialog = AlertDialog.Builder(context)
            .setTitle("Version")
            .setMessage("1.0")
            .setPositiveButton(android.R.string.ok, null)
            .create()

        aboutDialog.show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Profile.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Profile().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}