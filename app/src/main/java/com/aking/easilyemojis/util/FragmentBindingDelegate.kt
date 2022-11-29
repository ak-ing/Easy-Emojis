/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aking.easilyemojis.util


import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import kotlin.reflect.KProperty

class FragmentViewBindingDelegate<in R : Fragment, out T : ViewDataBinding>(private val autoDestroy: Boolean) {

    private var binding: T? = null

    operator fun getValue(fragment: R, property: KProperty<*>): T {
        if (fragment.viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.DESTROYED) {
            throw IllegalStateException("The property of ${property.name} has been destroyed.")
        }

        if (binding == null) {
            binding = DataBindingUtil.bind<T>(fragment.requireView())?.apply {
                lifecycleOwner = fragment.viewLifecycleOwner
            }
            fragment.viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                override fun onDestroy(owner: LifecycleOwner) {
                    if (!autoDestroy) return
                    binding?.unbind()
                    binding = null
                }
            })
        }
        return binding!!
    }

}

/**
 * 绑定fragment布局View，设置生命周期所有者并返回binding。
 * @param autoDestroy 自动销毁，在onDestroyView之前执行，默认为true
 */
fun <R : Fragment, T : ViewDataBinding> Fragment.binding(autoDestroy: Boolean = true): FragmentViewBindingDelegate<R, T> =
    FragmentViewBindingDelegate(autoDestroy)