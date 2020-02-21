package br.com.prado.eduardo.luiz.navigationmodules

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration.Builder(
            setOf(R.id.charactersListFragment, R.id.locationListFragment)
        ).build()
    }

    private val navController: NavController by lazy {
        findNavController(R.id.fragment_nav_host)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolMain = findViewById<Toolbar>(R.id.toolbar_main)
        setSupportActionBar(toolMain)

        val bottomNavMain = findViewById<BottomNavigationView>(R.id.bottom_nav_main)

        bottomNavMain.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.charactersListFragment, R.id.locationListFragment -> bottomNavMain.visibility =
                    View.VISIBLE
                else -> bottomNavMain.visibility = View.GONE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
