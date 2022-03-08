/**
 * 2022.03.08
 * Teemu Eerola
 * 1606161
 *
 * Single activity application that uses the navigation library.
 * Content is displayed by fragments.
 */

package com.example.parliamentapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.parliamentapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}