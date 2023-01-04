package com.aking.easilyemojis.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.aking.easilyemojis.R
import com.aking.easilyemojis.base.BaseFragment
import com.aking.easilyemojis.databinding.FragmentMainBinding
import com.aking.easilyemojis.ui.adapter.PicPagingAdapter
import com.aking.easilyemojis.ui.vm.HomeViewModel
import com.aking.easilyemojis.util.binding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * Created by Rick at 2022/11/27 18:28
 * God bless my code.
 */
class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by binding()
    private val vm: HomeViewModel by viewModels()
    private val picPagingAdapter = PicPagingAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TAG", "onViewCreated: ")
        init()
        observer()
    }

    private fun observer() {
        picPagingAdapter.addLoadStateListener {
            Log.d("TAG", "addLoadStateListener: ")
            when (it.refresh) {
                is LoadState.NotLoading -> {
//                    progressBar.visibility = View.INVISIBLE
//                    recyclerView.visibility = View.VISIBLE
                }
                is LoadState.Loading -> {
//                    progressBar.visibility = View.VISIBLE
//                    recyclerView.visibility = View.INVISIBLE
                }
                is LoadState.Error -> {
                    val state = it.refresh as LoadState.Error
//                    progressBar.visibility = View.INVISIBLE
                    state.error.printStackTrace()
                    Toast.makeText(
                        context, "Load Error: ${state.error.message}", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        lifecycleScope.launch {
            Log.d("TAG", "launch: ")
            delay(2000)
            vm.picPagingFlow.collect {
                Log.d("TAG", "requestPic:")
                picPagingAdapter.submitData(it)
            }
        }

    }

    private fun init() {
        binding.rvPic.adapter = picPagingAdapter
    }

    /**
     * 搜索图片
     */
    fun searchPic(query: HashMap<String, String>) = vm.requestPic(query)


}