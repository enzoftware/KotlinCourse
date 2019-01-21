package com.projects.enzoftware.cryptotracker.utils

import org.koin.log.Logger
import timber.log.Timber


class TimberLogger : Logger {
    override fun debug(msg: String) {
        Timber.d(msg)
    }

    override fun err(msg: String) {
        Timber.e(msg)
    }

    override fun info(msg: String) {
        Timber.i(msg)
    }

}