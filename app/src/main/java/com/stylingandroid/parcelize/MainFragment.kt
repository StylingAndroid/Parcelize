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
                        /*
                         * Don't do this.
                         *
                         * I've done this here in some sample code to demonstrate
                         * that things don't always get flattened. There is no
                         * reason than you'd actually want to do this in this context.
                         *
                         * So...really...don't do this.
                         *
                         * Look, I'm not joking, you really shouldn't do this.
                         *
                         * Even if you're being attacked by a pack of wild dogs and
                         * think that collapsing then immediately re-expanding a
                         * Parcelable will save your life, then I'm sorry, but
                         * it won't. Rest In Peace.
                         *
                         * Perhaps I forgot to mention: you really shouldn't do this.
                         */
                        CompoundDataClass::class.java.expand(compound.collapse())
                    )
                }
            }
    }
}
