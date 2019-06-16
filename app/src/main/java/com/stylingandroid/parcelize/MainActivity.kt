package com.stylingandroid.parcelize

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val simple = SimpleDataClass("Simple", 1)
    private val compound = CompoundDataClass("Compound", simple, "Transient")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_main, MainFragment.newInstance(simple, compound))
            commit()
        }
    }
}
