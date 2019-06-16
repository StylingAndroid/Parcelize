package com.stylingandroid.parcelize

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import timber.log.Timber

class MainFragment : Fragment() {

    private var simple: SimpleDataClass? = null
    private var compound: CompoundDataClass? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            simple = it.getParcelable(ARG_SIMPLE)
            compound = it.getParcelable(ARG_COMPOUND)
        }
        Timber.d("Simple: \"%s\"; Compound: \"%s\"", simple, compound)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    companion object {
        private const val ARG_SIMPLE = "simple"
        private const val ARG_COMPOUND = "compound"

        @JvmStatic
        fun newInstance(simpleDataClass: SimpleDataClass, compound: CompoundDataClass) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_SIMPLE, simpleDataClass)
                    putParcelable(
                        ARG_COMPOUND,
                        CompoundDataClass::class.java.expand(compound.collapse())
                    )
                }
            }
    }
}
