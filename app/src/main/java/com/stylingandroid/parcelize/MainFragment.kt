package com.stylingandroid.parcelize

import android.os.Bundle
import android.os.Parcel
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
                    var parcel = Parcel.obtain()
                    parcel.writeParcelable(compound, 0)
                    val byteArray = parcel.marshall()
                    parcel.recycle()
                    parcel = Parcel.obtain()
                    parcel.unmarshall(byteArray, 0, byteArray.size)
                    parcel.setDataPosition(0)
                    putParcelable(
                        ARG_COMPOUND,
                        parcel.readParcelable(CompoundDataClass::class.java.classLoader)
                    )
                    parcel.recycle()
                }
            }
    }
}
