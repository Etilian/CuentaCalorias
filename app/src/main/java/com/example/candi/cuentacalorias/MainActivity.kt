package com.example.candi.cuentacalorias

import android.net.Uri
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_calorias.*
import kotlinx.android.synthetic.main.fragment_platos.*

class MainActivity : AppCompatActivity(), alimentosFragment.OnFragmentInteractionListener,
    caloriasFragment.OnFragmentInteractionListener, platosFragment.OnFragmentInteractionListener,
    perfilFragment.OnFragmentInteractionListener {

    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var sqlite: AdminSQlite

    private lateinit var adapter: AdapterDatosTres

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = FirebaseDatabase.getInstance()
        dbReference = database.getReference("Calorias")
        sqlite = AdminSQlite(this, "administracion.db", null, 1)

        sqlite.creaPersona()
        sqlite.creaPerfil()

        setSupportActionBar(toolbar)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    fun leer(): ArrayList<Calorias> {
        return sqlite.leerSqlite()
    }

    fun insertarCaloria() {
        if ( editText1.text.length==0 || editText2.text.length==0 ) {
            Toast.makeText(this, "Inserte plato y caloria", Toast.LENGTH_SHORT).show()
        } else {
            try {
                sqlite.insertar(editText1.text.toString(), Integer.parseInt(editText2.text.toString()))
                editText1.setText("")
                editText2.setText("")
            }catch (ex: NumberFormatException) {
                Toast.makeText(this, "El campo calorias solo puede contener numeros", Toast.LENGTH_SHORT).show()
                editText2.setText("")
            }
        }
    }

    fun borrarPlato(){
        if ( editText1.text.length==0 || editText2.text.length==0 ) {
            Toast.makeText(this, "Inserte plato y caloria", Toast.LENGTH_SHORT).show()
        } else {
            try {
                if ( sqlite.borrarBD(editText1.text.toString()) ) {
                    Toast.makeText(this, "Borrado correctamente", Toast.LENGTH_SHORT).show()
                    editText1.setText("")
                    editText2.setText("")
                }else{
                    Toast.makeText(this, "Tenga en cuenta los espacios y mallusculas para Borrar", Toast.LENGTH_SHORT).show()
                    editText1.setText("")
                    editText2.setText("")
                }
            }catch (ex: NumberFormatException) {
                Toast.makeText(this, "El campo calorias solo puede contener numeros", Toast.LENGTH_SHORT).show()
                editText2.setText("")
            }
        }
    }

    fun consultaPersona():Int {
        if ( sqlite.consultaPersona()==-1 ){
            Toast.makeText(this, "No se puede haceder a la tabla persona", Toast.LENGTH_SHORT).show()
            return 0
        }
        else
            return sqlite.consultaPersona()
    }

    fun pasarDia(){
        if ( sqlite.pasarDia() ) {

        }else{
            Toast.makeText(this, "Error al borrar", Toast.LENGTH_SHORT).show()
        }
    }

    fun insertaPerfil( caloriasDia: Double ){
        if ( sqlite.insertaPerfil(caloriasDia.toInt()) ) {
            Toast.makeText(this, "Perfil añadido", Toast.LENGTH_SHORT).show()
        }else {
            Toast.makeText(this, "Error al añadir perfil", Toast.LENGTH_SHORT).show()
        }
        textView9.text = sqlite.consultaPerfil().toString()
    }

    fun consultaPerfil():Int{
        if ( sqlite.consultaPerfil()==-1 ){
            Toast.makeText(this, "No se puede haceder a la tabla perfil", Toast.LENGTH_SHORT).show()
            return 0
        }
        else
            return sqlite.consultaPerfil()
    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if (position == 0) {
                return caloriasFragment.newInstance("a", "b")
            } else if (position == 1) {
                return perfilFragment.newInstance("a", "b")
            } else if (position == 2) {
                return alimentosFragment.newInstance("a", "b")
            } else {
                return platosFragment.newInstance("a", "b")
            }
        }

        override fun getCount(): Int {
            // Show 4 total pages.
            return 4
        }


    }
}
