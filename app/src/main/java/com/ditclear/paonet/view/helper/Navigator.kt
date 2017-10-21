package com.ditclear.paonet.view.helper

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.content.ContextCompat
import android.view.View
import com.ditclear.paonet.R
import com.ditclear.paonet.model.data.Article
import com.ditclear.paonet.view.Constants
import com.ditclear.paonet.view.article.ArticleDetailActivity
import com.ditclear.paonet.view.auth.LoginActivity
import com.ditclear.paonet.view.search.SearchActivity
import com.ditclear.paonet.view.transitions.FabTransform
import com.ditclear.paonet.view.transitions.MorphTransform


/**
 * 页面描述：页面跳转
 *
 * Created by ditclear on 2017/10/2.
 */

fun navigateToArticleDetail(activity: Activity, v: View?, article: Article) {
    val intent = Intent(activity, ArticleDetailActivity::class.java)
    val bundle = Bundle()
    bundle.putSerializable(Constants.KEY_SERIALIZABLE, article)
    intent.putExtras(bundle)
    v?.let {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, it,
                activity.getString(R.string.transition_image))
        activity.startActivity(intent,options.toBundle())
        return
    }
    activity.startActivity(intent)
}

//登录
fun needsLogin(@ColorRes color: Int, triggeringView: View) {
    val context: Activity = triggeringView.context as Activity
    val login = Intent(context, LoginActivity::class.java)
    val startColor = ContextCompat.getColor(context, color)
    if (triggeringView is FloatingActionButton) {
        val fabIcon = triggeringView.getTag(R.integer.fab_icon) as Int? ?: R.color.background_light
        FabTransform.addExtras(login, startColor, fabIcon)
    } else {
        MorphTransform.addExtras(login, startColor, (triggeringView.height / 2))
    }
    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            context,
            triggeringView, context.resources.getString(R.string.transition_login))

    context.startActivityForResult(login, 1, options.toBundle())
}

//搜索
fun navigateToSearch(activity: Activity, v: View? = null) {
    v?.let {
        val options = ActivityOptions.makeSceneTransitionAnimation(activity, v,
                activity.getString(R.string.transition_search_back)).toBundle()
        activity.startActivity(Intent(activity, SearchActivity::class.java), options)
        return
    }
    activity.startActivity(Intent(activity, SearchActivity::class.java))
}