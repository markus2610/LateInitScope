package de.mw.lateinitscope

import android.app.Application
import de.mw.lateinitscope.inject.ApplicationComponent
import de.mw.lateinitscope.inject.create

class MWApplication : Application() {

    val component: ApplicationComponent by lazy { ApplicationComponent::class.create(this) }
}
