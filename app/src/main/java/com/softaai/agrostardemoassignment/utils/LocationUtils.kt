package com.softaai.agrostardemoassignment.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat

class LocationUtils {
    private val REQ_PERM_CODE = 9874

    fun checkLocationPermission(activity: Activity?): Boolean {
        val fineLoc = ContextCompat
            .checkSelfPermission(activity!!, Manifest.permission.ACCESS_FINE_LOCATION)
        val coarseLoc = ContextCompat
            .checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
        if (fineLoc != PackageManager.PERMISSION_GRANTED ||
            coarseLoc != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                activity,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                REQ_PERM_CODE
            )
            return false
        }
        return true
    }

    fun checkLocationPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ): Boolean {
        return requestCode == REQ_PERM_CODE && grantResults.size >= 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED
    }

}