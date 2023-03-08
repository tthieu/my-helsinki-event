package com.tthieu.myhelsinki.common.data.preferences

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MyHelsinkiPreferences @Inject constructor(
    @ApplicationContext context: Context
): Preferences {
}