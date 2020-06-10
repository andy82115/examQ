package com.andyyeh.examQ.mainActivity.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andyyeh.examQ.Configuration
import com.andyyeh.examQ.R
import com.andyyeh.examQ.mainActivity.data.ClimateBean
import com.andyyeh.examQ.mainActivity.data.ClimateModel
import com.bumptech.glide.Glide
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.item_climate_img.view.*
import kotlinx.android.synthetic.main.item_climate_txt.view.*
import java.lang.Exception

class ClimateAdapter(climateModel: ClimateModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mData = climateModel.datas

    var txtClickedObserver: Consumer<ClimateBean>? = null

    inner class TxtViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
         @SuppressLint("CheckResult")
         fun bind(position: Int){
             itemView.vItemTxtStartTimeTV.text = mData[position].StartTime
             itemView.vItemTxtEndTimeTV.text = mData[position].EndTime
             itemView.vItemTxtParameterTV.text = mData[position].Parameter
             itemView.vItemTxtClimateCV.setOnClickListener {
                 Single.just(mData[position])
                     .subscribeOn(Schedulers.io())
                     .observeOn(AndroidSchedulers.mainThread())
                     .subscribe(txtClickedObserver)
             }
         }
    }

    inner class ImgViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(position: Int){
            Glide.with(itemView.context).load(mData[position].ImgId).into(itemView.vItemImgClimateIV)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return mData[position].Type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Configuration.CLIMATE_TXT_TYPE -> {
                val txtView = LayoutInflater.from(parent.context).inflate(R.layout.item_climate_txt, parent, false)
                TxtViewHolder(txtView)
            }
            Configuration.CLIMATE_IMG_TYPE -> {
                val imgView = LayoutInflater.from(parent.context).inflate(R.layout.item_climate_img, parent, false)
                ImgViewHolder(imgView)
            }
            else -> {
                throw Exception("We don't have this type of item")
            }
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is TxtViewHolder -> holder.bind(position)
            is ImgViewHolder -> holder.bind(position)
        }
    }


}