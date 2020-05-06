package com.library.extensions

import android.content.Context
import android.util.Log
import androidx.annotation.NonNull
import com.gun0912.tedpermission.TedPermission
import com.library.R

fun logE(msg: String = "hey", tag: String = "AppLogger") {
    Log.e(tag, msg)
}

fun setPermission(
    context: Context, @NonNull permissions: Array<String>,
    permissionListener: com.gun0912.tedpermission.PermissionListener?,
    msg: String = context.getString(R.string.permission_denial_string)
) {

    if (permissions.isEmpty() && permissionListener != null) {
        return
    }

    TedPermission.with(context)
        .setPermissionListener(permissionListener)
        .setDeniedMessage(msg)
        .setPermissions(*permissions)
        .check()
}