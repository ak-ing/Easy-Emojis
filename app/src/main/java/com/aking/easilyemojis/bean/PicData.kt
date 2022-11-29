package com.aking.easilyemojis.bean

/**
 * Created by Rick at 2022/11/28 22:08
 * God bless my code.
 * @Description 图片Data
 */
data class PicData(
    val actualhits: String,
    val hit: Hit,
    val numhits: String,
    val totalhits: String
)

data class Hit(
    val POSITION: String,
    val clickfeedback: String,
    val imgInfo: ImgInfo,
    val isMakeupData: String,
    val keyword: String,
    val pn: String,
    val sc_stype: String,
    val src: String,
    val start: String
)

data class ImgInfo(
    val collectTotal: String,
    val item: List<Pic>
)

data class Pic(
    val bigPicCache: String,
    val bigPicUrl: String,
    val cateId: String,
    val copyright: String,
    val create_time: String,
    val create_time_diff: String,
    val firstCate: String,
    val firstCateNew: String,
    val height: String,
    val imageDiscomfortType: String,
    val imageType: String,
    val img: String,
    val imgNumInSet: String,
    val imgUrl: String,
    val isAbove: String,
    val isAd: String,
    val isImgSet: String,
    val isSave: Boolean,
    val l2Score: String,
    val ltrScore: String,
    val pkIndex: String,
    val qiScore: String,
    val qtScore: String,
    val secondCate: String,
    val secondCateNew: String,
    val site: String,
    val thirdCate: String,
    val title: String,
    val width: String
)