package com.example.candi.cuentacalorias

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_perfil.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [perfilFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [perfilFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class perfilFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        buttonPerfil.setOnClickListener {
            try {
                when {
                    RadioButton1.isChecked -> calcularCaloriasHombre()
                    RadioButton2.isChecked -> calucalaCaloriasMujer()
                    else -> Toast.makeText(context, "Elija un sexo", Toast.LENGTH_SHORT).show()
                    }
            } catch (ex: NumberFormatException) {
                Toast.makeText(context, "No deje espacios en blanco e introduzca '.' en vez de ','", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun calcularCaloriasHombre(){
        var estatura: Double
        if ( editText3.text.toString().toDouble()<5.0 ) {
            estatura = editText3.text.toString().toDouble() * 100
        } else {
            estatura = editText3.text.toString().toDouble()
        }
        when {
            RadioButton3.isChecked -> (activity as MainActivity).insertaPerfil((66 + (13.7 * editText4.text.toString().toDouble())) +
                    ((5 * estatura)-(6.8 * editText.text.toString().toDouble())) * 1.2)
            RadioButton4.isChecked -> (activity as MainActivity).insertaPerfil((66 + (13.7 * editText4.text.toString().toDouble())) +
                    ((5 * estatura)-(6.8 * editText.text.toString().toDouble())) * 1.375)
            RadioButton5.isChecked -> (activity as MainActivity).insertaPerfil((66 + (13.7 * editText4.text.toString().toDouble())) +
                    ((5 * estatura)-(6.8 * editText.text.toString().toDouble())) * 1.55)
            RadioButton6.isChecked -> (activity as MainActivity).insertaPerfil((66 + (13.7 * editText4.text.toString().toDouble())) +
                    ((5 * estatura)-(6.8 * editText.text.toString().toDouble())) * 1.725)
            RadioButton7.isChecked -> (activity as MainActivity).insertaPerfil((66 + (13.7 * editText4.text.toString().toDouble())) +
                    ((5 * estatura)-(6.8 * editText.text.toString().toDouble())) * 1.9)
            else -> Toast.makeText(context, "Elija una actividad", Toast.LENGTH_SHORT).show()
        }
    }

    fun calucalaCaloriasMujer () {
        var estatura: Double
        if ( editText3.text.toString().toDouble()<5.0 ) {
            estatura = editText3.text.toString().toDouble() * 100
        } else {
            estatura = editText3.text.toString().toDouble()
        }
        when {
            RadioButton3.isChecked -> (activity as MainActivity).insertaPerfil((655 + (9.6 * editText4.text.toString().toDouble())) +
                    ((1.8 * estatura)-(4.7 * editText.text.toString().toDouble())) * 1.2)
            RadioButton4.isChecked -> (activity as MainActivity).insertaPerfil((655 + (9.6 * editText4.text.toString().toDouble())) +
                    ((1.8 * estatura)-(4.7 * editText.text.toString().toDouble())) * 1.375)
            RadioButton5.isChecked -> (activity as MainActivity).insertaPerfil((655 + (9.6 * editText4.text.toString().toDouble())) +
                    ((1.8 * estatura)-(4.7 * editText.text.toString().toDouble())) * 1.55)
            RadioButton6.isChecked -> (activity as MainActivity).insertaPerfil((655 + (9.6 * editText4.text.toString().toDouble())) +
                    ((1.8 * estatura)-(4.7 * editText.text.toString().toDouble())) * 1.725)
            RadioButton7.isChecked -> (activity as MainActivity).insertaPerfil((655 + (9.6 * editText4.text.toString().toDouble())) +
                    ((1.8 * estatura)-(4.7 * editText.text.toString().toDouble())) * 1.9)
            else -> Toast.makeText(context, "Elija una actividad", Toast.LENGTH_SHORT).show()
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment perfilFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            perfilFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
