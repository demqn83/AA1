package ru.demqn.appname

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity(), FragmentMoviesList.TransactionsFragmentClicks {

    private val rootFragment =
        FragmentMoviesList().apply { setClickListener(this@MainActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
    //            add(R.id.container_view, FragmentMoviesList())
                add(R.id.container_view, rootFragment)
                commit()
            }
        }
    }

    override fun addMovieDetails() {
//        val toast = Toast.makeText(applicationContext, "Popal", Toast.LENGTH_LONG)
//        toast.show()
        supportFragmentManager.beginTransaction().apply {
            addToBackStack(null)
            replace(R.id.container_view, FragmentMoviesDetails())
            commit()
        }
    }
}