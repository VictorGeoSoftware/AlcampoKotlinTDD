package com.victor.test.alcampokotlin.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.victor.test.alcampokotlin.R
import com.victor.test.alcampokotlin.data.models.StoreDto
import com.victor.test.alcampokotlin.data.models.StoreListByRegionDto
import com.victor.test.alcampokotlin.utils.inflate
import com.victor.test.alcampokotlin.utils.traceObject
import kotlinx.android.synthetic.main.row_stores_adapter_detail.view.*
import kotlinx.android.synthetic.main.row_stores_adapter_header.view.*

/**
 * Created by victorpalmacarrasco on 28/8/18.
 * ${APP_NAME}
 */
class StoresAdapter(storeHashMap: HashMap<String, StoreListByRegionDto>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_STORE = 1
    }

    private val itemsToShowList = ArrayList<Any>()

    init {
        val setList = storeHashMap.keys

        for (set in setList) {
            itemsToShowList.add(set)
            storeHashMap[set]?.let {
                itemsToShowList.addAll(it.storeList)
            }
        }

        traceObject("itemsToShowList size :: ${itemsToShowList.size}")
    }


    override fun getItemCount(): Int {
        return itemsToShowList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (itemsToShowList[position] is String) {
            TYPE_HEADER
        } else {
            TYPE_STORE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        traceObject("onCreateViewHolder - type :: $viewType")
        return if (viewType == TYPE_STORE) {
            CityHeaderViewHolder(parent.inflate(R.layout.row_stores_adapter_detail))
        } else {
            StoreViewHolder(parent.inflate(R.layout.row_stores_adapter_header))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is StoreViewHolder) {
            holder.bind(itemsToShowList[position] as StoreDto)
        } else if (holder is CityHeaderViewHolder) {
            holder.bind(itemsToShowList[position] as String)
        }
    }

    class CityHeaderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(cityName: String) = with(itemView) {
            txt_city_name.text = cityName
        }
    }

    class StoreViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(store: StoreDto) = with(itemView) {
            txt_store_name.text = store.label
            txt_store_address.text = store.street
            txt_store_schedule.text = store.phoneNumber
        }
    }
}
