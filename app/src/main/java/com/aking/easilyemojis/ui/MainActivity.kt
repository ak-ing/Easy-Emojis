package com.aking.easilyemojis.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MenuItem.OnActionExpandListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.navigation.fragment.NavHostFragment
import com.aking.easilyemojis.R
import com.aking.easilyemojis.databinding.ActivityMainBinding
import com.aking.easilyemojis.ui.fragment.MainFragment
import com.aking.easilyemojis.util.contentView


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by contentView(R.layout.activity_main)
    private lateinit var searchView: SearchView
    private val mainFragment: MainFragment by lazy() {
        (supportFragmentManager.findFragmentByTag("navHost") as NavHostFragment)
            .childFragmentManager.findFragmentById(R.id.my_mainFragment) as MainFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.topAppBar)
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top_app_bar, menu)
        menu.findItem(R.id.search)?.let { item ->
            item.setActionView(R.layout.layout_search)
            // Associate searchable configuration with the SearchView
            val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
            searchView = (menu.findItem(R.id.search).actionView as SearchView).apply {
                setSearchableInfo(searchManager.getSearchableInfo(componentName))
                setOnQueryTextListener(object : OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        val navHostFragment =
                            supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
                        val childFragmentManager = navHostFragment.childFragmentManager
                        val a = R.id.my_mainFragment
                        val findFragmentById = childFragmentManager.findFragmentById(R.id.my_mainFragment)
                        mainFragment.searchPic(hashMapOf("query" to "$query"))
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return true
                    }
                })
            }
            item.setOnActionExpandListener(object : OnActionExpandListener {
                override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                    binding.topAppBar.menu.findItem(R.id.favorite).isVisible = false
                    return true
                }

                override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                    binding.topAppBar.menu.findItem(R.id.favorite).isVisible = true
                    return true
                }
            })
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favorite -> {
                true
            }
            R.id.search -> {
                true
            }
            R.id.more -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}