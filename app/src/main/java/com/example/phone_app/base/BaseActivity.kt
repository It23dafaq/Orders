package com.example.phone_app.base

import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import com.example.phone_app.R
import com.example.phone_app.utils.helpers.PreferencesHelper
import com.example.phone_app.utils.singletons.Statics
import org.koin.android.ext.android.inject
import java.util.*

abstract class BaseActivity : AppCompatActivity() {

    private val preferencesHelper: PreferencesHelper by inject()

    abstract val layoutResource: Int

    abstract fun handleIntent()

    abstract fun setupViews()

    abstract fun setupListeners()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
        /*supportActionBar?.hide()*/
        handleIntent()
        setupViews()
        setupListeners()
    }

    @CallSuper
    override fun attachBaseContext(newBase: Context) {
        val currentLanguage = newBase.getString(R.string.language)
        val newLanguage = preferencesHelper.getString(Statics.PREFERENCES_LANGUAGE)
        when {
            (currentLanguage != newLanguage || Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) ->
                super.attachBaseContext(newBase.getLocaleWrappedContext(Locale(newLanguage)))
            else -> super.attachBaseContext(newBase)
        }
    }

    private fun Context.getLocaleWrappedContext(newLocale: Locale): ContextWrapper {
        try {
            Locale.setDefault(newLocale)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
                resources.configuration.setLocale(newLocale)

                val localeList = LocaleList(newLocale)
                LocaleList.setDefault(localeList)
                resources.configuration.locales = localeList

                val newContext = createConfigurationContext(resources.configuration)
                newContext.resources.configuration.setLocale(newLocale)
                ContextWrapper(newContext)
            }
            else -> {
                resources.configuration.setLocale(newLocale)
                ContextWrapper(createConfigurationContext(resources.configuration))
            }
        }
    }
}