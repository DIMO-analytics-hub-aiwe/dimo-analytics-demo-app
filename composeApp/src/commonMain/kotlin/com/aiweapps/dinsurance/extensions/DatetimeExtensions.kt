package com.aiweapps.dinsurance.extensions

import kotlinx.datetime.Clock

fun epochMillisNow() = Clock.System.now().toEpochMilliseconds()