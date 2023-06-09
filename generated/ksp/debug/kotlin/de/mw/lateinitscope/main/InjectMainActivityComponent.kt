package de.mw.lateinitscope.main

import android.app.Activity
import de.mw.lateinitscope.LazyCycleBar
import de.mw.lateinitscope.inject.ApplicationComponent
import de.mw.lateinitscope.inject.ComposeScreens
import kotlin.reflect.KClass
import me.tatarka.inject.`internal`.ScopedComponent

public fun KClass<MainActivityComponent>.create(activity: Activity,
    applicationComponent: ApplicationComponent): MainActivityComponent =
    InjectMainActivityComponent(activity, applicationComponent)

public fun KClass<MainActivityComponent>.create(activity: Activity): MainActivityComponent =
    InjectMainActivityComponent(activity)

public class InjectMainActivityComponent : MainActivityComponent {
  public override val composeScreens: ComposeScreens
    get() {
      require(applicationComponent is ScopedComponent)
      return ComposeScreens(
        greeting = { arg0 ->
          Greeting(
            name = arg0,
            lazyCycleBar = applicationComponent._scoped.get("de.mw.lateinitscope.LazyCycleBar") {
              run {
                lateinit var lazyCycleBar: LazyCycleBar
                applicationComponent.bar(
                  foo = lazy {
                    applicationComponent._scoped.get("de.mw.lateinitscope.LazyCycleFoo") {
                      applicationComponent.foo(
                        bar = applicationComponent._scoped.get("de.mw.lateinitscope.LazyCycleBar") {
                          lazyCycleBar
                        }
                      )
                    }
                  }
                ).also {
                  lazyCycleBar = it
                }
              }

            }
          )
        }
      )
    }

  public constructor(activity: Activity, applicationComponent: ApplicationComponent) :
      super(activity, applicationComponent)

  public constructor(activity: Activity) : super(activity = activity)
}
