package com.aiweapps.dinsurance.utils

import com.aiweapps.dinsurance.data.ContextHolder
import platform.Foundation.NSURL
import platform.SafariServices.SFSafariViewController
import platform.UIKit.UIApplication

actual fun launchBrowser(contextHolder: ContextHolder, url: String) {
    val safariViewController = SFSafariViewController(NSURL(string = url))
    val rootViewController = UIApplication.sharedApplication.keyWindow?.rootViewController
    rootViewController?.presentViewController(safariViewController, animated = true, completion = null)
}