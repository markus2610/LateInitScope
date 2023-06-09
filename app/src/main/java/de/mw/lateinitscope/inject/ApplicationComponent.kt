package de.mw.lateinitscope.inject

import android.app.Application
import android.content.Context
import de.mw.lateinitscope.MWApplication
import de.mw.lateinitscope.LazyCycleComponent
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
@ApplicationScope
abstract class ApplicationComponent(
    @get:Provides val application: Application,
): LazyCycleComponent {

    companion object {
        fun from(context: Context): ApplicationComponent {
            return (context.applicationContext as MWApplication).component
        }
    }
}
