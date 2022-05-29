package com.example.fu.ui

import com.example.fu.data.network.response.AddGarbageResponse
import com.example.fu.data.network.response.DataTokenForGarbage
import com.example.fu.data.network.response.GarbageType
import com.example.fu.databinding.ItemGarbageBinding
import com.example.fu.databinding.ItemGarbageTypeBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

sealed class RecyclerItem {

    data class GarbageType(
        val id: Int,
        val name: String,
    ) : RecyclerItem()

    object Loading : RecyclerItem()

    object Error : RecyclerItem()
}


fun typeGarbageListDelegate(
) =
    adapterDelegateViewBinding<DataTokenForGarbage, Any, ItemGarbageBinding>(
        viewBinding = { layoutInflater, parent ->
            ItemGarbageBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        },
        block = {
            bind {
                with(binding){
                    name.text = item.name
                    barcode.text = item.barcode
                    typeOfGarbage.text = item.garbageTypes?.joinToString(", ") { it.name } ?: " no type "
                    description.text = item.description
                }

            }

        }
    )


